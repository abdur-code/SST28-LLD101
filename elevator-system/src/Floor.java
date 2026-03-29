public class Floor {
    private int floorNumber;
    private Button upButton;
    private Button downButton;
    private boolean underMaintenance;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.upButton = new Button(Direction.UP);
        this.downButton = new Button(Direction.DOWN);
        this.underMaintenance = false;
    }

    public void setUnderMaintenance(boolean flag) {
        underMaintenance = flag;
        if (flag) {
            upButton.disable();
            downButton.disable();
            System.out.println("Floor " + floorNumber + " is under maintenance. Buttons disabled.");
        } else {
            upButton.enable();
            downButton.enable();
            System.out.println("Floor " + floorNumber + " is back in service.");
        }
    }

    public int getFloorNumber()      { return floorNumber; }
    public Button getUpButton()      { return upButton; }
    public Button getDownButton()    { return downButton; }
    public boolean isUnderMaintenance() { return underMaintenance; }
}
