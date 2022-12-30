package UI;

import javax.swing.*;

import Backend.GameControler;
import Backend.GameObjects.Chair;
import Backend.GameObjects.Key;
import Backend.GameObjects.PowerUps.AddHealthImpl;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import Backend.Player.Inventory;
import Backend.Player.Player;
import Backend.GameObjects.GameObjectIntterface;
import Backend.GameObjects.ObjectFactory;
import Backend.GameObjects.Aliens.BlindAlienImpl;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Board extends JFrame {
    Container container = getContentPane();

    private JLabel background;
    private JLabel playerFront;
    private JLabel playerBack;
    private JLabel playerLeft;
    private JLabel playerRight;
    private JLabel chair;

    private JLabel health;

    private Image backimage = new ImageIcon("EscapeFromKoc/resources/room.png").getImage();
    private Image playerfrontimage = new ImageIcon("EscapeFromKoc/resources/rabbit-front-angled.png").getImage();
    private Image playerbackimage = new ImageIcon("EscapeFromKoc/resources/rabbit-back-angled.png").getImage();
    private Image playerleftimage = new ImageIcon("EscapeFromKoc/resources/rabbit-left-angled.png").getImage();
    private Image playerrightimage = new ImageIcon("EscapeFromKoc/resources/rabbit-right-angled.png").getImage();

    private Image playerAbsImage = new ImageIcon(GameControler.movePlayer("back")).getImage();
    private Image bottlImage = new ImageIcon("EscapeFromKoc/resources/bottle.png").getImage();
    private Image newImgPlayer;
    private JLabel playerAbs;
    private JLabel bottleLabel;

    private Container pCont = getContentPane();
    private Key key = new Key();

    private static BlindAlienImpl blindAlien;
    private JLabel blindAlienLabel;

    private static ThrowBottleImpl bottle = new ThrowBottleImpl(400, 200);
    private boolean bottleThrown = false;

    

    public Board() {
        imageResize();
        setLayoutManager();
        loadImages();
        createAlien();
        setLocationAndSize();
        createFurniture();
        createHealth();
        addComponentsToContainer();
        addActionEvent();
        GameControler.updateFrame(this);
    }

    private void imageResize() {
        backimage = backimage.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        playerfrontimage = playerfrontimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerbackimage = playerbackimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerleftimage = playerleftimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        playerrightimage = playerrightimage.getScaledInstance(54, 96, Image.SCALE_SMOOTH);
        bottlImage = bottlImage.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        playerAbsImage = singleImageResize(playerAbsImage);
        newImgPlayer = playerAbsImage;
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
        bottleLabel = new JLabel(new ImageIcon(bottlImage));
    }

    public void setLocationAndSize() {
        background.setBounds(0, 0, 960, 540);
        playerFront.setBounds(100, 100, 100, 100);
        playerBack.setBounds(100, 100, 100, 100);
        playerLeft.setBounds(100, 100, 100, 100);
        playerRight.setBounds(100, 100, 100, 100);
        playerAbs.setBounds(GameControler.getPlayerCoords()[0], GameControler.getPlayerCoords()[1], 100, 100);
        blindAlienLabel.setBounds(blindAlien.getX(), blindAlien.getY(), 100, 100);
        bottleLabel.setBounds(bottle.getX(), bottle.getY(), 100, 100);

    }

    public void addComponentsToContainer() {
        if (key.getRevealed()) {
            System.out.println("Key revealed");
            pCont.add(key.reveal());
        }
        pCont.add(playerAbs);
        pCont.add(chair);
        pCont.add(health);
        pCont.add(blindAlienLabel);
        pCont.add(bottleLabel);
        pCont.add(background);
    }

    public void createFurniture() {
        chair = GameControler.createFurniture().getObjectLabel();
    }

    public void createHealth() {
        //health = GameControler.createPowerUp("health", 400, 400);
        health = new AddHealthImpl(100, 100).getHealth();
    }

    public void createAlien(){
        blindAlien = (BlindAlienImpl) GameControler.createAlien("blind",200, 200);
        blindAlienLabel = blindAlien.getObjectLabel();
    }

    public void repaint(){
        pCont.repaint();
    }
   
    public void setBottleLabelVisiable(boolean visible) {
        bottleLabel.setVisible(visible);
    }

    public void setBottleThrown(boolean thrown) {
        bottleThrown = thrown;
    }

    public void applyBottledAlienGoal() {
         GameControler.applyAlienGoal(blindAlien,bottle);
    }

    public void moveObject(String type, int x, int y) {
        if (type.equals("bottle")) {
            bottleLabel.setBounds(x, y, 100, 100);
        }
    }

    public void playerAbs(){
        playerAbs.setIcon(new ImageIcon(newImgPlayer));
        playerAbs.setBounds(GameControler.getPlayerCoords()[0], GameControler.getPlayerCoords()[1], 100, 100);
        playerAbs.setVisible(true);
    }

    public void blindAlienAbs(){
        blindAlienLabel.setBounds(blindAlien.getX(), blindAlien.getY(), 100, 100);
        blindAlien.setDirection(blindAlienLabel);
    }

    public boolean getBottleThrown() {
        return bottleThrown;
    }

    public static int[] getCoords(String str){
        if(str.equals("player")){
            return GameControler.getPlayerCoords();
        }
        else if(str.equals("bottle")){
            return bottle.getCoords();
        }
        else{
            return null;
        }
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
                        newImgPlayer = singleImageResize(GameControler.movePlayer("back"));
                        GameControler.applyAlienGoal(blindAlien);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN
                            && oldCoords[1] + playerFront.getHeight() <= background.getHeight() - 170
                                    + playerAbs.getHeight()) {

                        newImgPlayer = singleImageResize(GameControler.movePlayer("front"));
                        GameControler.applyAlienGoal(blindAlien);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT
                            && oldCoords[0] >= (background.getX() + 240) - ((float) 5 / 24) * (oldCoords[1] - 140)) {
                        newImgPlayer = singleImageResize(GameControler.movePlayer("left"));
                        GameControler.applyAlienGoal(blindAlien);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT
                            && oldCoords[0] + playerRight.getWidth() <= (background.getWidth() - 240)
                                    + ((float) 5 / 24) * (oldCoords[1] - 140)) {
                        newImgPlayer = singleImageResize(GameControler.movePlayer("right"));
                        GameControler.applyAlienGoal(blindAlien);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_A && Inventory.contains(bottle)) {
                        bottle.setTrajectory("west");
                        bottleThrown = true;
                        GameControler.usePowerUp(bottle);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_D && Inventory.contains(bottle)) {
                        bottle.setTrajectory("east");
                        bottleThrown = true;

                        GameControler.usePowerUp(bottle);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_W && Inventory.contains(bottle)) {
                        bottle.setTrajectory("north");
                        bottleThrown = true;

                        GameControler.usePowerUp(bottle);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_X && Inventory.contains(bottle)) {
                        bottle.setTrajectory("south");
                        bottleThrown = true;

                        GameControler.usePowerUp(bottle);
                    }

                    pCont.removeAll();
                    addComponentsToContainer();
                    playerAbs.setVisible(true);

                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        System.out.println(GameControler.getGameStatus());
                        GameControler.setGameStatus(GameControler.PAUSED);
                        System.out.println(GameControler.getGameStatus());
                        System.out.println("P Pressed");

                        ScreenCoordinator.pauseGame();
                    }

                }
                if (GameControler.getGameStatus() == GameControler.PAUSED) {
                    System.out.println("Here");
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        System.out.println("Space Pressed");
                        System.out.println(Inventory.isOpen());

                        System.out.println(Inventory.isOpen());
                        GameControler.setGameStatus(GameControler.RUNNING);
                        ScreenCoordinator.pauseGame();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    if (e.getKeyCode() == KeyEvent.VK_I) {
                        System.out.println("I Pressed");
                        if (Inventory.isOpen()) {
                            Inventory.closeFrame();
                        } else {
                            Inventory.setFrame();

                        }
                    }
                }
            }
        });

        bottleLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (GameControler.getGameStatus() == GameControler.RUNNING) {
                        int[] playerCoords = GameControler.getPlayerCoords();
                        int[] bottleCoords = { bottle.getX(), bottle.getY() };
                        System.out.println("0 -> " + playerCoords[0] + " " + bottleCoords[0]);
                        System.out.println("1 -> " + playerCoords[1] + " " + bottleCoords[1]);
                        if (Math.abs(playerCoords[0] - bottleCoords[0]) < 50
                                && Math.abs(playerCoords[1] - bottleCoords[1]) < 50) {
                            System.out.println("Picked bottle up");
                            GameControler.pickObject(bottle);
                            bottleLabel.setVisible(false);
                        }

                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        health.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (GameControler.getGameStatus() == GameControler.RUNNING) {

                        int[] playerCoords = GameControler.getPlayerCoords();
                        int[] healthCoords = { health.getX(), health.getY() };
                        System.out.println("0 -> " + playerCoords[0] + " " + healthCoords[0]);
                        System.out.println("1 -> " + playerCoords[1] + " " + healthCoords[1]);
                        if (Math.abs(playerCoords[0] - healthCoords[0]) < 50
                                && Math.abs(playerCoords[1] - healthCoords[1]) < 50) {
                            System.out.println("Picked power up");
                            GameControler.pickObject(new AddHealthImpl(0, 0));

                            health.setVisible(false);

                        }

                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        chair.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (GameControler.getGameStatus() == GameControler.RUNNING) {
                        int[] playerCoords = GameControler.getPlayerCoords();
                        int[] chairCoords = { chair.getX(), chair.getY() };

                        System.out.println("0 -> " + playerCoords[0] + " " + chairCoords[0]);
                        System.out.println("1 -> " + playerCoords[1] + " " + chairCoords[1]);
                        if (Math.abs(playerCoords[0] - chairCoords[0]) < 50
                                && Math.abs(playerCoords[1] - chairCoords[1]) < 50) {
                            System.out.println("Player is on chair");
                            key.setRevealed(true);
                        }

                    }
                }

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (GameControler.getGameStatus() == GameControler.RUNNING && key.getRevealable()) {
                        int[] playerCoords = GameControler.getPlayerCoords();
                        int[] chairCoords = { chair.getX(), chair.getY() };

                        System.out.println("Key clicked");
                        if (Math.abs(playerCoords[0] - chairCoords[0]) < 50
                                && Math.abs(playerCoords[1] - chairCoords[1]) < 50 && key.getRevealed()) {
                            GameControler.pickObject(key);
                            key.setRevealed(false);
                            key.setRevealable(false);
                            System.out.println("Key collected");
                        }

                    }
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

}
