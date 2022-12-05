package UI;

import javax.swing.*;

import Backend.GameController;

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
        GameController.setGameStatus(GameController.RUNNING);
    }

    public static void pauseGame() {
        PauseScreen pauseScreen = new PauseScreen();
        pauseScreen.setTitle("Paused");
        pauseScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pauseScreen.setSize(960, 540);
        pauseScreen.setVisible(true);

    }

    public static void main(String[] a) {
        login();
    }

}
