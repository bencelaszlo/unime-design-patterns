import java.util.Arrays;

public class CheckForMatchRowHandler extends CheckForMatchBaseHandler {
    public CheckResult check(CheckResult checkResult) {
        if (checkResult.isWin()) {
            return checkResult;
        }

        for (int i = 0; i < Constants.GAME_FIELD_HEIGHT; i++) {
            CheckResult rowCheckResult = checkRowForMatch(checkResult.getGameFields(), i, checkResult.getPlayer());
            if (rowCheckResult.isWin()) {
                return rowCheckResult;
            }
        }

        if (next != null) {
            return next.check(checkResult);
        }

        return new CheckResult(checkResult.getPlayer(), false, checkResult.getGameFields(), null);
    }

    private CheckResult checkRowForMatch(FieldButton[] gameField, int rowIndex, Players player) {
        FieldButton[] buttonRow = Arrays.stream(gameField).filter(field -> field.getPosition().getY() == rowIndex)
                .toArray(FieldButton[]::new);

        if (Arrays.stream(buttonRow).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckResult(player, true, gameField, buttonRow);
        }

        return new CheckResult(player, false, gameField, null);
    }
}
