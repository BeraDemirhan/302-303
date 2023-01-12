package Backend.GameObjects;

import Backend.GameObjects.Aliens.BlindAlienImpl;
import Backend.GameObjects.Aliens.TimeWastingAlien.TimeWastingAlien;

public class ObjectFactory {

    public static GameObjectIntterface createObject(String mark, int x, int y){
        GameObjectIntterface gameObject;

        if(mark.equals("chair")){
            gameObject = new Chair(x,y);
        }
        else if(mark.equals("blind-alien")){
            gameObject = new BlindAlienImpl(x,y);
        }
        else if(mark.equals("timeawasting-alien")){
            gameObject = new TimeWastingAlien(x,y);
        }
        else{
            throw new RuntimeException(mark+" is not a game object.");
        }
        return gameObject;
    }

}
