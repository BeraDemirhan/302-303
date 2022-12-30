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
        // REQUIRES: health power up
        // Modifies: the health label
        // Effects: the health appears on the room
        this.x = x;
        this.y = y;

    }

    public JLabel getHealth() {
        // REQUIRES: Health power up
        // Modifies: health existence
        // Effects: the player get health
        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/health.png", x, y, 50, 50);
    }

    @Override
    public void activatePowerUp(Player player) {
        // REQUIRES: Health power up and Player
        // Modifies: The health activate
        // Effects: the player get health and the health number increase
        player.addHealth(1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
