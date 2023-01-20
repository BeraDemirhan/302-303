package Backend.GameObjects;

import Backend.GameObjects.Aliens.BlindAlienImpl;

public class ObjectFactory {

    public static GameObjectIntterface createObject(String mark, int x, int y){
        GameObjectIntterface gameObject;

        if(mark.equalsIgnoreCase("chair")){
            gameObject = new Chair(x,y);
        }
        else if(mark.equalsIgnoreCase("blind-alien")){
            gameObject = new BlindAlienImpl(x,y);
        }
        else if(mark.equalsIgnoreCase("sofa")){
            gameObject = new Sofa(x,y);
        }
        else if(mark.equalsIgnoreCase("piano")){
            gameObject = new Piano(x,y);
        }
        else if(mark.equalsIgnoreCase("table")){
            gameObject = new Table(x,y);
        }
        
        else{
            throw new RuntimeException(mark+" is not a game object.");
        }
        return gameObject;
    }

}
