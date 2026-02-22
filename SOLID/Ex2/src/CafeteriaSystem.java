import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceFormatter formatter;
    private final BillingCalculator calculator;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, InvoiceFormatter formatter) {
        this.store = store;
        this.formatter = formatter;
        this.calculator = new BillingCalculator(menu);
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        Invoice invoice = calculator.calculate(invId, customerType, lines);
        String printable = formatter.format(invoice);
        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
