import java.awt.*;
import java.awt.event.ActionListener;

public class FieldButtonFactory {
    public static FieldButton create(ActionListener actionListener, int x, int y) {
        FieldButton button = new FieldButton(x, y);
        button.setFont(new Font(Constants.FONT_NAME, Font.BOLD, Constants.FONT_SIZE));
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
}
