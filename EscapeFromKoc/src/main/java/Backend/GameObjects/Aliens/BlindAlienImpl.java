package Backend.GameObjects.Aliens;

import Backend.Player.Player;

import javax.swing.*;
import java.awt.*;

public class BlindAlienImpl implements Alien{
    private int velocity = 5;
    private int damage = 1;
    private int x;
    private int y;
    private Image blindAlienImg = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private JLabel blindAlienLabel;
    public BlindAlienImpl(int x , int y ){
        spawnObject(x, y);
    }

    @Override
    public void spawnObject(int x, int y){
        this.x = x;
        this.y = y;

        blindAlienImg = blindAlienImg.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        blindAlienLabel = new JLabel(new ImageIcon(blindAlienImg));
        blindAlienLabel.setBounds(x, y, 100, 100);
    }

    @Override
    public JLabel getObjectLabel(){
        return blindAlienLabel;
    }


    @Override
    public int getX(){
        return this.x;
    }

    @Override
    public int getY(){
        return this.y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void moveAlienToPlayer(Player p){
        if (y < p.getY()) {
            this.setY(y + velocity);
            this.setX((int) (y - velocity * ((float) (430 - x) / (y + 772))));

        } else if (y > p.getY()) {
            this.setY(y - velocity);
            this.setX((int) (x + velocity * ((float) (430 - x) / (y + 772))));

        }
        if (x > p.getX()) {
            this.setX(x - velocity);
        } else if (x < p.getX()) {
            this.setX(x + velocity);
        }

    }
    public void attackPlayer(Player p){
        p.addHealth(-damage);
    }

    public void applyAlienGoal(Player p){
        if(Math.abs(p.getX() - x) < 50
                && Math.abs(p.getY() - y) < 50){
            attackPlayer(p);
        }else{
            moveAlienToPlayer(p);
        }
    }


}
