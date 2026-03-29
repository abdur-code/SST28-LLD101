import java.util.List;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<ShowSeat> bookedSeats;
    private PaymentMode paymentMode;
    private BookingStatus status;
    private double totalAmountRs;

    public Booking(String id, User user, Show show, List<ShowSeat> bookedSeats,
                   PaymentMode paymentMode, double totalAmountRs) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.paymentMode = paymentMode;
        this.totalAmountRs = totalAmountRs;
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() { this.status = BookingStatus.CANCELLED; }

    public String getId()              { return id; }
    public User getUser()              { return user; }
    public Show getShow()              { return show; }
    public List<ShowSeat> getBookedSeats() { return bookedSeats; }
    public PaymentMode getPaymentMode(){ return paymentMode; }
    public BookingStatus getStatus()   { return status; }
    public double getTotalAmountRs()   { return totalAmountRs; }

    @Override
    public String toString() {
        return "Booking[" + id + " | " + user.getName() + " | " + show.getMovie().getTitle()
            + " | seats=" + bookedSeats.size() + " | Rs." + totalAmountRs + " | " + status + "]";
    }
}
