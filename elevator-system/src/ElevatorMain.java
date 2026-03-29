public class ElevatorMain {
    public static void main(String[] args) {

        // lifts start spread across zones: L1 at floor 0, L2 at floor 10
        Building building = new Building("TechPark Tower", 20, 2);
        ElevatorController ctrl = building.getController();
        ctrl.printStatus();

        // floor 5 request → L1 (zone 0-9) is closer than L2 (zone 10-19)
        System.out.println("\n--- someone on floor 5 presses UP ---");
        ctrl.handleExternalRequest(new ElevatorRequest(5, Direction.UP));

        // floor 14 request → L2 should pick this up since it owns that zone
        System.out.println("\n--- someone on floor 14 presses DOWN ---");
        ctrl.handleExternalRequest(new ElevatorRequest(14, Direction.DOWN));

        ctrl.printStatus();

        System.out.println("\n--- person boards at floor 5, presses 12, 8, 15 ---");
        Lift demoLift = new Lift("L1-demo", 5, 750);
        demoLift.addDestination(12);
        demoLift.addDestination(8);
        demoLift.addDestination(15);

        ScanSchedulingStrategy scan = new ScanSchedulingStrategy();
        java.util.List<Integer> order = scan.schedule(demoLift, demoLift.getPendingFloors());
        System.out.println("stop order from floor 5: " + order);

        System.out.println("\n--- weight overload ---");
        Lift overloadLift = new Lift("L-test", 0, 750);
        overloadLift.addPassenger(200);
        overloadLift.addPassenger(200);
        overloadLift.addPassenger(200);
        overloadLift.addPassenger(180); // 780kg total, cap is 750
        System.out.println(overloadLift);

        System.out.println("\none person steps off...");
        overloadLift.removePassenger(180);
        System.out.println(overloadLift);

        // 3-lift building to show load balancing across zones
        Building b2 = new Building("Test Block", 15, 3);
        ElevatorController ctrl2 = b2.getController();
        ctrl2.printStatus();

        // fire multiple requests — should get distributed across lifts
        System.out.println("\n--- burst of requests ---");
        ctrl2.handleExternalRequest(new ElevatorRequest(2, Direction.UP));
        ctrl2.handleExternalRequest(new ElevatorRequest(7, Direction.UP));
        ctrl2.handleExternalRequest(new ElevatorRequest(12, Direction.DOWN));
        ctrl2.printStatus();

        System.out.println("\n--- L1 goes under maintenance, request on floor 3 ---");
        ctrl2.setLiftUnderMaintenance("L1", true);
        ctrl2.handleExternalRequest(new ElevatorRequest(3, Direction.DOWN));

        System.out.println("\n--- floor 13 under maintenance ---");
        ctrl2.setFloorUnderMaintenance(13, true);
        ctrl2.handleExternalRequest(new ElevatorRequest(13, Direction.UP));

        System.out.println("\n--- swap to FCFS, bring L1 back ---");
        ctrl2.setLiftUnderMaintenance("L1", false);
        ctrl2.setTaskSchedulingStrategy((lift, floors) -> floors);
        ctrl2.handleExternalRequest(new ElevatorRequest(2, Direction.UP));

        ctrl2.printStatus();
    }
}
