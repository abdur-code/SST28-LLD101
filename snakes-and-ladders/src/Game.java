import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Game {
    private final Board board;
    private final MoveRule rule;
    private final PriorityQueue<Player> queue;
    private int turnSeq;

    public Game(Board board, List<Player> players, MoveRule rule) {
        this.board = board;
        this.rule = rule;
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Player::getTurnIndex));
        this.turnSeq = 0;
        for (Player p : players) {
            p.setTurnIndex(turnSeq++);
            queue.add(p);
        }
    }

    public void play() {
        while (queue.size() >= 2) {
            Player current = queue.poll();
            int steps = rule.roll(current);
            applyMove(current, steps);

            if (board.isWon(current.getPosition())) {
                System.out.println(current.getName() + " wins!");
                continue;
            }

            current.setTurnIndex(turnSeq++);
            queue.add(current);
        }
        Player last = queue.poll();
        if (last != null) {
            System.out.println(last.getName() + " finishes last.");
        }
    }

    private void applyMove(Player player, int steps) {
        if (steps <= 0) {
            System.out.println(player.getName() + " stays at " + player.getPosition());
            return;
        }
        int target = player.getPosition() + steps;
        if (target > board.getSize()) {
            System.out.println(player.getName() + " needs exact roll, stays at " + player.getPosition());
            return;
        }
        int resolved = board.resolvePosition(target);
        if (resolved != target) {
            BoardCell cell = board.getCellAt(target);
            String kind = (cell instanceof Snake) ? "Snake" : "Ladder";
            System.out.println(player.getName() + " hit a " + kind + "! " + target + " → " + resolved);
        }
        player.setPosition(resolved);
        System.out.println(player.getName() + " is now at " + resolved);
    }
}
