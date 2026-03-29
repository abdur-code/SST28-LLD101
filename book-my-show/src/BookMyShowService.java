import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookMyShowService {
    private List<Movie> movies;
    private List<Theatre> theatres;
    private List<Show> shows;
    private List<Booking> bookings;
    private PricingStrategy pricingStrategy;

    public BookMyShowService() {
        this.movies = new ArrayList<>();
        this.theatres = new ArrayList<>();
        this.shows = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.pricingStrategy = new BasePricingStrategy();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Added: " + movie);
    }

    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
        System.out.println("Added: " + theatre);
    }

    public void addShow(Show show) {
        shows.add(show);
        show.getScreen().addShow(show);
        System.out.println("Added: " + show);
    }

    public void deleteShow(String showId) {
        shows.removeIf(s -> s.getId().equals(showId));
        System.out.println("Deleted show: " + showId);
    }

    public List<Movie> listMoviesInCity(String city) {
        return movies.stream()
                .filter(m -> m.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Theatre> listTheatresInCity(String city) {
        return theatres.stream()
                .filter(t -> t.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Show> getShowsForMovie(String movieId) {
        return shows.stream()
                .filter(s -> s.getMovie().getId().equals(movieId))
                .collect(Collectors.toList());
    }

    public void displaySeatsForShow(String showId) {
        Show show = getShow(showId);
        if (show == null) { System.out.println("Show not found."); return; }

        System.out.println("\nSeats for " + show + ":");
        for (ShowSeat ss : show.getShowSeats().values()) {
            System.out.println("  " + ss);
        }
    }

    public Booking bookTickets(String showId, User user, List<String> seatIds, PaymentMode mode) {
        Show show = getShow(showId);
        if (show == null) { System.out.println("Show not found."); return null; }

        List<ShowSeat> toBook = new ArrayList<>();

        // always lock seats in a consistent order to avoid deadlock
        List<String> sortedSeatIds = new ArrayList<>(seatIds);
        java.util.Collections.sort(sortedSeatIds);

        for (String seatId : sortedSeatIds) {
            ShowSeat ss = show.getShowSeat(seatId);
            if (ss == null) {
                System.out.println("Seat " + seatId + " does not exist in this show.");
                releaseSeats(toBook);
                return null;
            }
            synchronized (ss) {
                if (ss.getStatus() != SeatStatus.AVAILABLE) {
                    System.out.println("Seat " + seatId + " is not available. Booking failed for " + user.getName());
                    releaseSeats(toBook);
                    return null;
                }
                ss.setStatus(SeatStatus.LOCKED);
                toBook.add(ss);
            }
        }

        double total = toBook.stream().mapToDouble(ShowSeat::getPriceRs).sum();
        for (ShowSeat ss : toBook) {
            ss.setStatus(SeatStatus.BOOKED);
        }

        Booking booking = new Booking(
            "BK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
            user, show, toBook, mode, total
        );
        bookings.add(booking);
        System.out.println("Booking confirmed: " + booking);
        return booking;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst().orElse(null);

        if (booking == null) { System.out.println("Booking not found."); return; }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("Booking already cancelled."); return;
        }

        for (ShowSeat ss : booking.getBookedSeats()) {
            synchronized (ss) {
                ss.setStatus(SeatStatus.AVAILABLE);
            }
        }
        booking.cancel();
        System.out.println("Cancelled: " + booking);
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
        System.out.println("Pricing strategy set to: " + strategy.getClass().getSimpleName());
    }

    public void applyPricing(String showId) {
        Show show = getShow(showId);
        if (show == null) return;

        for (ShowSeat ss : show.getShowSeats().values()) {
            if (ss.getStatus() == SeatStatus.AVAILABLE) {
                ss.setPriceRs(pricingStrategy.calculatePrice(ss, show));
            }
        }
        System.out.println("Pricing updated for show: " + showId);
    }

    private void releaseSeats(List<ShowSeat> seats) {
        for (ShowSeat ss : seats) {
            synchronized (ss) {
                ss.setStatus(SeatStatus.AVAILABLE);
            }
        }
    }

    private Show getShow(String showId) {
        return shows.stream().filter(s -> s.getId().equals(showId)).findFirst().orElse(null);
    }
}
