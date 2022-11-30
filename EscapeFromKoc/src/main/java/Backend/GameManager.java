package Backend;

import UI.Register;

import javax.swing.*;
import java.awt.*;

public class GameManager {
    private static boolean loggedIn = false;

    public static void login() {
        Register frame = new Register();
        frame.setTitle("Register");
        frame.setBounds(10, 10, 370, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void startGame() {
        JFrame frame = new JFrame("Game");
        frame.setTitle("Game will be started here");
        frame.setVisible(true);
        frame.setBounds(20, 10, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] a) {
        login();
    }
}
