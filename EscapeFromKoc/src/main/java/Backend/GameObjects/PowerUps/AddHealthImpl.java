package Backend.GameObjects.PowerUps;

import Backend.Player.Player;

import javax.swing.*;
import java.awt.*;

public class AddHealthImpl implements PowerUp {
    private int x;

    private int y;

    private Image healthImg = new ImageIcon("EscapeFromKoc/resources/heart.png").getImage();

    private JLabel healthLabel;

    public AddHealthImpl(int x, int y) {
        spawnHealth(x, y);
    }

    public void spawnHealth(int x, int y) {
        this.x = x;
        this.y = y;

        healthImg = healthImg.getScaledInstance(54, 54, Image.SCALE_SMOOTH);
        healthLabel = new JLabel(new ImageIcon(healthImg));
        healthLabel.setBounds(x, y, 100, 100);
    }

    public JLabel getHealth() {
        return healthLabel;
    }

    @Override
    public void activatePowerUp(Player player) {
        player.addHealth(1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
