package Backend.GameObjects.Player;

import Backend.Player.Player;
import org.junit.jupiter.api.Test;

import static Backend.GameControler.movePlayer;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerMovementCheck {

    @Test
    void playerMovesLeft(){
        // REQUIRES: Player x , y != null
        // Modifies: The location of the player, x and y
        // Effects: The player moved left
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double x1 = p.getX();
        movePlayer("Left");
        double x2 = p.getX();
        assertTrue(x1 > x2);
    }
    @Test
    void playerMovesRight(){
        // REQUIRES: Player x , y != null
        // Modifies: The location of the player, x and y
        // Effects: The player moved right
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double x1 = p.getX();
        movePlayer("right");
        double x2 = p.getX();
        assertTrue(x1 < x2);
    }
    @Test
    void playerMovesUp(){
        // REQUIRES: Player x , y != null
        // Modifies: The location of the player, x and y
        // Effects: The player moved up
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double y1 = p.getY();
        movePlayer("Front");
        double y2 = p.getY();
        assertTrue(y1 < y2);
    }
    @Test
    void playerMovesDown(){
        // REQUIRES: Player x , y != null
        // Modifies: The location of the player, x and y
        // Effects: The player moved down
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double y1 = p.getY();
        movePlayer("Back");
        double y2 = p.getY();
        assertTrue(y1 > y2);

    }
}