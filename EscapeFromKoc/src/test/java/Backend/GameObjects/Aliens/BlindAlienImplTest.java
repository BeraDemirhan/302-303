import Backend.GameObjects.Aliens.BlindAlienImpl;
import Backend.Player.Player;
import org.junit.jupiter.api.Test;
import javax.swing.JLabel;

import static org.junit.jupiter.api.Assertions.*;

class BlindAlienImplTest {

    @Test
    void getX() {
        BlindAlienImpl a = new BlindAlienImpl(10,10);
        assertEquals(10, a.getX());
    }

    @Test
    void getY() {
        BlindAlienImpl a = new BlindAlienImpl(10,10);
        assertEquals(10, a.getY());
    }
    @Test
    void hasKey(){
        assertFalse(new BlindAlienImpl(10,10).objectHasKey());
    }

    @Test
    void moveAlienUpToPlayer() {
        BlindAlienImpl a = new BlindAlienImpl(10,10);
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        double diffOne = Math.sqrt(Math.pow(a.getX()-p.getX(),2) + Math.pow(a.getY() - p.getY(),2));
        a.moveAlienToPlayer(p);
        double diffTwo = Math.sqrt(Math.pow(a.getX()-p.getX(),2) + Math.pow(a.getY() - p.getY(),2));

        assertTrue(diffOne > diffTwo);

    }

    @Test
    void moveAlienDownToPlayer() {
        BlindAlienImpl a = new BlindAlienImpl(100,100);
        Player p = Player.getPlayer();
        p.setX(10);
        p.setY(10);
        double diffOne = Math.sqrt(Math.pow(a.getX()-p.getX(),2) + Math.pow(a.getY() - p.getY(),2));
        a.moveAlienToPlayer(p);
        double diffTwo = Math.sqrt(Math.pow(a.getX()-p.getX(),2) + Math.pow(a.getY() - p.getY(),2));

        assertTrue(diffOne > diffTwo);

    }

    @Test
    void attackPlayerShouldCall(){
        BlindAlienImpl a = new BlindAlienImpl(10,10);
        Player p = Player.getPlayer();
        p.setY(10);
        p.setX(10);
        int prevHealth = p.getHealth();
        a.applyAlienGoal(p);
        assertTrue(p.getHealth() < prevHealth && a.getX() == 10 && a.getY() == 10); // The alien should not move and attack player
    }

    @Test
    void moveTowardsPlayerShouldCall() {
        BlindAlienImpl a = new BlindAlienImpl(10,10);
        Player p = Player.getPlayer();
        p.setX(100);
        p.setY(100);
        int prevHealth = p.getHealth();
        double diffOne = Math.sqrt(Math.pow(a.getX()-p.getX(),2) + Math.pow(a.getY() - p.getY(),2));
        a.applyAlienGoal(p);
        double diffTwo = Math.sqrt(Math.pow(a.getX()-p.getX(),2) + Math.pow(a.getY() - p.getY(),2));

        assertTrue(diffOne > diffTwo && p.getHealth() == prevHealth); // The test where the alien should move and not attack player

    }



}