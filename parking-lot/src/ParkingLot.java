import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;

    private final String name;
    private final List<ParkingFloor> floors;

    private ParkingLot(String name, List<ParkingFloor> floors) {
        this.name = name;
        this.floors = floors;
    }

    public static ParkingLot getInstance(String name, List<ParkingFloor> floors) {
        if (instance == null) {
            instance = new ParkingLot(name, floors);
        }
        return instance;
    }

    public Ticket park(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailable(vehicle.getType());
            if (spot != null) {
                spot.park(vehicle);
                Ticket ticket = new Ticket(vehicle, spot, floor.getFloorNumber());
                System.out.println("Parked " + vehicle.getLicensePlate() + " → " + ticket);
                return ticket;
            }
        }
        System.out.println("No spot available for " + vehicle.getLicensePlate() + " (" + vehicle.getType() + ")");
        return null;
    }

    public void release(Ticket ticket) {
        if (ticket == null) return;
        ticket.getSpot().release();
        System.out.println("Released " + ticket.getTicketId() + " — spot " + ticket.getSpot().getSpotId() + " is now free.");
    }

    public void printStatus() {
        System.out.println("=== " + name + " ===");
        for (ParkingFloor floor : floors) {
            floor.printStatus();
        }
    }
}
