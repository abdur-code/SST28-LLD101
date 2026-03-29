public class Button {
    private Direction direction;
    private boolean enabled;

    public Button(Direction direction) {
        this.direction = direction;
        this.enabled = true;
    }

    public void press() {
        if (!enabled) {
            System.out.println(direction + " button is disabled (floor under maintenance)");
        }
    }

    public void disable() { this.enabled = false; }
    public void enable()  { this.enabled = true; }

    public boolean isEnabled()      { return enabled; }
    public Direction getDirection() { return direction; }
}
