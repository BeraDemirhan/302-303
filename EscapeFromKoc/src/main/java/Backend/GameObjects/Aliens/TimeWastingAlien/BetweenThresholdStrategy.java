package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;

import java.util.ArrayList;

public class BetweenThresholdStrategy implements TimeWastingAlienStrategy{

    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
        if(alien.isAppear()){
            alienAppear(alien,key);
            alien.setAppear(false);
        }

    }

    private void alienAppear(TimeWastingAlien alien, Key key){
        alien.setX(key.getX());
        alien.setY(key.getY());
        alien.getObjectLabel().setVisible(true);
        System.out.println("Between strategy alien is seen");
        new Thread(){
            long start = System.nanoTime();
            long finish;
            public void run(){
                while (true){
                    finish = System.nanoTime();
                    if(2 ==  ((finish - start)/1000000000)){
                        alien.getObjectLabel().setVisible(false);
                        break;
                    }
                }
            }
        }.start();

    }


}
