package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;

import java.util.ArrayList;

public class LastCallStrategy implements TimeWastingAlienStrategy {


    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
        alien.setActive(false);
        alienAppear(alien, key);
    }
    private void alienAppear(TimeWastingAlien alien, Key key){
        alien.setX(key.getX());
        alien.setY(key.getY());
        alien.getObjectLabel().setVisible(true);
        new Thread(){
            long start = System.nanoTime();
            long finish;
            public void run(){
                while (true){
                    finish = System.nanoTime();
                    if(1 ==  ((finish - start)/1000000000)){
                        alien.getObjectLabel().setVisible(false);
                        break;
                    }
                }
            }
        }.start();

    }

}
