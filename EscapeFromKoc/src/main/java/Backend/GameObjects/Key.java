package Backend.GameObjects;

import javax.swing.*;

import UI.UIUtils;

import java.awt.*;

public class Key {

    private int x;
    private int y;
    private boolean revealed = false;
    private boolean isrevealable = true;

    public boolean getRevealed() {
        return revealed;
    }

    public JLabel reveal() {
        if (!isrevealable) {
            return new JLabel();
        }
        revealed = true;

        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/key.png", x, y, 50, 50);
    }

    public void setRevealable(boolean revealable) {
        isrevealable = revealable;
    }

    public void spawnKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean getRevealable() {
        return isrevealable;
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
}
