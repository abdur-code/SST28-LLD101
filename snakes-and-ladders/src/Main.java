import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Board board = BoardBuilder.buildRandom(10);

        List<Player> players = Arrays.asList(
            new Player("Alice"),
            new Player("Bob"),
            new Player("Charlie")
        );

        MoveRule rule = RuleFactory.create(GameMode.EASY);
        Game game = new Game(board, players, rule);

        System.out.println("=== Snakes and Ladders ===");
        game.play();
    }
}
