package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.Key;

import java.util.ArrayList;

public class BetweenThresholdStrategy implements TimeWastingAlienStrategy{

    @Override
    public void changeKeyLoc(ArrayList<Object> list, Key key, TimeWastingAlien wastingAlien) {
        //stay 2 seconds.  then disappear
    }
}
