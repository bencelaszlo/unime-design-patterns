import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public final class GameManager implements ActionListener {
    private FieldButton[] gameField = new FieldButton[Constants.GAME_FIELD_HEIGHT * Constants.GAME_FIELD_WIDTH];
    private int turnCounter = 0;
    private boolean isPlayerXChance;
    private ResultChecker resultChecker;

    private GameManager() {
        GUIFacade.setup();

        for (int i = 0; i < Constants.GAME_FIELD_WIDTH; i++) {
            for (int j = 0; j < Constants.GAME_FIELD_HEIGHT; j++) {
                int fieldIndex = i + j * Constants.GAME_FIELD_WIDTH;
                gameField[fieldIndex] = (FieldButton) ButtonFactory.create(ButtonFactory.ButtonType.FieldButton, "",
                        this, i, j);
                GUIFacade.addFieldButtonToPanel(gameField[fieldIndex]);
            }
        }

        GUIFacade.finalizeSetup();
    }

    public static GameManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private class LazyHolder {
        LazyHolder() {
        }

        static final GameManager INSTANCE = new GameManager();
    }

    private Players getCurrentPlayer() {
        return isPlayerXChance ? Players.X : Players.O;
    }

    public void startGame() {
        isPlayerXChance = true;
        turnCounter = 0;
        resultChecker = new ResultChecker();
        GUIFacade.setMainLabelText(this.getCurrentPlayer() + " " + Constants.TURN);
    }

    public void gameOver(String s) {
        GUIFacade.setMainLabelText(s);

        int n = GUIFacade.showGameOverDialog(s);
        GUIFacade.disposeFrame();
        if (n == 0) {
            GameManager gameManager = getInstance();
            gameManager.startGame();
        }
    }

    public void matchCheck() {
        ArrayList<ResultChecker.CheckResult> checkResults = resultChecker.matchCheck(gameField,
                new Players[] { Players.X, Players.O }, turnCounter);

        for (int i = 0; i < checkResults.size(); i++) {
            if (checkResults.get(i).isWin()) {
                onWin(checkResults.get(i));
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
        markWinnerRow(checkResult.getWinnerFields());
        GUIFacade.disableButtons(gameField);
        gameOver(checkResult.getPlayer() + " " + Constants.WINS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < gameField.length; i++) {
            if (e.getSource() == gameField[i] && gameField[i].getOccupiedBy() == null) {
                gameField[i].occupy(getCurrentPlayer(), GUIFacade.getCurrentPlayerColor(isPlayerXChance));
                isPlayerXChance = !isPlayerXChance;
                GUIFacade.setMainLabelText(getCurrentPlayer() + " " + Constants.TURN);
                turnCounter++;
                matchCheck();
            }
        }
    }
};
