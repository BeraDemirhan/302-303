package Backend.GameObjects.Aliens;

import Backend.Player.Player;
import UI.UIUtils;

import javax.swing.*;
import java.awt.*;

public class BlindAlienImpl implements Alien {

    private boolean ALERT = false;
    private int velocity = 5;
    private int damage = 1;
    private int x;
    private int y;
    private String dir = "Front";
    private String generalPath = "EscapeFromKoc/resources/BlindAlien/BlindAlien" ;// General path does not change but dir does


    public BlindAlienImpl(int x, int y) {
        spawnObject(x, y);
    }

    @Override
    public void spawnObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public JLabel getObjectLabel() {
        return UIUtils.createLabel(generalPath+dir+".png", x, y, 96, 54);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void moveAlienToPlayer(Player p) {
        System.out.println(this.x + " " + this.y);
        if (this.y < p.getY()) {
            this.setY(this.y + velocity);
            this.setX((int) (this.x - velocity * ((float) (430 - this.x) / (this.y + 772))));
            this.dir = "Front";

        } else if (this.y > p.getY()) {
            this.setY(this.y - velocity);
            this.setX((int) (this.x + velocity * ((float) (430 - this.x) / (this.y + 772))));
            this.dir = "Back";

        }
        if (this.x > p.getX()) {
            this.setX(this.x - velocity);
            this.dir = "Left";
        } else if (this.x < p.getX()) {
            this.setX(this.x + velocity);
            this.dir = "Right";
        }
        System.out.println(this.x + " " + this.y);


    }

    public void attackPlayer(Player p) {
        p.addHealth(-damage);
    }

    public void applyAlienGoal(Player p) {
        // alien goals are intended to be defined for all aliens,
        // eg: blind alien will try to move towards player on movement events
        // (applying the goal is only the end result not the conditions that it has to
        // have to apply it)
        if (Math.abs(p.getX() - this.x) < 50
                && Math.abs(p.getY() - this.y) < 50) {
            System.out.println("Attacking");
            attackPlayer(p);
        } else {
            moveAlienToPlayer(p);
        }
    }

    @Override
    public boolean objectHasKey() {
        // Placeholder code
        return false;
    }

}
