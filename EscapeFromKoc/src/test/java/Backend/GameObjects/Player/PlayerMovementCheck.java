package Backend.GameObjects.Player;

import Backend.Player.Player;
import org.junit.jupiter.api.Test;

import static Backend.GameControler.movePlayer;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerMovementCheck {

    @Test
    void playerMovesLeft(){
        //checks whether player move left
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
        //checks whether player move right

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
        //checks whether player move up
        //Back means that we can not see the face of character so player goes up. In our coordinate system when we go
        //up our y value decreases. So we check whether y coordinate is decreasing.
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double y1 = p.getY();
        movePlayer("Back");
        double y2 = p.getY();
        assertTrue(y2 < y1);
    }
    @Test
    void playerMovesDown(){
        //checks whether player move up
        //Front means that we can see the face of character so player goes down. In our coordinate system when we go
        //down our y value increases. So we check whether y coordinate is increasing.


        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double y1 = p.getY();
        movePlayer("Front");
        double y2 = p.getY();
        assertTrue(y2 > y1);
    }
    @Test
    void updatesXCorrectly(){
        //checks whether player can update x coordinate correctly when moving up or down
        //Middle point of map's x coordinate is 240. So if player is not in middle trajectory of it should come closer to middle
        // when it goes up. It should go away when player goes down.
        //Back means going up. Front means going down.
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double x1 = p.getX();
        movePlayer("Back");
        double x2 = p.getX();
        double y2 = p.getY();
        assertTrue(((x1 < 240 ) && (x1 < x2)) || ((x1 > 240) && (x1 > x2)));
        movePlayer("Front");
        double x3 = p.getX();
        assertTrue(((x3 < 240 ) && (x3 < x2)) || ((x3 > 240) && (x3 > x2)));



    }
}