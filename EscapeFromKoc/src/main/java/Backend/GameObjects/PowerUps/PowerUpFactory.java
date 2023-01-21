package Backend.GameObjects.PowerUps;

import java.util.Random;
public class PowerUpFactory {
    public static PowerUp createPowerUp(String mark, int x, int y){
        PowerUp powerUp;

        if(mark.equals("add-health")){
            powerUp = new AddHealthImpl(x,y);
        }
        else if(mark.equals("throw-bottle")){
            powerUp = new ThrowBottleImpl(x,y);
        }
        else if(mark.equals("hint")){
            powerUp = new HintPowerUp(x, y);
        }
        else if (mark.equals("extra-time")) {
            powerUp = new ExtraTime(x,y);

        }
        else{
            throw new RuntimeException(mark+" is not a power up.");
        }
        return powerUp;
    }
    public static PowerUp spawnPowerUp(String mark){
        Random rand = new Random();
        PowerUp powerUp;
        int x = rand.nextInt(250, 710) +1;
        int y = rand.nextInt(300,540) +1;
        String [] pList = {"add-health","throw-bottle","hint","extra-time"};
        /*int index = rand.nextInt(pList.length)+1;
        String mark = pList[index];*/
        if(mark.equals("add-health")){
            powerUp = new AddHealthImpl(x,y);
        }
        else if(mark.equals("throw-bottle")){
            powerUp = new ThrowBottleImpl(x,y);
        }
        else if(mark.equals("hint")){
            powerUp = new HintPowerUp(x, y);
        }
        else if (mark.equals("extra-time")) {
            powerUp = new ExtraTime(x,y);

        }
        else{
            throw new RuntimeException(mark+" is not a power up.");
        }
        return powerUp;
    }
}
