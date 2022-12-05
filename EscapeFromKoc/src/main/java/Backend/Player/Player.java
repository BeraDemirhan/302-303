package Backend.Player;

import Backend.GameObjects.PowerUps.PowerUp;
public class Player {
    private Inventory inventory = null;
    private int health = 5;
    public Player(){
        Inventory inventory = new Inventory();
    }

    public void usePowerUp(PowerUp pu){
        pu.activatePowerUp();
        inventory.removeItem(pu);
    }

}
