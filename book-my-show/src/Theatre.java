import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private String id;
    private String name;
    private String city;
    private List<Screen> screens;

    public Theatre(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) { screens.add(screen); }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public String getCity()        { return city; }
    public List<Screen> getScreens(){ return screens; }

    @Override
    public String toString() {
        return "Theatre[" + name + " | " + city + " | " + screens.size() + " screen(s)]";
    }
}
