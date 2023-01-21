package Backend.GameObjects;

import javax.swing.*;

import Backend.GameControler;
import UI.BuildMode;
import UI.UIUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Key {

    private int x = 0;
    private int y = 0;
    private boolean revealed = false;
    private boolean isrevealable = true;
    private Random spawnGenerator = new Random();


    public boolean getRevealed() {
        return revealed;
    }

    public JLabel reveal() {
        if (!isrevealable) {
            return new JLabel();
        }
        revealed = true;

        return getObjectLabel();
    }

    public JLabel getObjectLabel(){
        return GameControler.getObjectLabel("EscapeFromKoc/resources/RoomObjects/key.png", "", x, y, 50, 50);
    }


    public void setRevealable(boolean revealable) {
        isrevealable = revealable;
    }


    private boolean placed = false;
    public boolean isPlaced(){
        return placed;
    }

    public Key() {
        new Thread(){
            public void run(){
                while(GameControler.getBuiltObjects().size() < GameControler.getRequiredObjSize()){
                    System.out.println("Waiting for limit");
                    try{
                        sleep(500);
                    }catch(InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
                ArrayList<GameObjectIntterface> builtList = GameControler.getBuiltObjects();
                System.out.println(builtList.toString());
                int spawnIdx = spawnGenerator.nextInt(0, builtList.size());
        
                GameObjectIntterface tangentObj = builtList.get(spawnIdx);
                tangentObj.addKey();

                System.out.println("Key at " + tangentObj.getName() + " X: "+ tangentObj.getX() + " Y: " + tangentObj.getY()) ;
                spawnKey(tangentObj.getX(), tangentObj.getY());
                placed = true;
                return; 
            }
        }.start();
        
        

    }

    private void spawnKey(int x , int y){
        this.x = x;
        this.y = y;

    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean getRevealable() {
        return isrevealable;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }




}
