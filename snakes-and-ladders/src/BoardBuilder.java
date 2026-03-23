import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BoardBuilder {

    public static Board buildCustom(int size, List<BoardCell> cells) {
        Map<Integer, BoardCell> map = new HashMap<>();
        for (BoardCell c : cells) {
            map.put(c.getStart(), c);
        }
        return new Board(size, map);
    }

    public static Board buildRandom(int n) {
        int size = n * n;
        Map<Integer, BoardCell> map = new HashMap<>();
        Set<Integer> taken = new HashSet<>();
        Random rand = new Random();

        int snakes = 0;
        while (snakes < n) {
            int start = pick(rand, 2, size - 1);
            if (start <= n || taken.contains(start)) continue;
            int end = pick(rand, 2, start - n);
            if (end >= start) continue;
            map.put(start, new Snake(start, end));
            taken.add(start);
            snakes++;
        }

        int ladders = 0;
        while (ladders < n) {
            int start = pick(rand, 2, size - n - 1);
            if (taken.contains(start)) continue;
            int end = pick(rand, start + n, size - 1);
            if (end <= start) continue;
            map.put(start, new Ladder(start, end));
            taken.add(start);
            ladders++;
        }

        return new Board(size, map);
    }

    private static int pick(Random rand, int lo, int hi) {
        if (lo > hi) return lo;
        return rand.nextInt(hi - lo + 1) + lo;
    }
}
