import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookMyShowMain {

    public static void main(String[] args) throws InterruptedException {
        BookMyShowService service = new BookMyShowService();

        Movie m1 = new Movie("M1", "Interstellar", 169, "Sci-Fi", "Mumbai");
        Movie m2 = new Movie("M2", "The Dark Knight", 152, "Action", "Mumbai");
        service.addMovie(m1);
        service.addMovie(m2);

        // rows A and B, three seats each
        List<Seat> seats = Arrays.asList(
            new Seat("A1", 0, 0, SeatType.SILVER,   150),
            new Seat("A2", 0, 1, SeatType.SILVER,   150),
            new Seat("A3", 0, 2, SeatType.GOLD,     250),
            new Seat("B1", 1, 0, SeatType.GOLD,     250),
            new Seat("B2", 1, 1, SeatType.PLATINUM, 400),
            new Seat("B3", 1, 2, SeatType.PLATINUM, 400)
        );

        Screen sc1 = new Screen("SC1", ScreenType.IMAX, seats);
        Theatre t1  = new Theatre("T1", "PVR Juhu", "Mumbai");
        t1.addScreen(sc1);
        service.addTheatre(t1);

        Show s1 = new Show("S1", m1, sc1, "7:00 PM");
        Show s2 = new Show("S2", m1, sc1, "10:00 PM");
        service.addShow(s1);
        service.addShow(s2);

        User alice = new User("U1", "Alice", "alice@mail.com");
        User bob   = new User("U2", "Bob",   "bob@mail.com");

        System.out.println("\nMovies in Mumbai:");
        service.listMoviesInCity("Mumbai").forEach(System.out::println);

        System.out.println("\nTheatres in Mumbai:");
        service.listTheatresInCity("Mumbai").forEach(System.out::println);

        System.out.println("\nShows for Interstellar:");
        service.getShowsForMovie("M1").forEach(System.out::println);

        service.displaySeatsForShow("S1");

        System.out.println("\nAlice books A1 and A2 for the 7 PM show:");
        Booking b1 = service.bookTickets("S1", alice, Arrays.asList("A1", "A2"), PaymentMode.UPI);
        service.displaySeatsForShow("S1");

        // two users racing for the same seat
        System.out.println("\nAlice and Bob both try to grab A3 at the same time:");
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done  = new CountDownLatch(2);
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() -> {
            try {
                start.await();
                service.bookTickets("S1", alice, Arrays.asList("A3"), PaymentMode.CREDIT_CARD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                done.countDown();
            }
        });

        pool.submit(() -> {
            try {
                start.await();
                service.bookTickets("S1", bob, Arrays.asList("A3"), PaymentMode.DEBIT_CARD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                done.countDown();
            }
        });

        start.countDown();
        done.await();
        pool.shutdown();

        service.displaySeatsForShow("S1");

        System.out.println("\nAlice cancels her first booking:");
        service.cancelBooking(b1.getId());
        service.displaySeatsForShow("S1");

        // book 5 of 6 seats on the 10 PM show, then turn on surge pricing
        System.out.println("\n10 PM show — filling up seats:");
        service.bookTickets("S2", alice, Arrays.asList("A1", "A2", "A3", "B1", "B2"), PaymentMode.UPI);
        service.setPricingStrategy(new DemandBasedPricing());
        service.applyPricing("S2");
        service.displaySeatsForShow("S2");
    }
}
