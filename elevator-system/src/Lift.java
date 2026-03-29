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

    public Lift(String id, int startFloor, double weightCapacityKg) {
        this.id = id;
        this.currentFloor = startFloor;
        this.weightCapacityKg = weightCapacityKg;
        this.currentWeightKg = 0;
        this.state = ElevatorState.IDLE;
        this.doorsOpen = false;
        this.pendingFloors = new ArrayList<>();
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
        System.out.println("Lift " + id + " moving from floor " + currentFloor + " → " + floor);
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
            + " | weight=" + currentWeightKg + "/" + weightCapacityKg + "kg]";
    }
}
