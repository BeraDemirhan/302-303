package Backend.Player;

import Backend.GameObjects.Key;
import Backend.GameObjects.ObjectFactory;
import Backend.GameObjects.PowerUps.PowerUp;
import Backend.GameObjects.PowerUps.PowerUpFactory;

import javax.swing.*;
import java.awt.*;

public class Player {
    private Image playerfrontimage = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private Image playerbackimage = new ImageIcon("EscapeFromKoc/resources/rabbit-back-angled.png").getImage();
    private Image playerleftimage = new ImageIcon("EscapeFromKoc/resources/rabbit-left-angled.png").getImage();
    private Image playerrightimage = new ImageIcon("EscapeFromKoc/resources/rabbit-right-angled.png").getImage();
    private Inventory inventory = null;
    private int health = 5;
    private int score = 0;

    private int xCoord = 240;
    private int yCoord = 140;

    private static Player p = null;

    private Player() {
        if (p == null) {
            this.inventory = new Inventory();
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setInventory(String items){
        for (String s: items.split(" ")) {
            if (s.equalsIgnoreCase("Key")) {
                Key key = new Key();
                Inventory.addItem(key);
            }
            else if (s.contains("bottle")){
                Inventory.addItem(PowerUpFactory.createPowerUp("throw-bottle", 0, 0));
            }
            else if (s.contains("health")){
                Inventory.addItem(PowerUpFactory.createPowerUp("add-health", 0, 0));
            }
            else if (s.contains("vest")){
                Inventory.addItem(PowerUpFactory.createPowerUp("power-up-vest", 0, 0));
            }
        }
    }

    public void usePowerUp(PowerUp pu) {
        pu.activatePowerUp(p);
        inventory.removeItem(pu);
    }

    public void addHealth(int amount) {
        // REQUIRES: Player
        // Modifies: the number of health also known as extra life
        // Effects: when player get the health the number of heath will increase
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

    public Inventory getInventory() {
        return inventory;
    }

    public String getInventoryStr(){
        return inventory.toString();
    }
    
    public int getHealth(){
        return this.health;
    }

    public void setScore(int parseInt) {
        this.score = parseInt;
    }
}
