package Backend.GameObjects.PowerUps;

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

        } else{
            throw new RuntimeException(mark+" is not a power up.");
        }
        return powerUp;
    }
    
}
