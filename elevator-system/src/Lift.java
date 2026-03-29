import java.util.ArrayList;
import java.util.List;

public class Lift {
    private String id;
    private int currentFloor;
    private ElevatorState state;
    private double weightCapacityKg;
    private double currentWeightKg;
    private boolean doorsOpen;
    private List<Integer> pendingFloors;
    private int totalFloorsTravelled; // tracks how many floors this lift has moved in total
    private int homeFloor; // where this lift parks when idle (for zone-based positioning)

    public Lift(String id, int startFloor, double weightCapacityKg) {
        this.id = id;
        this.currentFloor = startFloor;
        this.weightCapacityKg = weightCapacityKg;
        this.currentWeightKg = 0;
        this.state = ElevatorState.IDLE;
        this.doorsOpen = false;
        this.pendingFloors = new ArrayList<>();
        this.totalFloorsTravelled = 0;
        this.homeFloor = startFloor;
    }

    public void addPassenger(double weightKg) {
        currentWeightKg += weightKg;
        if (currentWeightKg > weightCapacityKg) {
            doorsOpen = true;
            System.out.println("[ALARM] Lift " + id + " weight limit exceeded! Doors will not close. ("
                + currentWeightKg + "kg / " + weightCapacityKg + "kg)");
        }
    }

    public void removePassenger(double weightKg) {
        currentWeightKg = Math.max(0, currentWeightKg - weightKg);
        if (currentWeightKg <= weightCapacityKg && doorsOpen) {
            doorsOpen = false;
            System.out.println("[INFO] Lift " + id + " weight back in range. Doors can close.");
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
    public double getCurrentWeightKg()      { return currentWeightKg; }
    public double getWeightCapacityKg()     { return weightCapacityKg; }
    public boolean isDoorsOpen()            { return doorsOpen; }
    public List<Integer> getPendingFloors() { return pendingFloors; }

    @Override
    public String toString() {
        return "Lift[" + id + " | floor=" + currentFloor + " | state=" + state
            + " | weight=" + currentWeightKg + "/" + weightCapacityKg + "kg"
            + " | totalTravelled=" + totalFloorsTravelled + "]";
    }
}
