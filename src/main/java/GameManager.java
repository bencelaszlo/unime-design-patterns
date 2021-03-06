import java.awt.event.*;

public final class GameManager implements ActionListener {
    private FieldButton[] gameField = new FieldButton[Constants.GAME_FIELD_HEIGHT * Constants.GAME_FIELD_WIDTH];
    private int turnCounter = 0;
    private boolean isPlayerXChance;

    private GameManager() {
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
        GUIFacade.setup(e -> startGame());

        for (int i = 0; i < Constants.GAME_FIELD_WIDTH; i++) {
            for (int j = 0; j < Constants.GAME_FIELD_HEIGHT; j++) {
                int fieldIndex = i + j * Constants.GAME_FIELD_WIDTH;
                gameField[fieldIndex] = (FieldButton) ButtonFactory.create(ButtonFactory.ButtonType.FieldButton, "",
                        this, i, j);
                GUIFacade.addFieldButtonToPanel(gameField[fieldIndex]);
            }
        }

        isPlayerXChance = true;
        turnCounter = 0;
        GUIFacade.setMainLabelText(this.getCurrentPlayer() + " " + Constants.TURN);
    }

    public void gameOver(String s) {
        GUIFacade.setMainLabelText(s);

        int n = GUIFacade.showGameOverDialog(s);
        GUIFacade.disposeGUI();
        if (n == 0) {
            startGame();
        }
    }

    public void matchCheck() {
        CheckForMatchHandler rowCheckHandler = new CheckForMatchRowHandler();
        CheckForMatchHandler columnCheckHandler = new CheckForMatchColumnHandler();
        CheckForMatchHandler mainDiagonalCheckHandler = new CheckForMatchMainDiagonalHandler();
        CheckForMatchHandler antiDiagonalCheckHandler = new CheckForMatchAntiDiagonalHandler();
        rowCheckHandler.setNext(columnCheckHandler);
        columnCheckHandler.setNext(mainDiagonalCheckHandler);
        mainDiagonalCheckHandler.setNext(antiDiagonalCheckHandler);

        for (int playerIndex = 0; playerIndex < Players.values().length; playerIndex++) {
            CheckForMatchHandler.CheckResult matchCheck = rowCheckHandler.check(
                    new CheckForMatchHandler.CheckResult(Players.values()[playerIndex], false, this.gameField, null));
            if (matchCheck.isWin()) {
                onWin(matchCheck);
            }
        }

        if (turnCounter == Constants.GAME_FIELD_HEIGHT * Constants.GAME_FIELD_WIDTH) {
            gameOver(Constants.MATCH_TIE);
        }
    }

    public void onWin(CheckForMatchHandler.CheckResult checkResult) {
        GUIFacade.markWinnerRow(checkResult.getWinnerFields());
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
