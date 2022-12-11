package Backend.GameObjects;

import javax.swing.*;

public interface GameObjectIntterface {
    JLabel getObjectLabel();

    int getX();

    int getY();

    void spawnObject(int x, int y);

    boolean objectHasKey();
}
