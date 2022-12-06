package Backend.Player;

import Backend.GameObjects.PowerUps.PowerUp;

import javax.swing.*;
import java.awt.*;

public class Player {
    private Image playerfrontimage = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private Image playerbackimage = new ImageIcon("EscapeFromKoc/resources/rabbit-back-angled.png").getImage();
    private Image playerleftimage = new ImageIcon("EscapeFromKoc/resources/rabbit-left-angled.png").getImage();
    private Image playerrightimage = new ImageIcon("EscapeFromKoc/resources/rabbit-right-angled.png").getImage();
    private Inventory inventory = new Inventory();
    private int health = 5;

    private int xCoord = 100;
    private int yCoord = 100;


    private static Player p = null;
    private Player(){
        if (p == null){
            p = this;
        }
    }
    public static Player getPlayer(){
        if(p == null){
            return new Player();
        }else{
            return p;
        }
    }

    public void usePowerUp(PowerUp pu){
        pu.activatePowerUp(p);
        inventory.removeItem(pu);
    }
    public void addHealth(int amount){
        p.health += amount;
        System.out.println(p.health);
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
    public void setX(int newx){
        this.xCoord = newx;
    }
    public void setY(int newy){
        this.yCoord = newy;
    }
    public int getX(){
        return this.xCoord;
    }
    public int getY(){
        return this.yCoord;
    }
    public int getVelocity(){
        return 10;
    }

    public void addToInventory(Object obj){
        inventory.addItem(obj);
    }
}
