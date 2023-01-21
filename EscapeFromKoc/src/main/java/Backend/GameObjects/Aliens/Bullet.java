package Backend.GameObjects.Aliens;

import javax.swing.JLabel;

import Backend.GameControler;
import Backend.GameObjects.GameObjectIntterface;

public class Bullet implements GameObjectIntterface {
    private String generalPath = "EscapeFromKoc/resources/carrot";// General path does not change but dir
    // does
    private int x;
    private int y;
    private int velocity = 5;

    private int lastPlayerX;
    private int lastPlayerY;

    public int getVelocity() {
        return velocity;
    }

    public Bullet(int x, int y) {
        spawnObject(x, y);
    }

    @Override
    public void setObjectHasKey(boolean x) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addKey() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public JLabel getObjectLabel() {
        // TODO Auto-generated method stub
        return GameControler.getObjectLabel(generalPath, "", x, y, 34, 56);
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return this.x;
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return this.y;
    }

    @Override
    public void spawnObject(int x, int y) {
        // TODO Auto-generated method stub
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean objectHasKey() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getName() {
        return "Shot-Bullet";
    }

    public void moveBulletTo(int x, int y) {
        if (this.y < y) {
            // REQUIRES: Alien and Player x , y != null
            // Modifies: The location of the alien, x and y
            // Effects: The Alien and the player are now Closer (in terms of euclidean
            // distance)
            this.setY(this.y + velocity);
            this.setX((int) (this.x - velocity * ((float) (430 - this.x) / (this.y + 772))));

        } else if (this.y > y) {
            // REQUIRES: Alien and Player x , y != null
            // Modifies: The location of the alien, x and y
            // Effects: The Alien and the player are now closer (in terms of euclidean
            // distance)
            this.setY(this.y - velocity);
            this.setX((int) (this.x + velocity * ((float) (430 - this.x) / (this.y + 772))));

        }
        if (this.x > x) {
            // REQUIRES: Alien and Player x , y != null
            // Modifies: The location of the alien, x and y
            // Effects: The Alien and the player are now closer (in terms of euclidean
            // distance)
            this.setX(this.x - velocity);
        } else if (this.x < x) {
            // REQUIRES: Alien and Player x , y != null
            // Modifies: The location of the alien, x and y
            // Effects: The Alien and the player are now closer (in terms of euclidean
            // distance)
            this.setX(this.x + velocity);
        }
        

    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    
}