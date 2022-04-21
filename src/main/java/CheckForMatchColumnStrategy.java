import java.util.Arrays;

public class CheckForMatchColumnStrategy implements CheckForMatchStrategy {
    public CheckForMatchStrategy.CheckResult check(FieldButton[] gameField, Players player) {
        for (int i = 0; i < Constants.GAME_FIELD_WIDTH; i++) {
            CheckForMatchStrategy.CheckResult columnCheckResult = checkColumnForMatch(gameField, i, player);
            if (columnCheckResult.isWin()) {
                return columnCheckResult;
            }
        }

        return new CheckForMatchStrategy.CheckResult(player);
    }

    private CheckForMatchStrategy.CheckResult checkColumnForMatch(FieldButton[] gameField, int columnIndex, Players player) {
        FieldButton[] buttonColumn = Arrays.stream(gameField).filter(field -> field.getPosition().getX() == columnIndex)
                .toArray(FieldButton[]::new);

        if (Arrays.stream(buttonColumn).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckForMatchStrategy.CheckResult(player, buttonColumn);
        }

        return new CheckForMatchStrategy.CheckResult(player);
    }
}
