import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUIFacade {
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JButton restartButton = new JButton();
    private static JLabel mainLabel = new JLabel();
    private static JPanel buttonPanel = new JPanel();

    static Color getCurrentPlayerColor(boolean isPlayerXChance) {
        return isPlayerXChance ? Constants.COLOR_PLAYER_X : Constants.COLOR_PLAYER_O;
    }

    private static void setUpFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.getContentPane().setBackground(Constants.COLOR_CONTENT_PANE_BACKGROUND);
        frame.setTitle(Constants.TITLE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    private static void setUpMainLabel() {
        mainLabel = new JLabel();
        mainLabel.setBackground(Constants.COLOR_MAIN_LABEL_BACKGROUND);
        mainLabel.setForeground(Constants.COLOR_MAIN_LABEL_FOREGROUND);
        mainLabel.setFont(new Font(Constants.FONT_NAME, Font.BOLD, Constants.FONT_SIZE_MAIN_LABEL));
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setText(Constants.TITLE);
        mainLabel.setOpaque(true);
        mainLabel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH / 2, Constants.PANEL_HEIGHT));
        panel.add(GUIFacade.mainLabel);
    }


    public static void setUpRestartButton(ActionListener actionListener) {
        restartButton = ButtonFactory.create(ButtonFactory.ButtonType.ControlPanelButton, "Restart Game", actionListener, 0, 0);
        panel.add(GUIFacade.restartButton);
    }

    private static void setUpPanel(ActionListener restartActionListener) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.PANEL_HEIGHT);
        setUpRestartButton(restartActionListener);
        addComponentToFrame(panel, BorderLayout.NORTH);
    }

    private static void setUpButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(Constants.GAME_FIELD_HEIGHT, Constants.GAME_FIELD_WIDTH));
        buttonPanel.setBackground(Constants.COLOR_BACKGROUND);
        addComponentToFrame(buttonPanel);
    }

    static void setup(ActionListener restartActionListener) {
        setUpFrame();
        setUpPanel(restartActionListener);
        setUpMainLabel();
        setUpButtonPanel();
    }

    static void addFieldButtonToPanel(JButton button) {
        buttonPanel.add(button);
    }

    static void addComponentToFrame(JComponent component) {
        frame.add(component);
    }

    static void addComponentToFrame(JComponent component, String constraints) {
        frame.add(component, constraints);
    }

    static void setMainLabelText(String text) {
        mainLabel.setText(text);
    }

    static int showGameOverDialog(String endGameScenario) {
        Object[] options = Constants.GAME_OVER_DIALOG_OPTIONS;

        return JOptionPane.showOptionDialog(GUIFacade.frame,
                Constants.GAME_OVER + "\n" + endGameScenario,
                Constants.GAME_OVER,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    static void markWinnerRow(FieldButton[] fieldButtons) {
        Arrays.stream(fieldButtons).forEach(button -> {
            if (button != null) {
                button.markAsWinner();
            }
        });
    }

    static void disableButtons(JButton[] gameField) {
        Arrays.stream(gameField).forEach(field -> field.setEnabled((false)));
    }

    static void disposeGUI() {
        frame.dispose();
    }
}
