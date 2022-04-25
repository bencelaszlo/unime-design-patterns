public interface CheckForMatchHandler {
    public class CheckResult {
        private FieldButton[] gameFields;
        private boolean isWin;
        private Players player;
        private FieldButton[] winnerFields;

        public CheckResult(Players player, boolean isWin, FieldButton[] gameFields, FieldButton[] winnerFields) {
            this.gameFields = gameFields;
            this.isWin = isWin;
            this.player = player;
            this.winnerFields = winnerFields;
        }

        public FieldButton[] getGameFields() {
            return gameFields;
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

    public CheckResult check(CheckResult checkResult);

    public void setNext(CheckForMatchHandler handler);
}
