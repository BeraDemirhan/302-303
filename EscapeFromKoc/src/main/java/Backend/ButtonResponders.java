package Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

public class ButtonResponders {
    private static File file = new File("EscapeFromKoc/src/main/java/Backend/NamesPasswords.txt");

    public static boolean LoginButton(String username, String password) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        String line = null;
        while (true) {
            try {
                if ((line = br.readLine()) == null)
                    break;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String[] info = null;
            String rgx = "thisissplitter";
            info = line.split(rgx);
            if (username.equals(info[0]) && password.equals(info[1])) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    public static boolean SignUpButton(String username, String password) throws IOException {
        BufferedReader br = null;
        boolean valid = true;
        String namePw = new String();
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        String line = null;
        while (true) {
            try {
                if ((line = br.readLine()) == null)
                    break;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String[] info = null;
            String rgx = "thisissplitter";
            info = line.split(rgx);
            if (username.equals(info[0])) {
                valid = false;
            }
        }
        if (valid) {
            namePw += username;
            namePw += "thisissplitter";
            namePw += password;
            PrintWriter out = new PrintWriter(new FileOutputStream(file, true));
            out.append(namePw);
            out.append(System.lineSeparator());
            out.close();
        }
        br.close();
        return valid;
    }

}
