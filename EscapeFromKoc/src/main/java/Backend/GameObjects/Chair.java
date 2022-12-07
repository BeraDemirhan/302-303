package Backend.GameObjects;

import javax.swing.*;

import java.awt.*;

public class Chair extends JLabel {
    private int x;
    private int y;
    private Image chairImg = new ImageIcon("EscapeFromKoc/resources/chair.png").getImage();
    private JLabel chairLabel;

    public Chair(int x, int y) {
        spawnChair(x, y);
    }

    public void spawnChair(int x, int y) {
        this.x = x;
        this.y = y;

        chairImg = chairImg.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        chairLabel = new JLabel(new ImageIcon(chairImg));
        chairLabel.setBounds(x, y, 100, 100);
    }

    public JLabel getChair() {
        return chairLabel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
