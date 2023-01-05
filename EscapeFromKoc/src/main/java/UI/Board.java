package UI;

import javax.swing.*;

import Backend.GameControler;
import Backend.GameObjects.Key;
import Backend.GameObjects.PowerUps.AddHealthImpl;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import Backend.Player.Inventory;
import Backend.GameObjects.Aliens.BlindAlienImpl;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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


    private BlindAlienImpl blindAlien;
    private JLabel blindAlienLabel;

    private ThrowBottleImpl bottle = new ThrowBottleImpl(400, 200);
    private boolean bottleThrown = false;

    public boolean getBottleThrown(){
        return bottleThrown;
    }
    private static final int TIMER_DELAY = 35;

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
    
        updateFrame();
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
        blindAlienLabel.setBounds(blindAlien.getX(),blindAlien.getY(),100,100);
        bottleLabel.setBounds(bottle.getX(), bottle.getY(), 100, 100);

    }
    public int[] getBottleLabelCoords(){
        int[] coords = new int[2];
        coords[0] = bottleLabel.getX();
        coords[1] = bottleLabel.getY();

        return  coords;
    }
    public void setBottleLabelCoords(int x, int y){
        bottleLabel.setBounds(x,y,100,100);
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

    public ArrayList<String> getObjects(){
        ArrayList<String> objects = new ArrayList<String>();
        for(int i = 0; i < pCont.getComponentCount(); i++){
            if (pCont.getComponent(i).toString().contains("JLabel")) {
                objects.add(pCont.getComponent(i).toString());
            }
        }
        return objects;
    }

    public int[] getObjectCoords(String object){
        int[] coords = new int[2];
        for(int i = 0; i < pCont.getComponentCount(); i++){
            if (pCont.getComponent(i).toString().equals(object)) {
                coords[0] = pCont.getComponent(i).getX();
                coords[1] = pCont.getComponent(i).getY();
                return coords;
            }
        }
        return coords;
    }


    public void createFurniture() {
        chair = GameControler.createFurniture().getObjectLabel();
    }

    // Overview: this function creates health which is a power up
    public void createHealth() {

     /*
             AF = new AddHealthImpl(coordinates).get()

             rep invariant
                x != null && y != null

             @requires get Health function from add health implementation class
             @modifies health existence
             @effects health object is created

       */
        health = new AddHealthImpl(100, 100).getHealth();
    }

    public boolean getHealth(){
        if( !(health == null)){
            return true;
        }
        return false;
    }

    // Overview: this function creates Alien according to given type

    public void createAlien(){
        /*

                   AF = createAlien(type, coordinates)

                   rep invariant
                    type != null && x != null && y != null

                   @requires create alien function from game controller
                   @modifies alien
                   @effects alien object is created

       */
        blindAlien = (BlindAlienImpl) GameControler.createAlien("blind",200, 200);
        blindAlienLabel = blindAlien.getObjectLabel();
    }

    /*
        Overview= With this function the bottle is thrown by changing the coordinates of the bottle label
     */
    public void bottleThrowAnimation(int[] playerCoords, int[] newCoords) {
        /*
            AF(playerCoords, bottleCoords) =
                for (
                    p.x += (b.x - p.x)/7
                    p.y += (b.y - p.y)/7
                    bottleLabel.setBounds(p.x, p.y, w, h)
                    applyAlienGoal()
                )
                bottleThrown = false

            rep invariant
                player != null && bottle != null


            @playerCoords *Coordinator of the player (x,y)
            @newCoords *Coordinator of bottle (x,y)*

            @modifies bottle bounds and state of the bottleThrown
            @effects bottleThrown becomes false, x and y coordinates of bottle changes
        */
        System.out.println("animating bottle throw");


        int x = playerCoords[0];
        int y = playerCoords[1];
        int x2 = newCoords[0];
        int y2 = newCoords[1];

        int dx = x2 - x;
        int dy = y2 - y;
        int steps = 7;
        double xIncr = (double) dx / (double) steps;
        double yIncr = (double) dy / (double) steps;
        for (int i = 0; i < steps; i++) {
            x += xIncr;
            y += yIncr;
            try {

                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Bottle Label coords: " + bottleLabel.getX() + " " + bottleLabel.getY());
            bottleLabel.setBounds((int) x, (int) y, 100, 100);
            GameControler.applyAlienGoal(blindAlien, bottle);
            bottleLabel.setVisible(true);
            pCont.repaint();
        }
        bottleThrown = false;

    }

    public void updateFrame() {

        new javax.swing.Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    playerAbs.setIcon(new ImageIcon(newImgPlayer));
                    playerAbs.setBounds(GameControler.getPlayerCoords()[0], GameControler.getPlayerCoords()[1], 100,
                            100);
                    playerAbs.setVisible(true);
                    blindAlienLabel.setBounds(blindAlien.getX(), blindAlien.getY(), 100, 100); 
                    blindAlien.setDirection(blindAlienLabel);

                    if (bottleThrown) {
                        new Thread() {
                            public void run() {
                                bottleThrowAnimation(GameControler.getPlayerCoords(), bottle.getCoords());
                            }
                        }.start();;
                    }
                }
            }
        }).start();
    }
    /*Overview: all key operations for game is implemented with addActionEvent

     */
    public void addActionEvent() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                playerAbs.setVisible(false);
                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    int[] oldCoords = GameControler.getPlayerCoords();
                    if (e.getKeyCode() == KeyEvent.VK_UP && oldCoords[1] >= background.getY() + 140) {
                        /*

                            @requires arrow key and status should be running mode
                            @modifies player direction and movement also alien movement
                            @effects player direction become beck
                                     player moves
                                     blind aliens follows the player

                         */
                        newImgPlayer = singleImageResize(GameControler.movePlayer("back"));
                        GameControler.applyAlienGoal(blindAlien);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN
                            && oldCoords[1] + playerFront.getHeight() <= background.getHeight() - 170
                                    + playerAbs.getHeight()) {
                        /*


                            @requires arrow key and status should be running mode
                            @modifies player direction and movement also alien movement
                            @effects player direction become front
                                     player moves
                                     blind aliens follows the player

                         */

                        newImgPlayer = singleImageResize(GameControler.movePlayer("front"));
                        GameControler.applyAlienGoal(blindAlien);


                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT
                            && oldCoords[0] >= (background.getX() + 240) - ((float) 5 / 24) * (oldCoords[1] - 140)) {
                        /*


                            @requires arrow key and status should be running mode
                            @modifies player direction and movement also alien movement
                            @effects player direction become left
                                     player moves
                                     blind aliens follows the player

                         */

                        newImgPlayer = singleImageResize(GameControler.movePlayer("left"));
                        GameControler.applyAlienGoal(blindAlien);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT
                            && oldCoords[0] + playerRight.getWidth() <= (background.getWidth() - 240)
                                    + ((float) 5 / 24) * (oldCoords[1] - 140)) {
                       /*

                            @requires arrow key and status should be running mode
                            @modifies player direction and movement also alien movement
                            @effects player direction become beck
                                     player moves
                                     blind aliens follows the player

                         */
                        newImgPlayer = singleImageResize(GameControler.movePlayer("right"));
                        GameControler.applyAlienGoal(blindAlien);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_A && Inventory.contains(bottle)) {
                        /*

                            @requires A key and bottle
                            @modifies bottle trajectory, status of bottleThrown
                            @effects bottle moves to west, bottleThrown = true

                         */
                        bottle.setTrajectory("west");
                        bottleThrown = true;
                        GameControler.usePowerUp(bottle);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_D && Inventory.contains(bottle)) {
                        /*


                            @requires D key and bottle
                            @modifies bottle trajectory, status of bottleThrown
                            @effects bottle moves to east, bottleThrown = true

                         */
                        bottle.setTrajectory("east");
                        bottleThrown = true;

                        GameControler.usePowerUp(bottle);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_W && Inventory.contains(bottle)) {
                        /*

                            @requires W key and bottle
                            @modifies bottle trajectory, status of bottleThrown
                            @effects bottle moves to north, bottleThrown = true

                         */
                        bottle.setTrajectory("north");
                        bottleThrown = true;

                        GameControler.usePowerUp(bottle);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_X && Inventory.contains(bottle)) {
                        /*


                            @requires X key and bottle
                            @modifies bottle trajectory, status of bottleThrown
                            @effects bottle moves to south, bottleThrown = true

                         */
                        bottle.setTrajectory("south");
                        bottleThrown = true;

                        GameControler.usePowerUp(bottle);
                    }

                    pCont.removeAll();
                    addComponentsToContainer();
                    playerAbs.setVisible(true);

                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        /*

                            @requires Esc key and game status should be running
                            @modifies game status
                            @effects game status become 0

                         */
                        System.exit(0);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        /*


                            @modifies: game status
                            @effects game status becomes pause
                         */

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
                        /*


                            @requires Space key
                            @modifies game status
                            @effects game status changes paused to running mode
                         */
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
                         /*


                            @requires Space key
                            @modifies game status
                            @effects inventory is opened
                         */
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
