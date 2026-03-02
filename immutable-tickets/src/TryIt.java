import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket original = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + original);

        IncidentTicket assigned = service.assign(original, "agent@example.com");
        System.out.println("\nAfter assign (new instance): " + assigned);
        System.out.println("Original unchanged:          " + original);

        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter escalation (new instance): " + escalated);
        System.out.println("Assigned unchanged:              " + assigned);

        try {
            List<String> tags = escalated.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation blocked: " + e.getClass().getSimpleName());
        }
        System.out.println("Escalated still intact: " + escalated);

        System.out.println("\nSame object? original == assigned: " + (original == assigned));
        System.out.println("Same object? assigned == escalated: " + (assigned == escalated));
    }
}
