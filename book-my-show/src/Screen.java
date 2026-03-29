import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String id;
    private ScreenType screenType;
    private List<Seat> seats;
    private List<Show> shows;

    public Screen(String id, ScreenType screenType, List<Seat> seats) {
        this.id = id;
        this.screenType = screenType;
        this.seats = seats;
        this.shows = new ArrayList<>();
    }

    public void addShow(Show show) { shows.add(show); }

    public String getId()            { return id; }
    public ScreenType getScreenType(){ return screenType; }
    public List<Seat> getSeats()     { return seats; }
    public List<Show> getShows()     { return shows; }

    @Override
    public String toString() {
        return "Screen[" + id + " | " + screenType + " | " + seats.size() + " seats]";
    }
}
