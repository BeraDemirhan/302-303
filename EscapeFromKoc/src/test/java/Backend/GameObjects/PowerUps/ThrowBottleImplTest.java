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
        Player player = Player.getPlayer();
        spawnBottle(0,0);
        // will be completed
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