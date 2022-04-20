import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI {
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JPanel buttonPanel = new JPanel();
    private static JLabel mainLabel = new JLabel();

    private static void setUpFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.getContentPane().setBackground(Constants.COLOR_CONTENT_PANE_BACKGROUND);
        frame.setTitle(Constants.TITLE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    private static void setUpMainLabel() {
        mainLabel.setBackground(Constants.COLOR_MAIN_LABEL_BACKGROUND);
        mainLabel.setForeground(Constants.COLOR_MAIN_LABEL_FOREGROUND);
        mainLabel.setFont(new Font(Constants.FONT_NAME, Font.BOLD, Constants.FONT_SIZE_MAIN_LABEL));
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setText(Constants.TITLE);
        mainLabel.setOpaque(true);
        panel.add(GUI.mainLabel);
    }

    private static void setUpPanel() {
        panel.setLayout(new BorderLayout());
        panel.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.PANEL_HEIGHT);
    }

    private static void setUpButtonPanel() {
        buttonPanel.setLayout(new GridLayout(Constants.GAME_FIELD_HEIGHT, Constants.GAME_FIELD_WIDTH));
        buttonPanel.setBackground(Constants.COLOR_BACKGROUND);
    }

    static void setup() {
        setUpFrame();
        setUpPanel();
        setUpMainLabel();
        setUpButtonPanel();
    }

    static void finalizeSetup() {
        addComponentToFrame(GUI.panel, BorderLayout.NORTH);
        addComponentToFrame(GUI.buttonPanel);
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

        return JOptionPane.showOptionDialog(GUI.frame,
                Constants.GAME_OVER + "\n" + endGameScenario,
                Constants.GAME_OVER,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    static void disableButtons(JButton[] gameField) {
        Arrays.stream(gameField).forEach(field -> field.setEnabled((false)));
    }

    static void disposeFrame() {
        frame.dispose();
    }
}
