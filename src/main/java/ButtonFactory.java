import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {
    public enum ButtonType {
        FieldButton,
        ControlPanelButton,
    };

    public static JButton create(ButtonType type, String text, ActionListener actionListener, int x, int y) {
        switch (type) {
            case FieldButton -> {
                FieldButton button = new FieldButton(text, x, y);
                button.setFont(new Font(Constants.FONT_NAME, Font.BOLD, Constants.FONT_SIZE));
                button.setFocusable(false);
                button.addActionListener(actionListener);
                return button;
            }
            case ControlPanelButton -> {
                return new ControlPanelButton(text, actionListener);
            }
            default -> {
                return new JButton(text);
            }
        }
    }
}
