package UI;

import javax.swing.*;

import Backend.GameControler;

public class ScreenCoordinator {

    public static void login() {
        Register frame = new Register();
        frame.setTitle("Register");
        frame.setBounds(10, 10, 370, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void startGame(Board frame) {
        frame.setTitle("Escape From Koç");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
    }

    public static void pauseGame() {
        PauseScreen pauseScreen = new PauseScreen();
        pauseScreen.setTitle("Paused");
        pauseScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pauseScreen.setSize(960, 540);
        pauseScreen.setVisible(true);

    }

    public static void buildGame(BuildMode buildMode) {
        buildMode.setTitle("Build Mode");
        buildMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMode.setSize(960, 540);
        buildMode.setVisible(true);
    }

    public static void gameOver(){
        GameOverScreen gameOverScreen = new GameOverScreen();
        gameOverScreen.setTitle("Game Over");
        gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverScreen.setSize(960, 540);
        gameOverScreen.setVisible(true);
    }

    public static void win(){
        WinScreen winScreen = new WinScreen();
        winScreen.setTitle("You Win");
        winScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winScreen.setSize(960, 540);
        winScreen.setVisible(true);
    }

    public static void loadOptions() {
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setTitle("Load Options");
        loadOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadOptions.setSize(960, 540);
        loadOptions.setVisible(true);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void main(String[] a) {
        login();
    }

}
