package Backend.GameObjects.PowerUps;

import Backend.Player.Player;

public class AddHealthImpl implements PowerUp{
    @Override
    public void activatePowerUp(Player player) {
        player.addHealth(1);
    }
}
