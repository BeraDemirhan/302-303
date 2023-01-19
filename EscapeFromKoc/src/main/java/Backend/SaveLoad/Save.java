package Backend.SaveLoad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import Backend.GameControler;
import Backend.GameObjects.GameObjectIntterface;
import Backend.Player.Inventory;
import Backend.Player.Player;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import org.bson.Document;
import org.bson.conversions.Bson;


public class Save {
    private static int saveNumber = 1;
    private static File saveFile = new File("EscapeFromKoc/resources/Save" + saveNumber + ".txt");
    private static File NamesandPasswords = new File("EscapeFromKoc/resources/NamesPasswords.txt");
    private static PrintWriter pw = null;

    private static String saveMethod = "MongoDB";

    public static void setSaveMethod(boolean mongo) {
        if (mongo) {
            saveMethod = "MongoDB";
        } else {
            saveMethod = "Plaintext";
        }

    }

    private static void pwInit() {

        try {
            pw = new PrintWriter(saveFile);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static MongoDatabase mongoInit() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoCredential credential = MongoCredential.createCredential("sampleUser", "myDb",
                "password".toCharArray());
        return mongo.getDatabase("myDb"); 

    }

    private static void saveToDB(){
        MongoDatabase database = mongoInit();
        String collName = "Level"+GameControler.getCurrentLevel();

        database.createCollection(collName);
        MongoCollection<Document> collection = database.getCollection(collName);
  
        Document PlayerDocument = new Document("title", "Player")
        .append("coordinates", new ArrayList<Integer>(Arrays.asList(new Integer[]{GameControler.getPlayerCoords()[0], GameControler.getPlayerCoords()[1]})).toString() )
        .append("inventory",  new ArrayList<String>( Arrays.asList(Player.getPlayer().getInventoryStr().split(" ", 0))).toString() )
        .append("health", GameControler.getPlayerHealth());
        
        collection.insertOne(PlayerDocument);

        

        int o = 0;
        for (String i : GameControler.getObjects()) {
            int[] coords = GameControler.getObjectCoords(i);
            Document ObjectDocument = new Document("id", o);
            ObjectDocument.append("title", "Object").append("coordinates", new ArrayList<Integer>(Arrays.asList(new Integer[]{coords[0], coords[1]})).toString());
            o++;
            collection.insertOne(ObjectDocument);
        }
        
        //Inserting document into the collection
        
    }

    // Save the game, including the player's current position, inventory, health,
    // current level, location of the objects in the level, etc.

    public static void saveGame(){
        //Save the game, including the player's current position, inventory, health, current level, location of the objects in the level, etc.
        System.out.println("Saving game...");
        if(saveMethod.equals("Plaintext")){
            pwInit();
            saveLevel();
            savePlayer();
            saveObjects();
            pw.close();

        }else if (saveMethod.equals("MongoDB")){
            saveToDB();
        }

        System.out.println("Game saved!");
    }

    public static void setSaveNum() {
        // Set the save number to the next available save slot
        int i = 1;
        while (true) {
            saveFile = new File("EscapeFromKoc/resources/Save" + i + ".txt");
            if (saveFile.exists()) {
                i++;
            } else {
                saveNumber = i;
                break;
            }
        }
    }

    public static void write(String text) {

        pw.println(text);

    }

    public static void saveLevel() {
        // Save the current level
        int level = GameControler.getCurrentLevel();
        write("Level: " + level);
    }

    public static void savePlayer() {
        // Save the player's current position, inventory, health, etc.
        int[] coords = GameControler.getPlayerCoords();
        write("Player's coordinates: " + coords[0] + ", " + coords[1]);
        String inventory = GameControler.getPlayerInventory();
        write("Player's inventory: " + inventory);
        int health = GameControler.getPlayerHealth();
        write("Player's health: " + health);
    }

    public static void saveObjects() {
        // Save the location of the objects in the level
        write("Objects:");
        for (String i : GameControler.getObjects()) {
            int[] coords = GameControler.getObjectCoords(i);
            write("Object " + i + ": " + coords[0] + ", " + coords[1]);
        }

    }

    public ArrayList<String> read(File file) {
        // Read the file
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

    public void saveNamesandPasswords() {
        // Save the names and passwords of the users
        write("Names and passwords:");
        write(read(NamesandPasswords).toString());
    }

}
