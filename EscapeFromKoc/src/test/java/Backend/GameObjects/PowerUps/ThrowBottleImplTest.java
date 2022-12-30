package Backend.GameObjects.PowerUps;

import Backend.Player.Inventory;
import Backend.Player.Player;
import UI.UIUtils;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ThrowBottleImplTest {

    @org.junit.jupiter.api.Test
    void spawnBottle(int x, int y) throws IOException {
        // REQUIRES: Bottle x , y = 0
        // Modifies: ?
        // Effects: The bottle appears a place on the room
        ThrowBottleImpl bottle = new ThrowBottleImpl(0, 0);
        assertEquals(0, bottle.getX());
        assertEquals(0, bottle.getY());
    }



    @org.junit.jupiter.api.Test
    void activatePowerUp() throws IOException {
        // REQUIRES: Bottle x and y equals player x +10  and player y + 25
        // Modifies: coordinates of the bottle
        // Effects: with arrow key the bottle moves
        Player player = Player.getPlayer();
        ThrowBottleImpl bottle = new ThrowBottleImpl(0, 0);
        bottle.activatePowerUp(player);
        assertEquals(player.getX() + 10, bottle.getX());
        assertEquals(player.getY()+25, bottle.getY());
    }
}