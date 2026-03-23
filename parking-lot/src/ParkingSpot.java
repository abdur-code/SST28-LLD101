public class ParkingSpot {
    private final String spotId;
    private final SpotSize size;
    private boolean occupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, SpotSize size) {
        this.spotId = spotId;
        this.size = size;
        this.occupied = false;
    }

    public String getSpotId()  { return spotId; }
    public SpotSize getSize()  { return size; }
    public boolean isOccupied(){ return occupied; }

    public void park(Vehicle v) {
        this.parkedVehicle = v;
        this.occupied = true;
    }

    public Vehicle release() {
        Vehicle v = this.parkedVehicle;
        this.parkedVehicle = null;
        this.occupied = false;
        return v;
    }

    @Override
    public String toString() {
        return spotId + "(" + size + ")" + (occupied ? "[" + parkedVehicle.getLicensePlate() + "]" : "[free]");
    }
}
