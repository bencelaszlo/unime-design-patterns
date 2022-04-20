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
    public static final int FONT_SIZE_MAIN_LABEL = 75;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 800;
    public static final int PANEL_HEIGHT = 100;

    public static final Color COLOR_PLAYER_X = new Color(255, 100, 100);
    public static final Color COLOR_PLAYER_O = new Color(100, 100, 255);
    public static final Color COLOR_WINNER_ROW = new Color(0, 0, 0);
    public static final Color COLOR_BACKGROUND = new Color(200, 200, 225);
    public static final Color COLOR_MAIN_LABEL_BACKGROUND = new Color(0, 0, 25);
    public static final Color COLOR_MAIN_LABEL_FOREGROUND = new Color(225, 225, 255);
    public static final Color COLOR_CONTENT_PANE_BACKGROUND = new Color(0, 0, 25);

    public static final int GAME_FIELD_WIDTH = 3;
    public static final int GAME_FIELD_HEIGHT = 3;
    public static final Object[] GAME_OVER_DIALOG_OPTIONS = {
            Constants.RESTART,
            Constants.EXIT
    };
}
