package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.Aliens.Alien;
import Backend.Player.Player;
import Backend.GameObjects.Key;
import javax.swing.*;

public interface TimeWastingAlienStrategy {
    void changeKeyLoc(Object[] list, Key key);
}