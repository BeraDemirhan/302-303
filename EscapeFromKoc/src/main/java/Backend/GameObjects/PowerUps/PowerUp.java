package Backend.GameObjects.PowerUps;


import Backend.Player.Player;

import java.awt.*;

public interface PowerUp {

    void activatePowerUp(Player player);

    int getX();

    int getY();
}
