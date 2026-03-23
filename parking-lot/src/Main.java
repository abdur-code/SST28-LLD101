import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Build floor 1: 2 compact, 2 regular, 1 large
        List<ParkingSpot> floor1Spots = Arrays.asList(
            new ParkingSpot("1-C1", SpotSize.COMPACT),
            new ParkingSpot("1-C2", SpotSize.COMPACT),
            new ParkingSpot("1-R1", SpotSize.REGULAR),
            new ParkingSpot("1-R2", SpotSize.REGULAR),
            new ParkingSpot("1-L1", SpotSize.LARGE)
        );

        // Build floor 2: 1 compact, 2 regular, 1 large
        List<ParkingSpot> floor2Spots = Arrays.asList(
            new ParkingSpot("2-C1", SpotSize.COMPACT),
            new ParkingSpot("2-R1", SpotSize.REGULAR),
            new ParkingSpot("2-R2", SpotSize.REGULAR),
            new ParkingSpot("2-L1", SpotSize.LARGE)
        );

        List<ParkingFloor> floors = Arrays.asList(
            new ParkingFloor(1, floor1Spots),
            new ParkingFloor(2, floor2Spots)
        );

        ParkingLot lot = ParkingLot.getInstance("Campus Parking", floors);

        lot.printStatus();
        System.out.println();

        Ticket t1 = lot.park(new Car("MH-01-AB-1234"));
        Ticket t2 = lot.park(new Bike("DL-05-XY-9900"));
        Ticket t3 = lot.park(new Truck("KA-03-ZZ-0001"));
        Ticket t4 = lot.park(new Car("UP-16-BC-4567"));
        Ticket t5 = lot.park(new Car("TN-09-CD-7890"));

        System.out.println();
        lot.printStatus();
        System.out.println();

        lot.release(t1);
        lot.park(new Car("GJ-01-EF-1111"));

        System.out.println();
        lot.printStatus();
    }
}
