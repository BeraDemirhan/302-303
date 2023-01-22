package Backend.GameObjects.Aliens;

import javax.swing.JLabel;

import Backend.GameControler;
import Backend.Player.Player;

public class ShooterAlienImpl implements Alien {

    private int damage = 1;
    private int x;
    private int y;
    private String dir = "Front";
    private String generalPath = "EscapeFromKoc/resources/shooterAlien";// General path does not change but dir
                                                                                 // does

    private Bullet b = new Bullet(x, y);
    private boolean bulletShot = false;

    public boolean getBulletShot(){
        return bulletShot;
    }


    private long cooldownStartTime = System.nanoTime();
    private int cooldownDuration = 4;

    public ShooterAlienImpl(int x, int y) {
        spawnObject(x, y);
    }

    @Override
    public String getName() {
        return "Shooter-alien";
    }

    @Override
    public boolean objectHasKey() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void spawnObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public JLabel getObjectLabel() {
        // TODO Auto-generated method stub
        return GameControler.getObjectLabel(generalPath,  "", x, y, 96, 54);
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return x;
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return y;
    }

    @Override
    public void applyAlienGoal(Object o) {
        // TODO Auto-generated method stub

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    //System.out.println((System.nanoTime() - cooldownStartTime)/ 1000000000);
                    if (( System.nanoTime()-cooldownStartTime) / 1000000000 < cooldownDuration) {
                        //System.out.println("sleeping");
                        try {

                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }else if (GameControler.getGameStatus() == GameControler.RUNNING){
                        attackPlayer(Player.getPlayer());
                        cooldownStartTime = System.nanoTime();

                    }else {
                        try {

                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }.start();

    }

    @Override
    public void attackPlayer(Player p) {
        // TODO Auto-generated method stub
        if (Math.sqrt(Math.pow(p.getX() - this.x, 2) + Math.pow(p.getY() - this.y, 2)) < 300) {
            System.out.println("shooting to player");
            bulletShot = true;
            //System.out.println("Alien: " + this.x + " " + this.y);
            int lastPlayerX = p.getX();
            int lastPlayerY = p.getY();
            

            new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 300 / b.getVelocity(); i++) {

                            sleep(25);
                            //System.out.println(b.getX() + " " + p.getX() + ", " + b.getY() + " " + p.getY());
                            b.moveBulletTo(lastPlayerX, lastPlayerY);
                            if(Math.sqrt(Math.pow(p.getX() - b.getX(), 2) + Math.pow(p.getY() - b.getY(), 2)) < 25 ){
                                System.out.println("player shot");
                                p.addHealth(-1);
                                break;
                            }
                            

                        }
                        bulletShot = false;
                        b.setX(x);
                        b.setY(y);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }.start();

        }

    }

    public Bullet getBullet(){
        return b;
    }

    @Override
    public void setObjectHasKey(boolean x) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addKey() {
        // TODO Auto-generated method stub
        
    }


}
