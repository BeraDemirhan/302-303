package Backend.GameObjects;

public class ObjectFactory {

    public static GameObjectIntterface createObject(String mark, int x, int y){
        GameObjectIntterface gameObject;

        if(mark.equals("chair")){
            gameObject = new Chair(x,y);
        }
        else{
            throw new RuntimeException(mark+" is not a game object.");
        }
        return gameObject;
    }

}
