import javax.swing.*;
import java.awt.*;

class FieldButton extends JButton {
    private Position2D position;
    private Players occupiedBy;

    public FieldButton(String text, int x, int y) {
        this.setText(text);
        this.position = new Position2D(x, y);
        this.occupiedBy = null;
    }

    public Position2D getPosition() {
        return this.position;
    }

    private void setOccupiedBy(Players player) {
        this.occupiedBy = player;
    }

    public Players getOccupiedBy() {
        return this.occupiedBy;
    }

    public void occupy(Players currentPlayer, Color currentPlayerColor) {
        this.setForeground(currentPlayerColor);
        this.setText(currentPlayer.toString());
        this.setOccupiedBy(currentPlayer);
    }

    public void markAsWinner() {
        this.setBackground(Constants.COLOR_WINNER_ROW);
    }
};
