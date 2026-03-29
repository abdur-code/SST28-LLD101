public class ElevatorMain {
    public static void main(String[] args) {

        Building building = new Building("TechPark Tower", 20, 2);
        ElevatorController ctrl = building.getController();
        ctrl.printStatus();

        System.out.println("\n--- someone on floor 5 presses UP ---");
        ctrl.handleExternalRequest(new ElevatorRequest(5, Direction.UP));

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

        Building b2 = new Building("Test Block", 10, 2);
        ElevatorController ctrl2 = b2.getController();

        System.out.println("\n--- L1 goes under maintenance, request on floor 3 ---");
        ctrl2.setLiftUnderMaintenance("L1", true);
        ctrl2.handleExternalRequest(new ElevatorRequest(3, Direction.DOWN));

        System.out.println("\n--- floor 7 under maintenance ---");
        ctrl2.setFloorUnderMaintenance(7, true);
        ctrl2.handleExternalRequest(new ElevatorRequest(7, Direction.UP));

        System.out.println("\n--- swap to FCFS, bring L1 back ---");
        ctrl2.setLiftUnderMaintenance("L1", false);
        ctrl2.setTaskSchedulingStrategy((lift, floors) -> floors);
        ctrl2.handleExternalRequest(new ElevatorRequest(2, Direction.UP));

        ctrl2.printStatus();
    }
}
