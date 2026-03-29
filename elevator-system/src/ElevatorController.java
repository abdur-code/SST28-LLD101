import java.util.List;

public class ElevatorController {
    private List<Lift> lifts;
    private List<Floor> floors;
    private LiftSelectionStrategy liftSelector;
    private TaskSchedulingStrategy taskScheduler;

    public ElevatorController(List<Lift> lifts, List<Floor> floors,
                              LiftSelectionStrategy liftSelector,
                              TaskSchedulingStrategy taskScheduler) {
        this.lifts = lifts;
        this.floors = floors;
        this.liftSelector = liftSelector;
        this.taskScheduler = taskScheduler;
    }

    // called when someone presses up/down on a floor
    public void handleExternalRequest(ElevatorRequest request) {
        Floor floor = getFloor(request.getFromFloor());
        if (floor != null && floor.isUnderMaintenance()) {
            System.out.println("Floor " + request.getFromFloor() + " is under maintenance. Request ignored.");
            return;
        }

        System.out.println("\n>> " + request);
        Lift chosen = liftSelector.selectLift(lifts, request);
        if (chosen == null) {
            System.out.println("No lift available right now.");
            return;
        }

        System.out.println("Dispatching " + chosen.getId() + " to floor " + request.getFromFloor());
        chosen.addDestination(request.getFromFloor());
        runSchedule(chosen);
    }

    // called when someone inside the lift presses a floor button
    public void handleInternalRequest(Lift lift, int destinationFloor) {
        System.out.println("\n>> Lift " + lift.getId() + " internal request: go to floor " + destinationFloor);
        lift.addDestination(destinationFloor);
        runSchedule(lift);
    }

    private void runSchedule(Lift lift) {
        List<Integer> order = new java.util.ArrayList<>(taskScheduler.schedule(lift, lift.getPendingFloors()));
        System.out.println("Stop order for " + lift.getId() + ": " + order);
        for (int floor : order) {
            lift.moveTo(floor);
        }
    }

    public void setLiftUnderMaintenance(String liftId, boolean flag) {
        Lift lift = getLift(liftId);
        if (lift != null) lift.setUnderMaintenance(flag);
    }

    public void setFloorUnderMaintenance(int floorNumber, boolean flag) {
        Floor floor = getFloor(floorNumber);
        if (floor != null) floor.setUnderMaintenance(flag);
    }

    public void setLiftSelectionStrategy(LiftSelectionStrategy strategy) {
        this.liftSelector = strategy;
    }

    public void setTaskSchedulingStrategy(TaskSchedulingStrategy strategy) {
        this.taskScheduler = strategy;
    }

    public void printStatus() {
        System.out.println("\n--- Lift Status ---");
        for (Lift l : lifts) System.out.println(l);
        System.out.println("-------------------");
    }

    private Lift getLift(String id) {
        for (Lift l : lifts) if (l.getId().equals(id)) return l;
        return null;
    }

    private Floor getFloor(int number) {
        for (Floor f : floors) if (f.getFloorNumber() == number) return f;
        return null;
    }
}
