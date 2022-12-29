package Backend;

import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.PowerUps.AddHealthImpl;
import Backend.GameObjects.PowerUps.PowerUp;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import Backend.Player.Inventory;
import Backend.Player.Player;

import java.awt.*;

import javax.swing.UIDefaults.ProxyLazyValue;

import UI.ScreenCoordinator;

public class GameControler {
    public static int PAUSED = 0;
    public static int RUNNING = 1;
    private static int GAMEOVER = 2;
    private static int gameStatus;

    private static Player p = Player.getPlayer();
    public static int EXIT = 3;

    /*
     * BackendManager singleton = null;
     * public BackendManager(){
     * if(singleton == null){
     * singleton = this;
     * }
     * }
     * public BackendManager getBackendManager(){
     * return singleton;
     * }
     */
    public static int getGameStatus() {
        return gameStatus;
    }

    public static void setGameStatus(int gameStatus) {
        GameControler.gameStatus = gameStatus;
    }

    public static Image movePlayer(String trajectory) {
        Image trajectoryImg = p.getPlayerImg(trajectory);
        if (trajectory.equalsIgnoreCase("Front")) {
            //
            // p.setY((int) (p.getY() + p.getVelocity()*Math.cos(Math.atan((float) 5/24))));
            // p.setX((int) (p.getX() - p.getVelocity()*Math.sin(Math.atan((float) 5/24))));

            p.setY(p.getY() + p.getVelocity());
            p.setX((int) (p.getX() - p.getVelocity() * ((float) (430 - p.getX()) / (p.getY() + 772))));

        } else if (trajectory.equalsIgnoreCase("back")) {
            p.setY(p.getY() - p.getVelocity());
            p.setX((int) (p.getX() + p.getVelocity() * ((float) (430 - p.getX()) / (p.getY() + 772))));

        } else if (trajectory.equalsIgnoreCase("Left")) {
            p.setX(p.getX() - p.getVelocity());
        } else if (trajectory.equalsIgnoreCase("right")) {
            p.setX(p.getX() + p.getVelocity());
        }
        return trajectoryImg;

    }
    /*
     * public static void moveObject(String trajectory, ThrowBottleImpl bottle){
     * if (trajectory.equalsIgnoreCase("Front")) {
     * bottle.setY(bottle.getY() + bottle.getVelocity());
     * bottle.setX((int) (bottle.getX() - bottle.getVelocity() * ((float) (430 -
     * bottle.getX()) / (bottle.getY() + 772))));
     * 
     * } else if (trajectory.equalsIgnoreCase("back")) {
     * bottle.setY(bottle.getY() - bottle.getVelocity());
     * bottle.setX((int) (bottle.getX() + bottle.getVelocity() * ((float) (430 -
     * bottle.getX()) / (bottle.getY() + 772))));
     * 
     * } else if (trajectory.equalsIgnoreCase("Left")) {
     * bottle.setX(bottle.getX() - bottle.getVelocity());
     * } else if (trajectory.equalsIgnoreCase("right")) {
     * bottle.setX(bottle.getX() + bottle.getVelocity());
     * }
     * 
     * }
     */

    public static void usePowerUp(PowerUp pu) {
        p.usePowerUp(pu);
    }

    public static void pickObject(Object obj) {
        p.getInventory();
        Inventory.addItem(obj);
        if (obj instanceof AddHealthImpl) {
            p.usePowerUp((AddHealthImpl) obj);
        }
    }

    public static int[] getPlayerCoords() {
        int[] coords = { p.getX(), p.getY() };
        return coords;
    }

    public static void exit() {
        ScreenCoordinator.exit();
    }



    public static void applyAlienGoal(Alien a){
        a.applyAlienGoal(p);
    }
    public static void applyAlienGoal(Alien a, ThrowBottleImpl bottle){
        a.applyAlienGoal(bottle);
    }

}
