public enum LiftType {
    PASSENGER(750, 10),
    FREIGHT(2000, 5),
    SERVICE(500, 6);

    private final double maxWeightKg;
    private final int maxPassengers;

    LiftType(double maxWeightKg, int maxPassengers) {
        this.maxWeightKg = maxWeightKg;
        this.maxPassengers = maxPassengers;
    }

    public double getMaxWeightKg()  { return maxWeightKg; }
    public int getMaxPassengers()   { return maxPassengers; }
}
