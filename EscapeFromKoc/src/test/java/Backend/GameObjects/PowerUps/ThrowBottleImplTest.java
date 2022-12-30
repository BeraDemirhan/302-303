package Backend.GameObjects.PowerUps;

import Backend.Player.Inventory;
import Backend.Player.Player;
import UI.UIUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ThrowBottleImplTest {

    @org.junit.jupiter.api.Test
    void spawnBottle(int x, int y) throws IOException {
        ThrowBottleImpl bottle = new ThrowBottleImpl(0, 0);
        int[] coord = bottle.getCoords();
        assertEquals(0, coord[0]);
        assertEquals(0, coord[1]);
    }



    @org.junit.jupiter.api.Test
    void activatePowerUp() throws IOException {
        Player player = Player.getPlayer();
        ThrowBottleImpl bottle = new ThrowBottleImpl(0, 0);
        bottle.activatePowerUp(player);
        assertEquals(player.getX() + 10, bottle.getX());
        assertEquals(player.getY()+25, bottle.getY());
    }
}