package Backend.GameObjects.Aliens.TimeWastingAlien;

import Backend.GameControler;
import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.Key;
import Backend.Player.Player;

import javax.swing.*;
import java.util.ArrayList;

public class TimeWastingAlien implements Alien {
    private boolean isActive = true;
    private double timeElapsed;
    private int x;
    private int y;
    private String dir = "Front";
    private String generalPath = "EscapeFromKoc/resources/BlindAlien/BlindAlienFront.png";


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
    public void wasteTime(ArrayList<Object> list, Key key, TimeWastingAlien wastingAlien){
        if(isActive){
            wastingStrategy.changeKeyLoc(list, key, wastingAlien);
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
        setTimeElapsed((double) o);
        if( getTimeElapsed()/ GameControler.levelTime > 0.7){
            setStrategyCode(Timing);
            setAlienStrategy(getStrategyCode());
        }
        else if ((getTimeElapsed()/GameControler.levelTime >= 0.3) && (getTimeElapsed()/ GameControler.levelTime <= 0.7)){
            setStrategyCode(BetweenThreshold);
            setAlienStrategy(getStrategyCode());
        }
        else if (getTimeElapsed()/ GameControler.levelTime < 0.3) {
            setStrategyCode(LastCall);
            setAlienStrategy(getStrategyCode());
        }
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    @Override
    public boolean objectHasKey() {
        return false;
    }

    @Override
    public JLabel getObjectLabel() {
        return GameControler.getObjectLabel(generalPath, dir, x, y, 96, 54);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }


    @Override
    public void attackPlayer(Player p) {
        // do nothing
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
