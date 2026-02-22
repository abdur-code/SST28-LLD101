import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final FeeComponentRegistry registry;

    public HostelFeeCalculator(FakeBookingRepo repo, FeeComponentRegistry registry) {
        this.repo = repo;
        this.registry = registry;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);
        ReceiptPrinter.print(req, monthly, deposit);
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = registry.forRoom(req.roomType).monthlyContribution();
        for (AddOn a : req.addOns) total = total.plus(registry.forAddOn(a).monthlyContribution());
        return total;
    }
}
