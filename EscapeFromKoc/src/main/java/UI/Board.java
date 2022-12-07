package UI;

import javax.swing.*;

import Backend.GameControler;
import Backend.GameObjects.Chair;
import Backend.GameObjects.Key;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JFrame {
    Container container = getContentPane();

    private JLabel background;
    private JLabel playerFront;
    private JLabel playerBack;
    private JLabel playerLeft;
    private JLabel playerRight;
    private JLabel chair;

    private Image backimage = new ImageIcon("EscapeFromKoc/resources/room.png").getImage();
    private Image playerfrontimage = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private Image playerbackimage = new ImageIcon("EscapeFromKoc/resources/rabbit-back-angled.png").getImage();
    private Image playerleftimage = new ImageIcon("EscapeFromKoc/resources/rabbit-left-angled.png").getImage();
    private Image playerrightimage = new ImageIcon("EscapeFromKoc/resources/rabbit-right-angled.png").getImage();

    private Image playerAbsImage = new ImageIcon(GameControler.movePlayer("back")).getImage();
    private JLabel playerAbs;

    private Container pCont = getContentPane();
    private Key key = new Key();

    public Board() {
        imageResize();
        setLayoutManager();
        loadImages();
        setLocationAndSize();
        createFurniture();
        addComponentsToContainer();
        addActionEvent();
    }

    private void imageResize() {
        backimage = backimage.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        playerfrontimage = playerfrontimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerbackimage = playerbackimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerleftimage = playerleftimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerrightimage = playerrightimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerAbsImage = singleImageResize(playerAbsImage);
    }

    private Image singleImageResize(Image img) {
        return img.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    private void loadImages() {
        background = new JLabel(new ImageIcon(backimage));
        playerFront = new JLabel(new ImageIcon(playerfrontimage));
        playerBack = new JLabel(new ImageIcon(playerbackimage));
        playerLeft = new JLabel(new ImageIcon(playerleftimage));
        playerRight = new JLabel(new ImageIcon(playerrightimage));
        playerAbs = new JLabel(new ImageIcon(playerAbsImage));
    }

    public void setLocationAndSize() {
        background.setBounds(0, 0, 960, 540);
        playerFront.setBounds(100, 100, 100, 100);
        playerBack.setBounds(100, 100, 100, 100);
        playerLeft.setBounds(100, 100, 100, 100);
        playerRight.setBounds(100, 100, 100, 100);
        playerAbs.setBounds(GameControler.getPlayerCoords()[0], GameControler.getPlayerCoords()[1], 100, 100);

    }

    public void addComponentsToContainer() {
        pCont.add(playerAbs);
        pCont.add(chair);
        pCont.add(background);
    }

    public void createFurniture() {
        chair = new Chair(300, 300).getChair();
        key.spawnKey(300, 300);
    }

    public void addActionEvent() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                super.keyPressed(e);
                playerAbs.setVisible(false);
                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    int[] oldCoords = GameControler.getPlayerCoords();
                    if (e.getKeyCode() == KeyEvent.VK_UP && oldCoords[1] >= background.getY() + 140) {
                        Image newImg = singleImageResize(GameControler.movePlayer("back"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN
                            && oldCoords[1] + playerFront.getHeight() <= background.getHeight() - 170 + playerAbs.getHeight()) {
                        Image newImg = singleImageResize(GameControler.movePlayer("front"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && oldCoords[0] >= (background.getX() + 240) - ((float) 5/24)*(oldCoords[1] - 140) ) {
                        Image newImg = singleImageResize(GameControler.movePlayer("left"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT
                            && oldCoords[0] + playerRight.getWidth() <= (background.getWidth() - 240)  + ((float) 5/24)*(oldCoords[1] - 140) ) {
                        Image newImg = singleImageResize(GameControler.movePlayer("right"));
                        int[] newCoords = GameControler.getPlayerCoords();
                        playerAbs = new JLabel(new ImageIcon(newImg));
                        playerAbs.setBounds(newCoords[0], newCoords[1], 100, 100);
                    }

                    pCont.removeAll();
                    addComponentsToContainer();
                    playerAbs.setVisible(true);
                    System.out.println("player x:" + playerAbs.getX() +  "player y:" + playerAbs.getY());
                    System.out.println("bg width:" + background.getWidth() +  "background height:" + background.getHeight());

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
