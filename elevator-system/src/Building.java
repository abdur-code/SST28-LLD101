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

        List<Lift> lifts = new ArrayList<>();
        for (int i = 0; i < numLifts; i++) {
            lifts.add(new Lift("L" + (i + 1), 0, 750));
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
