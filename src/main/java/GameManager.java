import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public final class GameManager implements ActionListener {
    private FieldButton[] gameField = new FieldButton[Constants.GAME_FIELD_HEIGHT * Constants.GAME_FIELD_WIDTH];
    private int turnCounter = 0;
    private boolean isPlayerXChance;
    private ResultChecker resultChecker;

    private GameManager() {
        GUI.setup();

        for (int i = 0; i < Constants.GAME_FIELD_WIDTH; i++) {
            for (int j = 0; j < Constants.GAME_FIELD_HEIGHT; j++) {
                int fieldIndex = i + j * Constants.GAME_FIELD_WIDTH;
                gameField[fieldIndex] = FieldButtonFactory.create(this, i, j);
                GUI.addFieldButtonToPanel(gameField[fieldIndex]);
            }
        }

        GUI.finalizeSetup();
    }

    public static GameManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private class LazyHolder {
        LazyHolder() {}

        static final GameManager INSTANCE = new GameManager();
    }

    private Players getCurrentPlayer() {
        return isPlayerXChance ? Players.X : Players.O;
    }

    private Color getCurrentPlayerColor() {
        return isPlayerXChance ? Constants.COLOR_PLAYER_X : Constants.COLOR_PLAYER_O;
    }

    public void startGame() {
        this.isPlayerXChance = true;
        this.turnCounter = 0;
        this.resultChecker = new ResultChecker();
        GUI.setMainLabelText(this.getCurrentPlayer() + " " + Constants.TURN);
    }

    public void gameOver(String s) {
        GUI.setMainLabelText(s);

        int n = GUI.showGameOverDialog(s);
        GUI.disposeFrame();
        if (n == 0) {
            GameManager gameManager = getInstance();
            gameManager.startGame();
        }
    }

    public void matchCheck() {
        ArrayList<ResultChecker.CheckResult> checkResults = resultChecker.matchCheck(this.gameField,
                new Players[] { Players.X, Players.O }, this.turnCounter);

        for (int i = 0; i < checkResults.size(); i++) {
            if (checkResults.get(i).isWin()) {
                this.onWin(checkResults.get(i));
            }
        }

        if (turnCounter == Constants.GAME_FIELD_HEIGHT * Constants.GAME_FIELD_WIDTH) {
            gameOver(Constants.MATCH_TIE);
        }
    }

    private void markWinnerRow(FieldButton[] fieldButtons) {
        Arrays.stream(fieldButtons).forEach(button -> {
            if (button != null) {
                button.markAsWinner();
            }
        });
    }

    public void onWin(ResultChecker.CheckResult checkResult) {
        this.markWinnerRow(checkResult.getWinnerFields());
        GUI.disableButtons(this.gameField);
        gameOver(checkResult.getPlayer() + " " + Constants.WINS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < gameField.length; i++) {
            if (e.getSource() == gameField[i] && gameField[i].getOccupiedBy() == null) {
                gameField[i].occupy(this.getCurrentPlayer(), this.getCurrentPlayerColor());
                isPlayerXChance = !isPlayerXChance;
                GUI.setMainLabelText(this.getCurrentPlayer() + " " + Constants.TURN);
                turnCounter++;
                matchCheck();
            }
        }
    }
};
