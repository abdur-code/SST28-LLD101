import java.util.ArrayList;
import java.util.List;

public class Building {
    private String name;
    private ElevatorController controller;

    public Building(String name, int numFloors, int numLifts) {
        this.name = name;

        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) {
            floors.add(new Floor(i));
        }

        // spread lifts across different zones so they cover the whole building
        // e.g. 2 lifts in a 20 floor building → one starts at floor 0, another at floor 10
        int zoneSize = numFloors / Math.max(numLifts, 1);
        List<Lift> lifts = new ArrayList<>();
        for (int i = 0; i < numLifts; i++) {
            int homeFloor = i * zoneSize;
            Lift lift = new Lift("L" + (i + 1), homeFloor, LiftType.PASSENGER);
            lift.setHomeFloor(homeFloor);
            lifts.add(lift);
        }

        this.controller = new ElevatorController(
            lifts, floors,
            new NearestLiftStrategy(),
            new ScanSchedulingStrategy()
        );

        System.out.println(name + " — " + numLifts + " lifts, " + numFloors + " floors");
    }

    public ElevatorController getController() { return controller; }
    public String getName()                   { return name; }
}
