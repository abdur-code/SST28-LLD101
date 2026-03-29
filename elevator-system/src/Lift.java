import java.util.ArrayList;
import java.util.List;

public class Lift {
    private String id;
    private int currentFloor;
    private ElevatorState state;
    private LiftType liftType;
    private double currentWeightKg;
    private int passengerCount;
    private boolean doorsOpen;
    private List<Integer> pendingFloors;
    private int totalFloorsTravelled;
    private int homeFloor;

    public Lift(String id, int startFloor, LiftType liftType) {
        this.id = id;
        this.currentFloor = startFloor;
        this.liftType = liftType;
        this.currentWeightKg = 0;
        this.passengerCount = 0;
        this.state = ElevatorState.IDLE;
        this.doorsOpen = false;
        this.pendingFloors = new ArrayList<>();
        this.totalFloorsTravelled = 0;
        this.homeFloor = startFloor;
    }

    public void addPassenger(double weightKg) {
        currentWeightKg += weightKg;
        passengerCount++;

        if (passengerCount > liftType.getMaxPassengers()) {
            doorsOpen = true;
            System.out.println("[ALARM] Lift " + id + " (" + liftType + ") max passengers exceeded! "
                + passengerCount + "/" + liftType.getMaxPassengers());
        }
        if (currentWeightKg > liftType.getMaxWeightKg()) {
            doorsOpen = true;
            System.out.println("[ALARM] Lift " + id + " (" + liftType + ") weight limit exceeded! "
                + currentWeightKg + "kg / " + liftType.getMaxWeightKg() + "kg");
        }
    }

    public void removePassenger(double weightKg) {
        currentWeightKg = Math.max(0, currentWeightKg - weightKg);
        passengerCount = Math.max(0, passengerCount - 1);

        boolean weightOk = currentWeightKg <= liftType.getMaxWeightKg();
        boolean countOk = passengerCount <= liftType.getMaxPassengers();
        if (weightOk && countOk && doorsOpen) {
            doorsOpen = false;
            System.out.println("[INFO] Lift " + id + " back within limits. Doors can close.");
        }
    }

    public void addDestination(int floor) {
        if (!pendingFloors.contains(floor)) {
            pendingFloors.add(floor);
        }
    }

    public void moveTo(int floor) {
        int floorsMovedNow = Math.abs(floor - currentFloor);
        totalFloorsTravelled += floorsMovedNow;
        System.out.println("Lift " + id + " moving from floor " + currentFloor + " → " + floor
            + " (trip cost: " + floorsMovedNow + " floors)");
        if (floor > currentFloor) state = ElevatorState.MOVING_UP;
        else if (floor < currentFloor) state = ElevatorState.MOVING_DOWN;
        currentFloor = floor;
        pendingFloors.remove(Integer.valueOf(floor));
        if (pendingFloors.isEmpty()) state = ElevatorState.IDLE;
    }

    public void setUnderMaintenance(boolean flag) {
        if (flag) {
            state = ElevatorState.UNDER_MAINTENANCE;
            System.out.println("Lift " + id + " is now under maintenance.");
        } else {
            state = ElevatorState.IDLE;
            System.out.println("Lift " + id + " is back in service.");
        }
    }

    public void setHomeFloor(int floor) { this.homeFloor = floor; }
    public int getHomeFloor()           { return homeFloor; }
    public int getTotalFloorsTravelled() { return totalFloorsTravelled; }

    // send lift back to its home floor if it has nothing to do
    public void returnToHomeIfIdle() {
        if (state == ElevatorState.IDLE && currentFloor != homeFloor && pendingFloors.isEmpty()) {
            System.out.println("Lift " + id + " repositioning to home floor " + homeFloor);
            moveTo(homeFloor);
        }
    }

    public String getId()                   { return id; }
    public int getCurrentFloor()            { return currentFloor; }
    public ElevatorState getState()         { return state; }
    public LiftType getLiftType()            { return liftType; }
    public double getCurrentWeightKg()      { return currentWeightKg; }
    public int getPassengerCount()          { return passengerCount; }
    public boolean isDoorsOpen()            { return doorsOpen; }
    public List<Integer> getPendingFloors() { return pendingFloors; }

    @Override
    public String toString() {
        return "Lift[" + id + " | " + liftType + " | floor=" + currentFloor + " | state=" + state
            + " | passengers=" + passengerCount + "/" + liftType.getMaxPassengers()
            + " | weight=" + currentWeightKg + "/" + liftType.getMaxWeightKg() + "kg"
            + " | totalTravelled=" + totalFloorsTravelled + "]";
    }
}
