import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        FeeComponentRegistry registry = new FeeComponentRegistry();
        registry.registerRoom(LegacyRoomTypes.SINGLE,  new SingleRoomType());
        registry.registerRoom(LegacyRoomTypes.DOUBLE,  new DoubleRoomType());
        registry.registerRoom(LegacyRoomTypes.TRIPLE,  new TripleRoomType());
        registry.registerRoom(LegacyRoomTypes.DELUXE,  new DeluxeRoomType());
        registry.registerAddOn(AddOn.MESS,    new MessAddOnComponent());
        registry.registerAddOn(AddOn.LAUNDRY, new LaundryAddOnComponent());
        registry.registerAddOn(AddOn.GYM,     new GymAddOnComponent());

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), registry);
        calc.process(req);
    }
}
