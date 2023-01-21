package Backend.GameObjects;

import javax.swing.JLabel;

import UI.UIUtils;

public class Sofa implements GameObjectIntterface {
    private int x;
    private int y;

    private boolean hasKey = false;

    public Sofa(int x, int y) {
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
        return UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/sofaCorner.png", x, y, 96*3/2, 54*3/2);
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
        return "Sofa";
    }
    @Override
    public void addKey() {
        // TODO Auto-generated method stub
        hasKey = true;
        
    }
}
    

