package Backend.Player;

import Backend.GameObjects.PowerUps.PowerUp;

import javax.swing.*;
import java.awt.*;

public class Player {
    private Image playerfrontimage = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private Image playerbackimage = new ImageIcon("EscapeFromKoc/resources/rabbit-back-angled.png").getImage();
    private Image playerleftimage = new ImageIcon("EscapeFromKoc/resources/rabbit-left-angled.png").getImage();
    private Image playerrightimage = new ImageIcon("EscapeFromKoc/resources/rabbit-right-angled.png").getImage();
    private Inventory inventory = null;
    private int health = 5;

    private int xCoord = 240;
    private int yCoord = 140;

    private static Player p = null;

    public Player() {
        if (p == null) {
            Inventory inventory = new Inventory();
            p = this;
        }
    }

    public static Player getPlayer() {
        if (p == null) {
            return new Player();
        } else {
            return p;
        }
    }

    public void usePowerUp(PowerUp pu) {
        pu.activatePowerUp(p);
        inventory.removeItem(pu);
    }

    public void addHealth(int amount) {
        p.health += amount;
    }

    public Image getPlayerImg(String position) {
        Image img = null;
        if (position.equalsIgnoreCase("Front")) {
            img = playerfrontimage;
        } else if (position.equalsIgnoreCase("Back")) {
            img = playerbackimage;
        } else if (position.equalsIgnoreCase("Left")) {
            img = playerleftimage;
        } else if (position.equalsIgnoreCase("right")) {
            img = playerrightimage;
        }
        return img;
    }

    public void setX(int newx) {
        this.xCoord = newx;
    }

    public void setY(int newy) {
        this.yCoord = newy;
    }

    public int getX() {
        return this.xCoord;
    }

    public int getY() {
        return this.yCoord;
    }

    public int getVelocity() {
        return 10;
    }
}
