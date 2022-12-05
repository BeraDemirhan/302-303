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


    private Player p = null;
    public Player(){
        if (p == null){
            Inventory inventory = new Inventory();
            p = this;
        }

    }
    public Player getPlayer(){
        return p;
    }

    public void usePowerUp(PowerUp pu){
        pu.activatePowerUp(p);
        inventory.removeItem(pu);
    }
    public void addHealth(int amount){
        p.health += amount;
    }

    public Image getPlayerImg(String position){
        Image img = null;
        if(position.equalsIgnoreCase("Front")){
            img = playerfrontimage;
        } else if(position.equalsIgnoreCase("back")){
            img = playerbackimage;
        } else if (position.equalsIgnoreCase("Left")){
            img = playerleftimage;
        } else if(position.equalsIgnoreCase("right")) {
            img = playerrightimage;
        }
        return img;
    }
}
