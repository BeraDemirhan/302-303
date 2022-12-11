package Backend.GameObjects.PowerUps;

import Backend.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ThrowBottleImpl implements PowerUp {
    private int x;

    private int y;

    private int velocity = 20;
    private int coords[] = { this.getX(), this.getY() };

    private JLabel bottleLabel;

    public ThrowBottleImpl(int x, int y) {
        spawnBottle(x, y);
    }

    private void spawnBottle(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public JLabel getBottle() {
        return bottleLabel;
    }

    private String trajectory = "";

    public void setTrajectory(String trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public void activatePowerUp(Player player) {
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
        coords = new int[] { this.getX(), this.getY() };
    }

    public int[] getCoords() {
        System.out.println("coords: " + coords[0] + " " + coords[1]);
        return coords;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setX(int x) {
        // bottleLabel.setBounds(x, this.y, 100, 100);
        this.x = x;
    }

    public void setY(int y) {
        // bottleLabel.setBounds(this.x, y, 100, 100);
        this.y = y;
    }
}
