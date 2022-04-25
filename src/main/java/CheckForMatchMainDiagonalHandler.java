import java.util.Arrays;

public class CheckForMatchMainDiagonalHandler extends CheckForMatchBaseHandler {
    public CheckResult check(CheckResult checkResult) {
        if (checkResult.isWin()) {
            return checkResult;
        }

        FieldButton[] mainDiagonal = Arrays.stream(checkResult.getGameFields())
                .filter(field -> field.getPosition().getX() == field.getPosition().getY()).toArray(FieldButton[]::new);
        if (Arrays.stream(mainDiagonal).allMatch(field -> field.getOccupiedBy() == checkResult.getPlayer())) {
            return new CheckResult(checkResult.getPlayer(), true, checkResult.getGameFields(), mainDiagonal);
        }

        if (next != null) {
            return next.check(checkResult);
        }

        return new CheckResult(checkResult.getPlayer(), false, checkResult.getGameFields(), null);
    }
}
