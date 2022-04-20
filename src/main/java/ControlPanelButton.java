import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanelButton extends JButton {
    public ControlPanelButton(String text, ActionListener actionListener) {
        this.setFont(new Font(Constants.FONT_NAME, Font.BOLD, Constants.FONT_SIZE / 4));
        this.setText(text);
        this.setPreferredSize(new Dimension(Constants.FRAME_WIDTH / 3, Constants.PANEL_HEIGHT));
        this.setBackground(Constants.COLOR_MAIN_LABEL_BACKGROUND);
        this.setForeground(Constants.COLOR_MAIN_LABEL_FOREGROUND);
        this.addActionListener(actionListener);
    }
}
