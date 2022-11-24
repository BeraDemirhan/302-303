import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class GameManager {
    private static boolean loggedIn = false;
    public static void login(){
        Register frame = new Register();
        frame.setTitle("Register");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    public static void startGame(){
        JFrame frame = new JFrame("Game");
        frame.setTitle("Game will be started here");
        frame.setVisible(true);
        frame.setBounds(20, 10, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void setLoggedIn(boolean x){
        loggedIn = x;
    }
    public static void main(String[] a) {
        login();

    }
}
