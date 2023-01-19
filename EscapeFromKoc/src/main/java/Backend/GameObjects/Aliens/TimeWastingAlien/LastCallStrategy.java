package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.Key;

import java.util.ArrayList;

public class LastCallStrategy implements TimeWastingAlienStrategy {


    @Override
    public void changeKeyLoc(ArrayList<Object> list, Key key, TimeWastingAlien wastingAlien) {
        //If less than 30% of the total time remains when this alien appears,
        // the alien will conclude that the player is not in a good situation,
        // and he/she might lose the game.
        // So, it will change the location of the key only once and disappear.
        if(wastingAlien.isActive()){
            list.forEach();
        }

    }
    private Object findKey(){

    }


}
