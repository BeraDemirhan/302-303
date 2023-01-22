package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameControler;
import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;
import UI.Board;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LastCallStrategy implements TimeWastingAlienStrategy {


    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
        if(alien.getControl() == 0){
            changeLoc(list,key);
            Board.timeWastingAlienLabelSet();
            alien.setControl(1);
            alien.setControlTime(GameControler.showTime());
        }
        if (GameControler.showTime() == alien.getControlTime() + 1){
            Board.wastingAlienSet();
            alien.setActive(false);
        }

    }
    private void changeLoc(ArrayList<GameObjectIntterface> list, Key key){

        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).setObjectHasKey(true);
        key.setY(list.get(randomNum).getY());
        key.setX(list.get(randomNum).getX());
        System.out.println("Alien changed Key loc");
    }


}
