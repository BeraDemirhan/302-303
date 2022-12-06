package UI;

import javax.swing.*;

import Backend.GameControler;
import Backend.GameObjects.PowerUps.AddHealthImpl;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JFrame {
    private Dimension d;
    private JLabel background;
    private JLabel playerFront;
    private JLabel playerBack;
    private JLabel playerLeft;
    private JLabel playerRight;

    private Image backimage = new ImageIcon("EscapeFromKoc/resources/room.png").getImage();
    private Image playerfrontimage = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private Image playerbackimage = new ImageIcon("EscapeFromKoc/resources/rabbit-back-angled.png").getImage();
    private Image playerleftimage = new ImageIcon("EscapeFromKoc/resources/rabbit-left-angled.png").getImage();
    private Image playerrightimage = new ImageIcon("EscapeFromKoc/resources/rabbit-right-angled.png").getImage();




    private Image playerAbsImage = new ImageIcon(GameControler.movePlayer("front")).getImage();
    private JLabel playerAbs;


    private AddHealthImpl healthPowerUp = new AddHealthImpl(150, 150);
    private Image healthImage = new ImageIcon(healthPowerUp.getImg()).getImage();
    private JLabel healthLabel;
    // add this to gamecontroller later on !! for demo only

    private Container pCont = getContentPane();


    public Board() {
        imageResize();
        setLayoutManager();
        loadImages();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    private void imageResize() {
        backimage = backimage.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        playerfrontimage = playerfrontimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerbackimage = playerbackimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerleftimage = playerleftimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerrightimage = playerrightimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);

        // changed
        playerAbsImage = singleImageResize(playerAbsImage);
        healthImage = healthImage.getScaledInstance(50,50, Image.SCALE_SMOOTH);

    }

    private Image singleImageResize(Image img){
        return img.getScaledInstance(54,96, Image.SCALE_SMOOTH);
    }
    public void setLayoutManager() {
        pCont.setLayout(null);
    }

    private void loadImages() {
        background = new JLabel(new ImageIcon(backimage));
        playerFront = new JLabel(new ImageIcon(playerfrontimage));
        playerBack = new JLabel(new ImageIcon(playerbackimage));
        playerLeft = new JLabel(new ImageIcon(playerleftimage));
        playerRight = new JLabel(new ImageIcon(playerrightimage));

        playerAbs = new JLabel(new ImageIcon(playerAbsImage));
        healthLabel = new JLabel(new ImageIcon(healthImage));
    }

    public void setLocationAndSize() {
        background.setBounds(0, 0, 960, 540);
        playerFront.setBounds(100, 100, 100, 100);
        playerBack.setBounds(100, 100, 100, 100);
        playerLeft.setBounds(100, 100, 100, 100);
        playerRight.setBounds(100, 100, 100, 100);


        playerAbs.setBounds(100,100,100,100);
        healthLabel.setBounds(healthPowerUp.getX(), healthLabel.getY(),100,100);

    }

    public void addComponentsToContainer() {
        pCont.add(playerAbs);
        pCont.add(healthLabel);
        pCont.add(background);
    }

    public void addActionEvent() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                playerAbs.setVisible(false);
                pCont.removeAll();

                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    int[] oldCoords = GameControler.getPlayerCoords();
                    if (e.getKeyCode() == KeyEvent.VK_UP && oldCoords[1] >= 0) {
                        Image newImg = singleImageResize(GameControler.movePlayer("back"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100,100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN
                            && oldCoords[1] + playerFront.getHeight() <= background.getHeight()) {
                        Image newImg = singleImageResize(GameControler.movePlayer("front"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100,100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && oldCoords[0]>= 0) {
                        Image newImg = singleImageResize(GameControler.movePlayer("left"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100,100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT
                            && oldCoords[0] + playerRight.getWidth() <= background.getWidth()) {
                        Image newImg = singleImageResize(GameControler.movePlayer("right"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100,100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_K){
                        System.out.println("K pressed");
                        GameControler.pickItem(healthPowerUp);

                    }

                    pCont.add(playerAbs);
                    pCont.add(healthLabel);
                    pCont.add(background);

                    playerAbs.setVisible(true);

                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        System.out.println(GameControler.getGameStatus());
                        GameControler.setGameStatus(GameControler.PAUSED);
                        System.out.println(GameControler.getGameStatus());
                        System.out.println("P Pressed");
                        GameControler.setGameStatus(GameControler.PAUSED);

                        ScreenCoordinator.pauseGame();
                    }
                }
                if (GameControler.getGameStatus() == GameControler.PAUSED) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        GameControler.setGameStatus(GameControler.RUNNING);
                        ScreenCoordinator.pauseGame();
                    }
                }

            }
        });
    }
}
