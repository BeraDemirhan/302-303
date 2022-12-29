package Backend.GameObjects.PowerUps;

import Backend.ButtonResponders;
import Backend.GameControler;
import Backend.Player.Player;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AddHealthImplTest {

    @org.junit.jupiter.api.Test
    void addHealth() throws IOException {
        Player player = Player.getPlayer();
        int prev = player.getHealth();
        player.addHealth(1);
        assertEquals(prev + 1, player.getHealth());
    }

    @org.junit.jupiter.api.Test
    void spawnHealth() throws IOException {
        AddHealthImpl addHealth = new AddHealthImpl(0, 0);
        assertEquals(0, addHealth.getX());
        assertEquals(0, addHealth.getY());
    }

    @org.junit.jupiter.api.Test
    void getHealth() throws IOException {
        AddHealthImpl addHealth = new AddHealthImpl(0, 0);
        assertNotNull(addHealth.getHealth());
    }

    @org.junit.jupiter.api.Test
    void activatePowerUp() throws IOException {
        Player player = Player.getPlayer();
        int prev = player.getHealth();
        AddHealthImpl addHealth = new AddHealthImpl(0, 0);
        addHealth.activatePowerUp(player);
        assertEquals(prev + 1, player.getHealth());
    }

}
