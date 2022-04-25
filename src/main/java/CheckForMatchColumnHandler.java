import java.util.Arrays;

public class CheckForMatchColumnHandler extends CheckForMatchBaseHandler {
    public CheckResult check(CheckResult checkResult) {
        if (checkResult.isWin()) {
            return checkResult;
        }

        for (int i = 0; i < Constants.GAME_FIELD_WIDTH; i++) {
            CheckResult columnCheckResult = checkColumnForMatch(checkResult.getGameFields(), i,
                    checkResult.getPlayer());
            if (columnCheckResult.isWin()) {
                return columnCheckResult;
            }
        }

        if (next != null) {
            return next.check(checkResult);
        }

        return new CheckResult(checkResult.getPlayer(), false, checkResult.getGameFields(), null);
    }

    private CheckResult checkColumnForMatch(FieldButton[] gameField, int columnIndex, Players player) {
        FieldButton[] buttonColumn = Arrays.stream(gameField).filter(field -> field.getPosition().getX() == columnIndex)
                .toArray(FieldButton[]::new);

        if (Arrays.stream(buttonColumn).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckResult(player, true, gameField, buttonColumn);
        }

        return new CheckResult(player, false, gameField, null);
    }
}
