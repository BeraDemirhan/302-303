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
        return UIUtils.createLabel("EscapeFromKoc/resources/BlindAlien/BlindAlienFront.png", x, y, 96, 54);
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

    public void attackPlayer(Player p) {
        p.addHealth(-damage);
    }

    public void applyAlienGoal(Player p) {
        // alien goals are intended to be defined for all aliens,
        // eg: blind alien will try to move towards player on movement events
        // (applying the goal is only the end result not the conditions that it has to
        // have to apply it)
        if (Math.abs(p.getX() - x) < 50
                && Math.abs(p.getY() - y) < 50) {
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
