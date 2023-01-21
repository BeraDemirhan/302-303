package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameControler;
import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;
import Backend.Player.Player;

import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;

public class TimeWastingAlien implements Alien {
    private boolean isActive = true;
    
    private int x;
    private int y;
    private String dir = "Front";
    private String generalPath = "EscapeFromKoc/resources/TimeWastingAlien.png";


    private TimeWastingAlienStrategy wastingStrategy;
    private int strategyCode = 0;
    public static final int  LastCall = 2;
    public static final int  BetweenThreshold = 1;
    public static final int  Timing = 0;

    public TimeWastingAlien(int x, int y){
        spawnObject(x, y);

    }
    public void setStrategyCode(int arg){
        this.strategyCode = arg;
    }
    public int getStrategyCode(){
        return strategyCode;
    }
    public void wasteTime(ArrayList<GameObjectIntterface> list, Key key, int time, TimeWastingAlien alien){
        if(isActive){
            wastingStrategy.changeKeyLoc(list, key, time, alien);
        }
    }
    private void setAlienStrategy(int strategyCode){
        switch (getStrategyCode()){
            case TimeWastingAlien.Timing:
                wastingStrategy = new TimingStrategy();
                    break;
            case TimeWastingAlien.BetweenThreshold:
                wastingStrategy = new BetweenThresholdStrategy();
                    break;
            case TimeWastingAlien.LastCall:
                wastingStrategy = new LastCallStrategy();
                    break;
        }
    }


    @Override
    public void spawnObject(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void applyAlienGoal(Object o) {
        // inputumuz geçen zaman
        //int remainingTimeRate = ((double) o) / GameControler.get
        if((double)o >0.7){
            setStrategyCode(Timing);
            setAlienStrategy(getStrategyCode());
        } else if (((double) o >= 0.3) && ((double) o <=0.7)){
            setStrategyCode(BetweenThreshold);
            setAlienStrategy(getStrategyCode());
        } else if ((double) o < 0.3) {
            setStrategyCode(LastCall);
            setAlienStrategy(getStrategyCode());
        }
    }


    @Override
    public boolean objectHasKey() {
        return false;
    }

    @Override
    public void setObjectHasKey(boolean x) {

    }

    @Override
    public JLabel getObjectLabel() {
        return GameControler.getObjectLabel(generalPath, dir, getX(), getY(), 96, 54);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void attackPlayer(Player p) {
        // do nothing
    }
    @Override
    public String getName() {
        return "TimeWastingAlien";
    }
    @Override
    public void addKey(){
        return;
    }

}
