package Backend;

import UI.Board;
import UI.Register;

import javax.swing.*;

public class GameManager {

    public static void login() {
        Register frame = new Register();
        frame.setTitle("Register");
        frame.setBounds(10, 10, 370, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void startGame() {
        Board frame = new Board();
        frame.setTitle("Escape From Koc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] a) {
        // login();
        startGame();
    }

}
