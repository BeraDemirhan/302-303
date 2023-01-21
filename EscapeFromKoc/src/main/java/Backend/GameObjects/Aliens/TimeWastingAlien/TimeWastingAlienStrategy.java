package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;

import java.util.ArrayList;

public interface TimeWastingAlienStrategy {
    void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien);
}