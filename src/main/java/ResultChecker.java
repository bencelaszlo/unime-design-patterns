import java.util.ArrayList;
import java.util.Arrays;

public class ResultChecker {
    public class CheckResult {
        private boolean isWin;
        private Players player;
        private FieldButton[] winnerFields;

        public CheckResult(Players player) {
            this.player = player;
            this.isWin = false;
            this.winnerFields = new FieldButton[0];
        }

        public CheckResult(Players player, FieldButton[] winnerFields) {
            this.isWin = winnerFields.length > 0;
            this.player = player;
            this.winnerFields = winnerFields;
        }

        public boolean isWin() {
            return this.isWin;
        }

        public FieldButton[] getWinnerFields() {
            return this.winnerFields;
        }

        public Players getPlayer() {
            return this.player;
        }
    };

    private CheckResult checkRowsForMatch(FieldButton[] gameField, Players player) {
        for (int i = 0; i < Constants.GAME_FIELD_HEIGHT; i++) {
            CheckResult rowCheckResult = checkRowForMatch(gameField, i, player);
            if (rowCheckResult.isWin()) {
                return rowCheckResult;
            }
        }

        return new CheckResult(player);
    }

    private CheckResult checkColumnsForMatch(FieldButton[] gameField, Players player) {
        for (int i = 0; i < Constants.GAME_FIELD_WIDTH; i++) {
            CheckResult columnCheckResult = checkColumnForMatch(gameField, i, player);
            if (columnCheckResult.isWin()) {
                return columnCheckResult;
            }
        }

        return new CheckResult(player);
    }

    private CheckResult checkRowForMatch(FieldButton[] gameField, int rowIndex, Players player) {
        FieldButton[] buttonRow = Arrays.stream(gameField).filter(field -> field.getPosition().getY() == rowIndex)
                .toArray(FieldButton[]::new);

        if (Arrays.stream(buttonRow).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckResult(player, buttonRow);
        }

        return new CheckResult(player);
    }

    private CheckResult checkColumnForMatch(FieldButton[] gameField, int columnIndex, Players player) {
        FieldButton[] buttonColumn = Arrays.stream(gameField).filter(field -> field.getPosition().getX() == columnIndex)
                .toArray(FieldButton[]::new);

        if (Arrays.stream(buttonColumn).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckResult(player, buttonColumn);
        }

        return new CheckResult(player);
    }

    private CheckResult checkDiagonalsForMatch(FieldButton[] gameField, Players player) {
        FieldButton[] mainDiagonal = Arrays.stream(gameField)
                .filter(field -> field.getPosition().getX() == field.getPosition().getY()).toArray(FieldButton[]::new);
        if (Arrays.stream(mainDiagonal).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckResult(player, mainDiagonal);
        }

        FieldButton[] otherDiagonal = Arrays.stream(gameField).filter(
                field -> Constants.GAME_FIELD_WIDTH - field.getPosition().getY() - field.getPosition().getX() == 1)
                .toArray(FieldButton[]::new);
        if (Arrays.stream(otherDiagonal).allMatch(field -> field.getOccupiedBy() == player)) {
            return new CheckResult(player, otherDiagonal);
        }

        return new CheckResult(player);
    }

    public ArrayList<CheckResult> matchCheck(FieldButton[] gameField, Players[] players, int turnCounter) {
        ArrayList<CheckResult> checkResults = new ArrayList<>();
        Arrays.stream(players).forEach(player -> {
            checkResults.add(this.checkRowsForMatch(gameField, player));
            checkResults.add(this.checkColumnsForMatch(gameField, player));
            checkResults.add(this.checkDiagonalsForMatch(gameField, player));
        });

        return checkResults;
    }
}
