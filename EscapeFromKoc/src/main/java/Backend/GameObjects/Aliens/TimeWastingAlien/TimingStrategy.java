package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TimingStrategy implements TimeWastingAlienStrategy{
    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
        if (time/3 ==0){
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).objectHasKey()){
                    list.get(i).setObjectHasKey(false);
                }
            }
            changeLoc(list, key);
            alienAppear(alien,key);
        }
    }
    private void changeLoc(ArrayList<GameObjectIntterface> list, Key key){
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).setObjectHasKey(true);
        key.setY(list.get(randomNum).getY());
        key.setX(list.get(randomNum).getX());
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
