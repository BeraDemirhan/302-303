package Backend.SaveLoad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Backend.GameControler;
import Backend.GameObjects.GameObjectIntterface;

public class Save {
    private static int saveNumber = 1;
    private static File saveFile = new File("EscapeFromKoc/resources/Save" + saveNumber + ".txt");
    private static File NamesandPasswords = new File("EscapeFromKoc/resources/NamesPasswords.txt");

    //Save the game, including the player's current position, inventory, health, current level, location of the objects in the level, etc.


    public static void saveGame(){
        //Save the game, including the player's current position, inventory, health, current level, location of the objects in the level, etc.
        System.out.println("Saving game...");
        saveLevel();
        savePlayer();
        saveObjects();
        System.out.println("Game saved!");
    }

    public static void setSaveNum(){
        //Set the save number to the next available save slot
        int i = 1;
        while(true){
            saveFile = new File("EscapeFromKoc/resources/Save" + i + ".txt");
            if(saveFile.exists()){
                i++;
            }else{
                saveNumber = i;
                break;
            }
        }
    }

    public static void write(String text){
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(saveFile, true));
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void saveLevel(){
        //Save the current level
        int level = GameControler.getCurrentLevel();
        write("Level: " + level);
    }

    public static void savePlayer(){
        //Save the player's current position, inventory, health, etc.
        int[] coords = GameControler.getPlayerCoords();
        write("Player's coordinates: " + coords[0] + ", " + coords[1]);
        String inventory = GameControler.getPlayerInventory();
        write("Player's inventory: " + inventory);
        int health = GameControler.getPlayerHealth();
        write("Player's health: " + health);
    }

    public static void saveObjects(){
        //Save the location of the objects in the level
        write("Objects:");
        for(String i : GameControler.getObjects()){
            int[] coords = GameControler.getObjectCoords(i);
            write("Object " + i + ": " + coords[0] + ", " + coords[1]);
        }
        write("End of objects");
        
    }

    public ArrayList<String> read(File file){
        //Read the file
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        String line = null;
        ArrayList<String> lines = new ArrayList<String>();
        while (true) {
            try {
                if ((line = br.readLine()) == null)
                    break;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            lines.add(line);
        }
        return lines;
    }

    public void saveNamesandPasswords(){
        //Save the names and passwords of the users
        write("Names and passwords:");
        write(read(NamesandPasswords).toString());
    }
    
}
