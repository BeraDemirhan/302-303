package Backend.GameObjects.PowerUps;

import Backend.Player.Player;

import javax.swing.*;
import java.awt.*;

public class AddHealthImpl implements PowerUp{

    private int x;
    private int y;
    public AddHealthImpl(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void activatePowerUp(Player player) {
        player.addHealth(1);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    private Image img = new ImageIcon("EscapeFromKoc/resources/health-up-powerup-icon.png").getImage();
    public Image getImg(){
        return img;
    }





}
