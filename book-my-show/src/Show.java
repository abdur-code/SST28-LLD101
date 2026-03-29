import java.util.LinkedHashMap;
import java.util.Map;

public class Show {
    private String id;
    private Movie movie;
    private Screen screen;
    private String startTime;
    private Map<String, ShowSeat> showSeats; // seatId -> ShowSeat

    public Show(String id, Movie movie, Screen screen, String startTime) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = new LinkedHashMap<>();

        // create one ShowSeat per physical seat on this screen
        for (Seat seat : screen.getSeats()) {
            showSeats.put(seat.getId(), new ShowSeat(seat, seat.getBasePriceRs()));
        }
    }

    public ShowSeat getShowSeat(String seatId) { return showSeats.get(seatId); }

    public String getId()                          { return id; }
    public Movie getMovie()                        { return movie; }
    public Screen getScreen()                      { return screen; }
    public String getStartTime()                   { return startTime; }
    public Map<String, ShowSeat> getShowSeats()    { return showSeats; }

    @Override
    public String toString() {
        return "Show[" + id + " | " + movie.getTitle() + " | " + startTime + " | " + screen.getId() + "]";
    }
}
