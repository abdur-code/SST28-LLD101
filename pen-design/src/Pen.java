abstract class Pen {
    protected String color;
    protected PenMechanism mechanism;
    protected InkBehavior ink;
    protected boolean isOpen;

    public Pen(String color, PenMechanism mechanism, InkBehavior ink) {
        this.color = color;
        this.mechanism = mechanism;
        this.ink = ink;
        this.isOpen = false;
    }

    public void open() {
        if (isOpen) {
            System.out.println("Already open.");
            return;
        }
        mechanism.open();
        isOpen = true;
    }

    public void close() {
        if (!isOpen) {
            System.out.println("Already closed.");
            return;
        }
        mechanism.close();
        isOpen = false;
    }

    public void write(String text) {
        if (!isOpen) {
            System.out.println("Pen is closed. Open it first.");
            return;
        }
        ink.write(text);
    }

    public void refill(String color) {
        ink.refill(color);
    }
}
