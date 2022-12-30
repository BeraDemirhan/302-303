package Backend.GameObjects.PowerUps;

import Backend.Player.Player;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowBottleImplTest {

	@org.junit.jupiter.api.Test
	void initTest() throws IOException {
		/***
		 * initial test to check out whether we have initialized our
		 * plastic bottle correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(0,0);
		assertEquals(0, throwBottle.getX());
		assertEquals(0, throwBottle.getY());
		assertTrue(throwBottle instanceof ThrowBottleImpl,"ThrowBottle is a ThrowBottleImpl.");
		assertTrue(throwBottle instanceof PowerUp,"ThrowBottle is a PowerUp.");
	}

	@org.junit.jupiter.api.Test
	void setTrajectoryTest() throws IOException {
		/***
		 * setTrajectory method is tested here.
		 * assertEquals is used to show that new trajectory
		 * is set correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(0,0);
		String testTrajectory = "back";
		throwBottle.setTrajectory(testTrajectory);
		assertEquals(throwBottle.getTrajectory(), testTrajectory);
	}


	@org.junit.jupiter.api.Test
	void activatePowerUpTest() throws IOException {
		/***
		 * activatePowerUp method is tested here.
		 * Different scenarios are tested to show how x and y
		 * coordinates change according to the trajectory.
		 */
		Player player = Player.getPlayer();
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(0,0);
		int[] startingCoord = throwBottle.getCoords();
		throwBottle.setTrajectory("not-a-trajectory");
		throwBottle.activatePowerUp(player);
		assertArrayEquals(throwBottle.getCoords(), new int[] {250,165});
		
		throwBottle.setX(0); throwBottle.setY(0);
		throwBottle.setTrajectory("south");
		throwBottle.activatePowerUp(player);
		assertArrayEquals(throwBottle.getCoords(), new int[] {222, 305});
		
		throwBottle.setX(0); throwBottle.setY(0);
		throwBottle.setTrajectory("north");
		throwBottle.activatePowerUp(player);
		assertArrayEquals(throwBottle.getCoords(), new int[] {274, 25});
		
		throwBottle.setX(0); throwBottle.setY(0);
		throwBottle.setTrajectory("west");
		throwBottle.activatePowerUp(player);
		assertArrayEquals(throwBottle.getCoords(), new int[] {110, 165});
		
		throwBottle.setX(0); throwBottle.setY(0);
		throwBottle.setTrajectory("east");
		throwBottle.activatePowerUp(player);
		assertArrayEquals(throwBottle.getCoords(), new int[] {390, 165});	
	}
	
	@org.junit.jupiter.api.Test
	void getXTest() throws IOException {
		/***
		 * getX method is tested here.
		 * assertEquals is used to show that x coordinations
		 * are get correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(10,20);
		int xCoord = throwBottle.getX();
		assertEquals(xCoord, 10);
	}
	
	@org.junit.jupiter.api.Test
	void getYTest() throws IOException {
		/***
		 * getY method is tested here.
		 * assertEquals is used to show that y coordinations
		 * are get correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(10,20);
		int yCoord = throwBottle.getY();
		assertEquals(yCoord, 20);
	}
	
	@org.junit.jupiter.api.Test
	void getVelocityTest() throws IOException {
		/***
		 * getVelocity method is tested here.
		 * assertEquals is used to show that velocity
		 * is get correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(10,20);
		int velocity = throwBottle.getVelocity();
		assertEquals(velocity, 20);
	}
	
	@org.junit.jupiter.api.Test
	void setXTest() throws IOException {
		/***
		 * setX method is tested here.
		 * assertEquals is used to show that x coordinations
		 * are set correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(10,20);
		assertEquals(throwBottle.getX(), 10);
		throwBottle.setX(35);
		assertEquals(throwBottle.getX(), 35);
	}
	
	@org.junit.jupiter.api.Test
	void setYTest() throws IOException {
		/***
		 * setY method is tested here.
		 * assertEquals is used to show that y coordinations
		 * are get correctly.
		 */
		ThrowBottleImpl throwBottle = new ThrowBottleImpl(10,20);
		assertEquals(throwBottle.getY(), 20);
		throwBottle.setY(35);
		assertEquals(throwBottle.getY(), 35);
	}
	
}
