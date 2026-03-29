public class BasePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(ShowSeat showSeat, Show show) {
        return showSeat.getSeat().getBasePriceRs();
    }
}
