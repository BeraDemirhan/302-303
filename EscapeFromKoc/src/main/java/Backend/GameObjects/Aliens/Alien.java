package Backend.GameObjects.Aliens;

import Backend.GameObjects.GameObjectIntterface;
import Backend.Player.Player;

import javax.swing.*;

public interface Alien extends GameObjectIntterface{
    void spawnObject(int x, int y);

    JLabel getObjectLabel();

    int getX();

    int getY();

    void applyAlienGoal(Object o);

    void attackPlayer(Player p);
}
