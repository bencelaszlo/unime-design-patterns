import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI {
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JPanel buttonPanel = new JPanel();
    private static JLabel mainLabel = new JLabel();
    private static JButton undoButton = new JButton();
    private static JButton redoButton = new JButton();

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
        mainLabel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH / 3, Constants.PANEL_HEIGHT));
        panel.add(GUI.mainLabel, BorderLayout.CENTER);
    }

    public static void setUpUndoButton() {
        undoButton = ButtonFactory.create(ButtonFactory.ButtonType.ControlPanelButton, "<- Undo", e -> undo(), 0, 0);
        panel.add(GUI.undoButton, BorderLayout.LINE_START);
    }

    public static void undo() {
        System.out.println("UNDO");
    }

    public static void redo() {
        System.out.println("REDO");
    }
    
    public static void setUpRedoButton() {
        redoButton = ButtonFactory.create(ButtonFactory.ButtonType.ControlPanelButton, "Redo ->", e -> redo(), 0, 0);
        panel.add(GUI.redoButton, BorderLayout.LINE_END);
    }

    private static void setUpPanel() {
        panel.setLayout(new GridLayout(1, 3));
        panel.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.PANEL_HEIGHT);
    }

    private static void setUpButtonPanel() {
        buttonPanel.setLayout(new GridLayout(Constants.GAME_FIELD_HEIGHT, Constants.GAME_FIELD_WIDTH));
        buttonPanel.setBackground(Constants.COLOR_BACKGROUND);
    }

    static void setup() {
        setUpFrame();
        setUpPanel();
        setUpUndoButton();
        setUpMainLabel();
        setUpRedoButton();
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
                options[0]);
    }

    static void disableButtons(JButton[] gameField) {
        Arrays.stream(gameField).forEach(field -> field.setEnabled((false)));
    }

    static void disposeFrame() {
        frame.dispose();
    }
}
