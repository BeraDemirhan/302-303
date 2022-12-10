package Backend.GameObjects;

import javax.swing.*;

import java.awt.*;

public class Chair extends JLabel implements GameObjectIntterface {
    private int x;
    private int y;
    private Image chairImg = new ImageIcon("EscapeFromKoc/resources/chair.png").getImage();
    private JLabel chairLabel;

    public Chair(int x, int y) {
        spawnObject(x, y);
    }

    @Override
    public void spawnObject(int x, int y) {
        this.x = x;
        this.y = y;

        chairImg = chairImg.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        chairLabel = new JLabel(new ImageIcon(chairImg));
        chairLabel.setBounds(x, y, 100, 100);
    }

    @Override
    public JLabel getObjectLabel() {
        return chairLabel;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
