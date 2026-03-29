public class Movie {
    private String id;
    private String title;
    private int durationMins;
    private String genre;
    private String city;

    public Movie(String id, String title, int durationMins, String genre, String city) {
        this.id = id;
        this.title = title;
        this.durationMins = durationMins;
        this.genre = genre;
        this.city = city;
    }

    public String getId()          { return id; }
    public String getTitle()       { return title; }
    public int getDurationMins()   { return durationMins; }
    public String getGenre()       { return genre; }
    public String getCity()        { return city; }

    @Override
    public String toString() {
        return "Movie[" + title + " | " + durationMins + "min | " + genre + " | " + city + "]";
    }
}
