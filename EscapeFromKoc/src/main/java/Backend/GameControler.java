package Backend;

import Backend.GameObjects.Chair;
import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.Key;
import Backend.GameObjects.ObjectFactory;
import Backend.GameObjects.Aliens.Alien;
import Backend.GameObjects.Aliens.BlindAlienImpl;
import Backend.GameObjects.PowerUps.*;
import Backend.Player.Inventory;
import Backend.Player.Player;
import Backend.SaveLoad.Load;
import Backend.SaveLoad.Save;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;


import javax.swing.JLabel;
import javax.swing.UIDefaults.ProxyLazyValue;

import UI.Board;
import UI.BuildMode;
import UI.ScreenCoordinator;
import UI.UIUtils;

public class GameControler {
    public static int PAUSED = 0;
    public static int RUNNING = 1;
    public static int GAMEOVER = 2;
    private static int gameStatus;
    private static int level = 1;
    private static Player p = Player.getPlayer();
    private static int score = 0;
    
    private static ArrayList<GameObjectIntterface> gameObjectList = new ArrayList<GameObjectIntterface>();


    private static Board activeBoard = new Board();

    private static BuildMode actBuildMode = new BuildMode(5);
    
    private static ArrayList<Integer> atleastList = new ArrayList<Integer>(Arrays.asList(5,7,10,14,19,25));



    public static int EXIT = 3;
    public static int levelTime = 50;
    public static long startTime;
    public static long currentTime;



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

    public static int getSaveNumber() {
        return Save.getSaveNumber();
    }

    public static void saveGame() {
        Save.setSaveNum();
        Save.saveGame();
    }

    public static void selectSaveFile(String name){
        Save.setSaveName(name);
    }

    public static String getSaveName(){
        return Save.getSaveName();
    }

    public static void startGame(){
        gameStatus = RUNNING;
        activeBoard.setBackground();
        ScreenCoordinator.startGame(activeBoard);
        activeBoard.printContainer();
    }

    public static int getPlayerHealth() {
        return p.getHealth();
    }

    public static ArrayList<String> getObjects(){
        return activeBoard.getObjects();
    }

    public static int[] getObjectCoords(String object){
        return activeBoard.getObjectCoords(object);
    }

    public static void loadGame() throws NumberFormatException, IOException{
        //Load the game, including the player's current position, inventory, health, current level, location of the objects in the level, etc.
        System.out.println("Loading game...");
        Load.loadGame();
        System.out.println("Game loaded!");
    }

    public static void saveBuildMode() throws NumberFormatException, IOException{
        Save.setSaveNum();
        Save.saveBuildMode();
        Load.loadGame();
    }

    public static void setSaveMethod(String method){
        if(method.equalsIgnoreCase("mongodb")){
            Save.setSaveMethod(true);
        }
        else{
            Save.setSaveMethod(false);
        }
    }

    public static ArrayList<GameObjectIntterface> getBuiltObjects(){
        return gameObjectList;
    }

    public static int[] getBuiltObjectCoords(String object){
        return actBuildMode.getBuiltObjectCoords(object);
    }

    public static void nextLevel(){
        if (level < 7){
            level++;
            actBuildMode = new BuildMode(level);
            activeBoard = new Board();
            buildGame();
        }
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
        if(p.hasKey() && (p.getX() >= 180 && p.getX() <= 300) && (p.getY() >= 350 && p.getY() <= 540)){
            System.out.println("You have reached the exit!");
            p.deleteKey();
            nextLevel();
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

    public static void setPlayerHealth(int health) {
        p.setHealth(health);
    }

    public static Player getPlayer() {
        return Player.getPlayer();
    }

    public static void setCurrentLevel(int level) {
        GameControler.level = level;
    }

    public static void pickObject(Object obj) {
        p.getInventory().addItem(obj);
        if (obj instanceof AddHealthImpl) {
            p.usePowerUp((AddHealthImpl) obj);
        }

        if (obj instanceof ExtraTime){
            p.usePowerUp((ExtraTime) obj);
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

    public static JLabel getObjectLabel(String path, String dir,int x, int y,int width, int height){
        return UIUtils.createLabel(path + dir + ".png", x, y, width, height);
    }

    public static void setLabelImage(JLabel label, String path, String dir,int width,int height){
        UIUtils.setLabelImage(label, path + dir + ".png", 96, 54);

    }


    public static Chair createFurniture() {
        Chair chair = (Chair) ObjectFactory.createObject("chair", 300, 300);
        Key key = new Key(); 
        key.spawnKey(chair.getX(), chair.getY());
        return chair;
    }


    /*public static PowerUp createPowerUp(String type, int x, int y) {
        if(type.equals("health")){
            AddHealthImpl health = new AddHealthImpl(x,y);
            return health;
        } else if (type.equals("hint")) {
            HintPowerUp hint = new HintPowerUp(x, y);

            return hint;
        } else if (type.equals("extra-time")) {
            ExtraTime extraTime =  new ExtraTime(x, y);
            return extraTime;
        } else return null;

    }*/

    public static Alien createAlien(String type, int x , int y) {
        if (type.equals("blind")) {
            BlindAlienImpl blindAlien = (BlindAlienImpl) ObjectFactory.createObject("blind-alien", x, y);
            return blindAlien;
        } else return null;
    }

    public static String getPlayerInventory(){
        return p.getInventory().toString();
    }

    public static int getCurrentLevel(){
        return level;
    }

    public static void addObject(String name , int x, int y){
        //put the creaded object in the activeBoard
        GameObjectIntterface obj = ObjectFactory.createObject(name, x, y);
        gameObjectList.add(obj);
        activeBoard.addObject(obj);
    }
    

    public static void bottleThrowAnimation(int[] playerCoords, int[] newCoords, Board board) {
        System.out.println("animating bottle throw");
        int x = playerCoords[0];
        int y = playerCoords[1];
        int x2 = newCoords[0];
        int y2 = newCoords[1];
        int dx = x2 - x;
        int dy = y2 - y;
        int steps = 7;
        double xIncr = (double) dx / (double) steps;
        double yIncr = (double) dy / (double) steps;
        for (int i = 0; i < steps; i++) {
            x += xIncr;
            y += yIncr;
            try {

                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.moveObject("bottle", (int) x, (int) y);
            board.applyBottledAlienGoal();
            board.setBottleLabelVisiable(true);
            board.repaint();
        }
        board.setBottleThrown(false);
        System.out.println("bottle thrown animation completed: " + board.getBottleThrown());
    }

    public static int getLevelTime() {
        return levelTime;
    }

    public static void setLevelTime(int levelTime) {
        GameControler.levelTime = levelTime;
    }

    public static void setScore(int score) {
        GameControler.score = score;
    }

    public static void buildGame(){
        ScreenCoordinator.buildGame(actBuildMode);
    }

}
