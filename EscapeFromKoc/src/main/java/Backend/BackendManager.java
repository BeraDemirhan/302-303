package Backend;

public class BackendManager {
    public static int PAUSED = 0;
    public static int RUNNING = 1;
    private static int GAMEOVER = 2;
    private static int gameStatus;

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
        BackendManager.gameStatus = gameStatus;
    }

}
