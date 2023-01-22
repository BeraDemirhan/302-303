package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameControler;
import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;
import UI.Board;

import java.util.ArrayList;

public class BetweenThresholdStrategy implements TimeWastingAlienStrategy{

    @Override
    public void changeKeyLoc(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien) {
            if(alien.getControl() == 1){
                System.out.println("Between strategy alien is seen");
                Board.timeWastingAlienLabelSet();
                alien.setControl(0);
                alien.setControlTime(time);
            }

            if (time == alien.getControlTime() + 2) {
                Board.wastingAlienSet();
            }
        }


}
