public class ShowSeat {
    private Seat seat;
    private SeatStatus status;
    private double priceRs;

    public ShowSeat(Seat seat, double priceRs) {
        this.seat = seat;
        this.priceRs = priceRs;
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat getSeat()          { return seat; }
    public SeatStatus getStatus()  { return status; }
    public double getPriceRs()     { return priceRs; }

    public void setStatus(SeatStatus status) { this.status = status; }
    public void setPriceRs(double price)     { this.priceRs = price; }

    @Override
    public String toString() {
        return seat.getId() + "[" + status + " | Rs." + priceRs + "]";
    }
}
