package Backend.SaveLoad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoSocketException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Backend.GameControler;
import Backend.Player.Player;

public class Load {
    private static File file = new File("EscapeFromKoc/resources/Save"+ GameControler.getSaveName() + GameControler.getSaveNumber() + ".txt");
    private static File prevFile = new File("EscapeFromKoc/resources/Save"+ GameControler.getSaveName() + (GameControler.getSaveNumber()) + ".txt");
    private static BufferedReader br;

    private static String saveMethod = Save.getSaveMethod();
    private static MongoClient clientGlobal = null;

    public static void loadGame() throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        if(saveMethod.equals("MongoDB")){
            readDatabase();
        } else if(saveMethod.equals("Plaintext")){
            readFile();
        }
    }

    public static void loadPrevGame() throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        if(saveMethod.equals("MongoDB")){
            readDatabase();
        } else if(saveMethod.equals("Plaintext")){
            file = prevFile;
            GameControler.setNewBoard();
            readFile();
        }
    }

    public static void readFile() throws NumberFormatException, IOException{
        br = new BufferedReader(new FileReader(file));
        System.out.println("reading: " + file.getName() + "");
        while (true) {
            try {
                if (!((br.readLine()) != null)) break;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String newLine = br.readLine();
            if(newLine.contains("Level")){
                GameControler.setCurrentLevel(Integer.parseInt(newLine.substring(6)));
            }
            if(newLine.contains("Player")){
                if(newLine.contains("coordinates")){
                    
                    String line = newLine;
                    String x = line.substring(line.indexOf(":") + 2, line.indexOf(","));
                    String y = line.substring(line.indexOf(",") + 2);
                    GameControler.getPlayer().setX(Integer.parseInt(x));
                    GameControler.getPlayer().setY(Integer.parseInt(y));
                }
                if(newLine.contains("health")){
                    
                    GameControler.getPlayer().setHealth(Integer.parseInt(newLine.substring(7)));
                }
                if(newLine.contains("inventory")){
                    while(!newLine.contains("End of inventory")){
                        GameControler.getPlayer().setInventory(newLine);
                        newLine = br.readLine();
                    }
                }
                
            }
            if(newLine.contains("Inventory")){
                while(!newLine.contains("End of inventory")){
                    newLine = br.readLine();
                    GameControler.getPlayer().setInventory(newLine);
                }
            }
            if(newLine.contains("Object")){
                while(!newLine.contains("End of objects")){
                    newLine = br.readLine();
                    // GameController.addObject(name, x, y)
                    String line = newLine;
                    if(line.contains("End of objects")){
                        break;
                    }
                    //System.out.println("line: " + line + " " + "    index of : : "+ line.indexOf(":") + "    index of , : " + line.indexOf(","));
                    String name = line.substring(0, line.indexOf(":"));
                    String x = line.substring(line.indexOf(":") + 2, line.indexOf(","));
                    String y = line.substring(line.indexOf(",") + 2);
                    GameControler.addObject(name, Integer.parseInt(x), Integer.parseInt(y));
                }
            }
            else{
                break;
            }
        }
        
    }

    public static void readDatabase(){
        MongoDatabase database = mongoInit();
        MongoCollection<Document> collection  = database.getCollection("Level"+GameControler.getCurrentLevel());


        Document playerDoc = collection.find(eq("title", "Player")).first();
        
        String coordString = (String) playerDoc.get("coordinates");
        String[] coords = coordString.replaceAll("[\\[\\](){}]", "").split(", ");
        int[] coordsInt = {Integer.parseInt(coords[0]) , Integer.parseInt(coords[1])};
        
        String invString =  (String) playerDoc.get("inventory");
        String[] items = invString.replaceAll("[\\[\\](){}]", "").split(", ");

        int health = (int) playerDoc.get("health");



        Player.getPlayer().setHealth(health);
        Player.getPlayer().setX(coordsInt[0]);
        Player.getPlayer().setY(coordsInt[1]);
        
        for(String item: items){
            Player.getPlayer().setInventory(item);
        }
        

        FindIterable<Document> objectDocIter = collection.find(eq("title", "GameObjectIntterface"));

        for(Document objDocument : objectDocIter){
            String nameString = (String) objDocument.get("mark");
            
            String objCoordString = (String) objDocument.get("coordinates");
            String[] objCoord = objCoordString.replaceAll("[\\[\\](){}]", "").split(", ");
            int[] coordInt = {Integer.parseInt(objCoord[0]) , Integer.parseInt(objCoord[1])};
            System.out.println("coords of " + nameString + " " + coordInt[0] + " " + coordInt[1]);
            
            GameControler.addObject(nameString, coordInt[0], coordInt[1]);
            
        }
        mongoClose();
    }

    private static MongoDatabase mongoInit() throws MongoSocketException{
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            clientGlobal = mongo;
            return mongo.getDatabase(GameControler.getSaveName()); 
    
        } catch (MongoSocketException e) {
            // TODO: handle exception
            saveMethod = "Plaintext";
            throw new MongoSocketException("Connection failed", null);
        }
        
    }

    private static void mongoClose(){
        clientGlobal.close();
    }
    
}

