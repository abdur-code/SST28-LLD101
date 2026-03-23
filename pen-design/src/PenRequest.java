class PenRequest {
    private final PenType penType;
    private final InkType inkType;
    private final String color;

    public PenRequest(PenType penType, InkType inkType, String color) {
        this.penType = penType;
        this.inkType = inkType;
        this.color = color;
    }

    public PenType getPenType() { return penType; }
    public InkType getInkType() { return inkType; }
    public String getColor() { return color; }
}
