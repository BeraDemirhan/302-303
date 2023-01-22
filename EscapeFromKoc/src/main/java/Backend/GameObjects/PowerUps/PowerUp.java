package Backend.GameObjects.PowerUps;


import javax.swing.JLabel;

import Backend.Player.Player;

public interface PowerUp {

    void activatePowerUp(Player player);

    JLabel getObjectLabel();

    int getX();
    int getY();
}
