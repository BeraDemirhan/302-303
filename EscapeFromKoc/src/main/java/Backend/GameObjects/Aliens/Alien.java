package Backend.GameObjects.Aliens;

import Backend.GameObjects.GameObjectIntterface;
import Backend.Player.Player;

import javax.swing.*;

public interface Alien extends GameObjectIntterface {
    @Override
    void spawnObject(int x, int y);

    @Override
    JLabel getObjectLabel();


    @Override
    int getX();

    @Override
    int getY();

    int setX(int x);

    int setY(int y);

    void applyAlienGoal(Player p);
}
