public class CellFactory {

    public static BoardCell create(CellType type, int start, int end) {
        switch (type) {
            case SNAKE:  return new Snake(start, end);
            case LADDER: return new Ladder(start, end);
            default: throw new IllegalArgumentException("Unknown cell type: " + type);
        }
    }
}
