public class RuleFactory {

    public static MoveRule create(GameMode mode) {
        if (mode == GameMode.HARD) {
            return new SafeRoll();
        }
        return new StandardRoll();
    }
}
