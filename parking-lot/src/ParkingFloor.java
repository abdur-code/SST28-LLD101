import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public int getFloorNumber() { return floorNumber; }

    public ParkingSpot findAvailable(VehicleType vehicleType) {
        SpotSize required = spotSizeFor(vehicleType);
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getSize() == required) {
                return spot;
            }
        }
        return null;
    }

    private SpotSize spotSizeFor(VehicleType type) {
        switch (type) {
            case BIKE:  return SpotSize.COMPACT;
            case CAR:   return SpotSize.REGULAR;
            case TRUCK: return SpotSize.LARGE;
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
    }

    public void printStatus() {
        System.out.print("  Floor " + floorNumber + ": ");
        for (ParkingSpot s : spots) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
