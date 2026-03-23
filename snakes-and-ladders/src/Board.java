import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, BoardCell> cells;

    public Board(int size, Map<Integer, BoardCell> cells) {
        this.size = size;
        this.cells = cells;
    }

    public int getSize() { return size; }

    public BoardCell getCellAt(int pos) {
        return cells.get(pos);
    }

    public int resolvePosition(int pos) {
        BoardCell cell = cells.get(pos);
        return (cell == null) ? pos : cell.getEnd();
    }

    public boolean isWon(int pos) {
        return pos == size;
    }
}
