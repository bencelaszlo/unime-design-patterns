import java.util.Arrays;

public class CheckForMatchAntiDiagonalStrategy implements CheckForMatchStrategy {
    public CheckForMatchStrategy.CheckResult check(FieldButton[] gameField, Players player) {
        FieldButton[] antiDiagonal = Arrays.stream(gameField).filter(
                        field -> Constants.GAME_FIELD_WIDTH - field.getPosition().getY() - field.getPosition().getX() == 1)
                .toArray(FieldButton[]::new);
        if (Arrays.stream(antiDiagonal).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckForMatchStrategy.CheckResult(player, antiDiagonal);
        }

        return new CheckForMatchStrategy.CheckResult(player);
    }
}
