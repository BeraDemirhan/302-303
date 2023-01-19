package Backend.GameObjects.PowerUps;

import Backend.GameControler;
import Backend.Player.Player;
import UI.Board;
import UI.UIUtils;

import javax.swing.*;

public class ExtraTime implements PowerUp{
    private int x;

    private int y;

    public ExtraTime(int x, int y) {
        spawnHealth(x, y);
    }

    public void spawnHealth(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public JLabel getExtraTimeLabel() {
        return UIUtils.createLabel("EscapeFromKoc/resources/key.png", x, y, 50, 50);
    }

    @Override
    public void activatePowerUp(Player player) {
        GameControler.setLevelTime(GameControler.getLevelTime() + 5);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
