public class Player {
    private final String name;
    private int position;
    private int turnIndex;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.turnIndex = 0;
    }

    public String getName()        { return name; }
    public int getPosition()       { return position; }
    public void setPosition(int p) { this.position = p; }
    public int getTurnIndex()      { return turnIndex; }
    public void setTurnIndex(int t){ this.turnIndex = t; }
}
