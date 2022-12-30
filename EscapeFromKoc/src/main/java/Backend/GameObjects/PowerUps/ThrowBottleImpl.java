package Backend.GameObjects.PowerUps;

import Backend.Player.Player;
import UI.UIUtils;

import javax.swing.*;

public class ThrowBottleImpl implements PowerUp {
    private int x;
    private int y;
    private String trajectory = "";
    private int velocity = 20;
    private int coords[] = { this.getX(), this.getY() };
    private JLabel bottleLabel;

    public ThrowBottleImpl(int x, int y) {
		/***
		 * @REQUIRES: X and Y coordinates of the bottle
		 * @MODIFIES: Creates and modifies the coordinate of the bottle
		 * @EFFECTS: Bottle is spawned.
		 */
        spawnBottle(x, y);
    }

    private void spawnBottle(int x, int y) {
		/***
		 * @REQUIRES: X and Y coordinates of the bottle
		 * @MODIFIES: Creates and modifies the coordinate of the bottle
		 */
        this.x = x;
        this.y = y;
    }

    public JLabel getBottle() {
        return UIUtils.createLabel("EscapeFromKoc/resources/PowerUps/bottle.png", x, y, 100, 100);
    }

    @Override
    public void activatePowerUp(Player player) {
		/***
		 * @REQUIRES: Player
		 * @MODIFIES: Coordinate of the bottle
		 * @EFFECTS: The coordinate of the bottle changes accordingly
		 */
        this.x = player.getX() + 10;
        this.y = player.getY() + 25;
        for (int i = 0; i < 7; i++) {
            if (trajectory.equalsIgnoreCase("south")) {
                this.setY(this.getY() + this.getVelocity());
                this.setX(
                        (int) (this.getX() - this.getVelocity() * ((float) (430 - this.getX()) / (this.getY() + 772))));

            } else if (trajectory.equalsIgnoreCase("north")) {
                this.setY(this.getY() - this.getVelocity());
                this.setX(
                        (int) (this.getX() + this.getVelocity() * ((float) (430 - this.getX()) / (this.getY() + 772))));

            } else if (trajectory.equalsIgnoreCase("west")) {
                this.setX(this.getX() - this.getVelocity());
            } else if (trajectory.equalsIgnoreCase("east")) {
                this.setX(this.getX() + this.getVelocity());
            }
        }
        this.coords = new int[] { this.getX(), this.getY() };
    }

    public int[] getCoords() {
        System.out.println("coords: " + this.coords[0] + " " + this.coords[1]);
        return this.coords;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTrajectory() {
        return this.trajectory;
    }
    
    public void setTrajectory(String trajectory) {
        this.trajectory = trajectory;
    }
}
