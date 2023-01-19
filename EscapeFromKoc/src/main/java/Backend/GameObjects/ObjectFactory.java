package Backend.GameObjects;

import Backend.GameObjects.Aliens.BlindAlienImpl;

public class ObjectFactory {

    public static GameObjectIntterface createObject(String mark, int x, int y){
        GameObjectIntterface gameObject;

        if(mark.equals("chair")){
            gameObject = new Chair(x,y);
        }
        else if(mark.equals("blind-alien")){
            gameObject = new BlindAlienImpl(x,y);
        }

        else if(mark.equals("sofa")){
            gameObject = new Sofa(x,y);
        }
        else if(mark.equals("table")){
            gameObject = new Table(x,y);
        }
        else if(mark.equals("piano")){
            gameObject = new Piano(x,y);
        }
        else{
            throw new RuntimeException(mark+" is not a game object.");
        }
        return gameObject;
    }

}
