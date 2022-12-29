package Backend.GameObjects.PowerUps;

import Backend.ButtonResponders;
import Backend.GameControler;
import Backend.Player.Player;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AddHealthImplTest {

    @org.junit.jupiter.api.Test
    void addHealth() throws IOException {
        Player player = new Player();
        int prev = player.getHealth();
        player.addHealth(1);
        assertEquals(prev + 1, player.getHealth());

    }
}
