package Backend;

import Backend.Player.Player;

import java.awt.*;

public class GameControler {
    public static int PAUSED = 0;
    public static int RUNNING = 1;
    private static int GAMEOVER = 2;
    private static int gameStatus;


    private static Player p = Player.getPlayer();
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

    public static Image movePlayer(String trajectory){
            Image trajectoryImg = p.getPlayerImg(trajectory);
            if(trajectory.equalsIgnoreCase("Front")){
                p.setY(p.getY() + p.getVelocity());
            } else if(trajectory.equalsIgnoreCase("back")){
                p.setY(p.getY() - p.getVelocity());
            } else if (trajectory.equalsIgnoreCase("Left")){
                p.setX(p.getX() - p.getVelocity());
            } else if(trajectory.equalsIgnoreCase("right")) {
                p.setX(p.getX() + p.getVelocity());
            }
            return trajectoryImg;

    }
    public static int[] getPlayerCoords(){
        int[] coords = {p.getX(),p.getY()};
        return coords;
    }



}
