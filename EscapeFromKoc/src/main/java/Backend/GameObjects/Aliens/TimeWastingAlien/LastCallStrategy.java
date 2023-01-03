package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameControler;
import Backend.GameObjects.Key;
import Backend.Player.Player;

import javax.swing.*;

public class LastCallStrategy implements TimeWastingAlienStrategy {

    private int x;
    private int y;
    private String direction = "Front";

    private String imagePath = "";  //Bera buraya eklersin alien görünüşünü. Aynı zamanda getObjectLabel'ı düzeltirsin.


    public LastCallStrategy(int x, int y){
        spawnObject(x,y);
    }
    @Override
    public void spawnObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean objectHasKey() {
        return false;
    }

    @Override
    public JLabel getObjectLabel() {
        return GameControler.getObjectLabel(imagePath, direction, x, y, 96, 54);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void applyAlienGoal(Object o) {
        changeKeyLoc((Key) o);
    }

    @Override
    public void attackPlayer(Player p) {

    }

    @Override
    public void changeKeyLoc(Key key) {

    }
}
