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
        return UIUtils.createLabel("EscapeFromKoc/resources/extraTime.png", x, y, 96/2, 54/2);
    }

    @Override
    public void activatePowerUp(Player player) {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        Board.addTime();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
