public interface CheckForMatchStrategy {
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
            return isWin;
        }

        public FieldButton[] getWinnerFields() {
            return winnerFields;
        }

        public Players getPlayer() {
            return player;
        }
    };

    public CheckResult check(FieldButton[] gameField, Players player);
}
