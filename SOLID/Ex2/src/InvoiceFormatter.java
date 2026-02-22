public class InvoiceFormatter {
    public String format(Invoice invoice) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(invoice.id).append("\n");
        for (InvoiceLine line : invoice.lines) {
            sb.append(String.format("- %s x%d = %.2f\n", line.itemName, line.qty, line.lineTotal));
        }
        sb.append(String.format("Subtotal: %.2f\n", invoice.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", invoice.taxPct, invoice.tax));
        sb.append(String.format("Discount: -%.2f\n", invoice.discount));
        sb.append(String.format("TOTAL: %.2f\n", invoice.total));
        return sb.toString();
    }
}
