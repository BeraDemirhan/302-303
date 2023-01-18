package Backend.GameObjects.PowerUps;

import Backend.Player.Player;
import UI.HintPowerUpUI.HintPowerUpHighlight;
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
        // REQUIRES: Health power up
        // Modifies: health existence
        // Effects: the player get health
        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/hintKey.png", x, y, 50, 50);
    }
    @Override
    public void activatePowerUp(Player player) {

    }
    public void paintForHint(){
        HintPowerUpHighlight rect =new HintPowerUpHighlight();
        JFrame frame = new JFrame("Rectangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(rect);
        frame.setSize(40, 40);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
