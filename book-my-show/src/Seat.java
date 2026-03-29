public class Seat {
    private String id;
    private int row;
    private int col;
    private SeatType seatType;
    private double basePriceRs;

    public Seat(String id, int row, int col, SeatType seatType, double basePriceRs) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
        this.basePriceRs = basePriceRs;
    }

    public String getId()          { return id; }
    public int getRow()            { return row; }
    public int getCol()            { return col; }
    public SeatType getSeatType()  { return seatType; }
    public double getBasePriceRs() { return basePriceRs; }

    @Override
    public String toString() {
        return id + "(" + seatType + ")";
    }
}
