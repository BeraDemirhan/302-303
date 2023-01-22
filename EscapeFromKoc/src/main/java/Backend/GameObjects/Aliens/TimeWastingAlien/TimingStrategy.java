package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;
import UI.Board;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TimingStrategy implements TimeWastingAlienStrategy{
    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
        new Thread() {
            public void run() {
                if (time % 3 == 1) {
                    alien.setDoJob(true);
                    Board.wastingAlienSet();}
                if ((time % 3 == 0) && alien.isDoJob()) {
                    alien.setDoJob(false);
                    System.out.println("Key changing time: " + time);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).objectHasKey()) {
                            list.get(i).setObjectHasKey(false);
                        }
                    }
                    changeLoc(list, key);
                    Board.timeWastingAlienLabelSet();
                }

            }
        }.start();
    }
    private void changeLoc(ArrayList<GameObjectIntterface> list, Key key){
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).setObjectHasKey(true);
        key.setY(list.get(randomNum).getY());
        key.setX(list.get(randomNum).getX());
        System.out.println("Alien changed Key loc with timing strat");
    }
}
