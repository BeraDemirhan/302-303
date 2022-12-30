package Backend.GameObjects.PowerUps;

import Backend.Player.Player;
import UI.UIUtils;

import javax.swing.*;

public class AddHealthImpl implements PowerUp {
    private int x;

    private int y;

    public AddHealthImpl(int x, int y) {
        spawnHealth(x, y);
    }

    public void spawnHealth(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public JLabel getHealth() {
        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/health.png", x, y, 50, 50);
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
