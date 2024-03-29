package Backend.GameObjects;

import javax.swing.*;

public interface GameObjectIntterface {
    JLabel getObjectLabel();

    int getX();

    int getY();

    void spawnObject(int x, int y);

    boolean objectHasKey();

    void setObjectHasKey(boolean x);

    String getName();

    void addKey();
}
