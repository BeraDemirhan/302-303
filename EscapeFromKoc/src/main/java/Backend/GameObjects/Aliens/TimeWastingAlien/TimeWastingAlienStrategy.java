package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.Key;

import java.util.ArrayList;

public interface TimeWastingAlienStrategy {
    void changeKeyLoc(ArrayList<Object> list, Key key, TimeWastingAlien wastingAlien);
}