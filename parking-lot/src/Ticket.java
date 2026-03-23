import java.time.LocalDateTime;

public class Ticket {
    private static int counter = 1;

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final int floorNumber;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, ParkingSpot spot, int floorNumber) {
        this.ticketId = "TKT-" + counter++;
        this.vehicle = vehicle;
        this.spot = spot;
        this.floorNumber = floorNumber;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId()  { return ticketId; }
    public Vehicle getVehicle()  { return vehicle; }
    public ParkingSpot getSpot() { return spot; }
    public int getFloorNumber()  { return floorNumber; }

    @Override
    public String toString() {
        return ticketId + " | " + vehicle.getLicensePlate() + " | floor " + floorNumber + " | spot " + spot.getSpotId();
    }
}
