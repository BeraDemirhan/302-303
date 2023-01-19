package Backend.SaveLoad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Backend.GameControler;

public class Load {
    private static File file = new File("save.txt");
    private static BufferedReader br;
    public static void loadGame() {
        // TODO Auto-generated method stub

    }

    public static void readFile() throws NumberFormatException, IOException{
        br = new BufferedReader(new FileReader(file));
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
                GameControler.getPlayer().setX(Integer.parseInt(newLine.substring(7, 10)));
                GameControler.getPlayer().setY(Integer.parseInt(newLine.substring(11, 14)));
                GameControler.getPlayer().setHealth(Integer.parseInt(newLine.substring(15, 18)));
                GameControler.getPlayer().setInventory(newLine.substring(19));
            }
            if(newLine.contains("Object")){
                while(!newLine.contains("End of objects")){
                    newLine = br.readLine();
                    GameControler.addObject(newLine.substring(7, 8), Integer.parseInt(newLine.substring(9, 12)), Integer.parseInt(newLine.substring(13, 16)));
                }
            }

        }
        
    }
    
}

