package UI;

import javax.swing.*;

import Backend.GameControler;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JFrame {
    Container container = getContentPane();
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
    }

    public void setLocationAndSize() {
        background.setBounds(0, 0, 960, 540);
        playerFront.setBounds(100, 100, 100, 100);
        playerBack.setBounds(100, 100, 100, 100);
        playerLeft.setBounds(100, 100, 100, 100);
        playerRight.setBounds(100, 100, 100, 100);

    }

    public void addComponentsToContainer() {
        container.add(playerBack);
        container.add(playerLeft);
        container.add(playerRight);
        container.add(playerFront);
        container.add(background);
    }

    public void addActionEvent() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    if (e.getKeyCode() == KeyEvent.VK_UP && playerBack.getY() >= 0) {
                        playerBack.setVisible(true);
                        playerFront.setVisible(false);
                        playerLeft.setVisible(false);
                        playerRight.setVisible(false);

                        playerBack.setBounds(playerBack.getX(), playerBack.getY() - 10, 100, 100);
                        playerFront.setBounds(playerBack.getX(), playerBack.getY() - 10, 100, 100);
                        playerLeft.setBounds(playerBack.getX(), playerBack.getY() - 10, 100, 100);
                        playerRight.setBounds(playerBack.getX(), playerBack.getY() - 10, 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN
                            && playerFront.getY() + playerFront.getHeight() <= background.getHeight()) {
                        playerBack.setVisible(false);
                        playerFront.setVisible(true);
                        playerLeft.setVisible(false);
                        playerRight.setVisible(false);

                        playerBack.setBounds(playerBack.getX(), playerBack.getY() + 10, 100, 100);
                        playerFront.setBounds(playerBack.getX(), playerBack.getY() + 10, 100, 100);
                        playerLeft.setBounds(playerBack.getX(), playerBack.getY() + 10, 100, 100);
                        playerRight.setBounds(playerBack.getX(), playerBack.getY() + 10, 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && playerLeft.getX() >= 0) {
                        playerBack.setVisible(false);
                        playerFront.setVisible(false);
                        playerLeft.setVisible(true);
                        playerRight.setVisible(false);

                        playerBack.setBounds(playerBack.getX() - 10, playerBack.getY(), 100, 100);
                        playerFront.setBounds(playerBack.getX() - 10, playerBack.getY(), 100, 100);
                        playerLeft.setBounds(playerBack.getX() - 10, playerBack.getY(), 100, 100);
                        playerRight.setBounds(playerBack.getX() - 10, playerBack.getY(), 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT
                            && playerRight.getX() + playerRight.getWidth() <= background.getWidth()) {
                        playerBack.setVisible(false);
                        playerFront.setVisible(false);
                        playerLeft.setVisible(false);
                        playerRight.setVisible(true);

                        playerBack.setBounds(playerBack.getX() + 10, playerBack.getY(), 100, 100);
                        playerFront.setBounds(playerBack.getX() + 10, playerBack.getY(), 100, 100);
                        playerLeft.setBounds(playerBack.getX() + 10, playerBack.getY(), 100, 100);
                        playerRight.setBounds(playerBack.getX() + 10, playerBack.getY(), 100, 100);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_P) {
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
