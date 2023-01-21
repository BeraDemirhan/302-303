package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LastCallStrategy implements TimeWastingAlienStrategy {


    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
        alien.setActive(false);
        changeLoc(list,key);
        alienAppear(alien, key);
    }
    private void changeLoc(ArrayList<GameObjectIntterface> list, Key key){

        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).setObjectHasKey(true);
        key.setY(list.get(randomNum).getY());
        key.setX(list.get(randomNum).getX());
        System.out.println("Alien changed Key loc");
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
