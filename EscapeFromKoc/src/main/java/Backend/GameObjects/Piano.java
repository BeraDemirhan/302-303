package Backend.GameObjects;

import javax.swing.JLabel;

import UI.UIUtils;

public class Piano implements GameObjectIntterface {
    private int x;
    private int y;

    private boolean hasKey = false;

    public Piano(int x, int y) {
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
    public JLabel getObjectLabel() {
        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/piano.png", x, y, 96/2, 54/2);
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
        return "Piano";
    }
    @Override
    public void addKey() {
        // TODO Auto-generated method stub
        hasKey = true;
        
    }
}