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
            if (br.readLine().equals("Level: ")) {
                GameControler.setCurrentLevel(Integer.parseInt(br.readLine()));

        }
        
    }
    
}
}
