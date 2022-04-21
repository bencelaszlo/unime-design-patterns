import java.util.Arrays;

public class CheckForMatchRowStrategy implements CheckForMatchStrategy {
    public CheckForMatchStrategy.CheckResult check(FieldButton[] gameField, Players player) {
        for (int i = 0; i < Constants.GAME_FIELD_HEIGHT; i++) {
            CheckForMatchStrategy.CheckResult rowCheckResult = checkRowForMatch(gameField, i, player);
            if (rowCheckResult.isWin()) {
                return rowCheckResult;
            }
        }

        return new CheckForMatchStrategy.CheckResult(player);
    }

    private CheckForMatchStrategy.CheckResult checkRowForMatch(FieldButton[] gameField, int rowIndex, Players player) {
        FieldButton[] buttonRow = Arrays.stream(gameField).filter(field -> field.getPosition().getY() == rowIndex)
                .toArray(FieldButton[]::new);

        if (Arrays.stream(buttonRow).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckForMatchStrategy.CheckResult(player, buttonRow);
        }

        return new CheckForMatchStrategy.CheckResult(player);
    }
}
