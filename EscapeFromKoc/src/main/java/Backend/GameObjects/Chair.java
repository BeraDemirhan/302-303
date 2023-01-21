package Backend.GameObjects;

import javax.swing.*;

import UI.UIUtils;

public class Chair implements GameObjectIntterface {
    private int x;
    private int y;

    private boolean hasKey = true;

    public Chair(int x, int y) {
        spawnObject(x, y);
    }

    @Override
    public void spawnObject(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public boolean objectHasKey() {
        return this.hasKey;
    }

    @Override
    public void setObjectHasKey(boolean x) {
        this.hasKey = x;
    }

    @Override
    public JLabel getObjectLabel() {
        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/chair.png", x, y, 96/2, 54/2);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getName() {
        return "Chair";
    }

}
