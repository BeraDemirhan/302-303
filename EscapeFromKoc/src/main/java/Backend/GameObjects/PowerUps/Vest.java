package Backend.GameObjects.PowerUps;

import Backend.Player.Player;
import UI.Board;
import UI.UIUtils;

import javax.swing.*;
public class Vest implements PowerUp {

    private int x;
    private int y;

    public Vest(int x, int y){
        spawnPowerUpVest(x,y);
    }

    public void spawnPowerUpVest(int x, int y){
        this.x = x;
        this.y = y;

    }

    public JLabel getPowerUpVestLabel() {
        return UIUtils.createLabel("EscapeFromKoc/resources/vest.png", this.getX(), this.getY(), 96, 54);
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void activatePowerUp(Player player) {
        new Thread(){
            @Override
            public void run()
            {
            {
                Board.powerUpVestUsage();
            }
        }
        }.start();
    }

    @Override
    public JLabel getObjectLabel() {
        return UIUtils.createLabel("EscapeFromKoc/resources/PowerUps/bottle.png", x, y, 100, 100);
    }
}