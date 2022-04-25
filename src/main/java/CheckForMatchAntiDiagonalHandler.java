import java.util.Arrays;

public class CheckForMatchAntiDiagonalHandler extends CheckForMatchBaseHandler {
    public CheckResult check(CheckResult checkResult) {
        if (checkResult.isWin()) {
            return checkResult;
        }

        FieldButton[] antiDiagonal = Arrays.stream(checkResult.getGameFields()).filter(
                field -> Constants.GAME_FIELD_WIDTH - field.getPosition().getY() - field.getPosition().getX() == 1)
                .toArray(FieldButton[]::new);
        if (Arrays.stream(antiDiagonal).allMatch(field -> field.getOccupiedBy() == checkResult.getPlayer())) {
            return new CheckResult(checkResult.getPlayer(), true, checkResult.getGameFields(), antiDiagonal);
        }

        if (next != null) {
            return next.check(checkResult);
        }

        return new CheckResult(checkResult.getPlayer(), false, checkResult.getGameFields(), null);
    }
}
