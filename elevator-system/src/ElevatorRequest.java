public class ElevatorRequest {
    private int fromFloor;
    private Direction direction;

    public ElevatorRequest(int fromFloor, Direction direction) {
        this.fromFloor = fromFloor;
        this.direction = direction;
    }

    public int getFromFloor()       { return fromFloor; }
    public Direction getDirection() { return direction; }

    @Override
    public String toString() {
        return "Request from floor " + fromFloor + " going " + direction;
    }
}
