// if more than 70% of seats are gone, bump the price up by 1.5x
public class DemandBasedPricing implements PricingStrategy {

    private static final double SURGE_THRESHOLD = 0.70;
    private static final double SURGE_MULTIPLIER = 1.5;

    @Override
    public double calculatePrice(ShowSeat showSeat, Show show) {
        long total = show.getShowSeats().size();
        long booked = show.getShowSeats().values().stream()
                .filter(ss -> ss.getStatus() == SeatStatus.BOOKED)
                .count();

        double occupancy = (double) booked / total;
        double base = showSeat.getSeat().getBasePriceRs();

        if (occupancy >= SURGE_THRESHOLD) {
            return base * SURGE_MULTIPLIER;
        }
        return base;
    }
}
