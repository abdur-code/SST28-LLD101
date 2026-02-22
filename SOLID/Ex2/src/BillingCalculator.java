import java.util.*;

public class BillingCalculator {
    private final Map<String, MenuItem> menu;

    public BillingCalculator(Map<String, MenuItem> menu) {
        this.menu = menu;
    }

    public Invoice calculate(String invId, String customerType, List<OrderLine> lines) {
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        double subtotal = 0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceLine(item.name, l.qty, lineTotal));
        }
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;
        return new Invoice(invId, invoiceLines, subtotal, taxPct, tax, discount, total);
    }
}
