package Backend.GameObjects.PowerUps;

import Backend.Player.Player;
import UI.Board;
import UI.UIUtils;

import javax.swing.*;

public class HintPowerUp implements PowerUp{
    private int x;
    private int y;

    private int keyX;
    private int keyY;



    public HintPowerUp(int x, int y){
        spawnHintPowerUp(x,y);

    }
    public void spawnHintPowerUp(int x, int y) {
        // REQUIRES: health power up
        // Modifies: the health label
        // Effects: the health appears on the room
        this.x = x;
        this.y = y;
        this.keyX = 0;
        this.keyY = 0;

    }

    public JLabel getHintPowerUP() {
        return UIUtils.createLabel("EscapeFromKoc/resources/carrot.png", x, y, 325/10, 356/10);
    }
    public JLabel getHintPowerUpKeyLocation(){
        return UIUtils.createLabel("EscapeFromKoc/resources/hintKey.png", keyX, keyY, 96/2, 54/2);

    }
    @Override
    public void activatePowerUp(Player player) {
        new Thread(){
            public void run() {
                Board.hintPowerUpUsage();
                }

        }.start();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getKeyX() {
        return keyX;
    }

    public void setKeyX(int keyX) {
        this.keyX = keyX;
    }

    public int getKeyY() {
        return keyY;
    }

    public void setKeyY(int keyY) {
        this.keyY = keyY;
    }
}
