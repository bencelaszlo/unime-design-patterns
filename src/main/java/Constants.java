import java.awt.*;

public class Constants {
    public static final String TITLE = "Tic Tac Toe";
    public static final String RESTART = "Restart";
    public static final String GAME_OVER = "Game Over";
    public static final String MATCH_TIE = "Match Tie";
    public static final String EXIT = "Exit";
    public static final String WINS = "Wins";
    public static final String TURN = "turn";
    public static final String FONT_NAME = "Ink Free";
    public static final int FONT_SIZE = 120;

    public static final Color COLOR_PLAYER_X = new Color(255, 0, 0);
    public static final Color COLOR_PLAYER_O = new Color(0, 0 , 255);
    public static final Color COLOR_WINNER_ROW = new Color(0, 0, 0);

    public static final int GAME_FIELD_WIDTH = 3;
    public static final int GAME_FIELD_HEIGHT = 3;
    public static final Object[] GAME_OVER_DIALOG_OPTIONS = {
            Constants.RESTART,
            Constants.EXIT
    };
}
