import java.util.Arrays;

public class CheckForMatchMainDiagonalStrategy implements CheckForMatchStrategy {
    public CheckForMatchStrategy.CheckResult check(FieldButton[] gameField, Players player) {
        FieldButton[] mainDiagonal = Arrays.stream(gameField)
                .filter(field -> field.getPosition().getX() == field.getPosition().getY()).toArray(FieldButton[]::new);
        if (Arrays.stream(mainDiagonal).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckForMatchStrategy.CheckResult(player, mainDiagonal);
        }

        return new CheckForMatchStrategy.CheckResult(player);
    }
}
