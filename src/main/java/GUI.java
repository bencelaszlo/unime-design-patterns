import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI {
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JPanel buttonPanel = new JPanel();
    private static JLabel textField  = new JLabel();

    private static void setUpFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle(Constants.TITLE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    private static void setUpTextField() {
        textField.setBackground(new Color(120, 20, 124));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText(Constants.TITLE);
        textField.setOpaque(true);
    }

    private static void setUpPanel() {
        panel.setLayout(new BorderLayout());
        panel.setBounds(0, 0, 800, 100);
    }

    private static void setUpButtonPanel() {
        buttonPanel.setLayout(new GridLayout(Constants.GAME_FIELD_HEIGHT, Constants.GAME_FIELD_WIDTH));
        buttonPanel.setBackground(new Color(150, 150, 150));
    }

    static void setup() {
        setUpFrame();
        setUpTextField();
        setUpPanel();
        setUpButtonPanel();    }

    static void finalizeSetup() {
        panel.add(GUI.textField);
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
        textField.setText(text);
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
