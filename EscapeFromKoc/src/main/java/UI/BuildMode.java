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
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildMode extends JFrame implements ActionListener {

    private Image chair = new ImageIcon("EscapeFromKoc/resources/RoomObjects/chair.png").getImage();
    private Image sofa = new ImageIcon("EscapeFromKoc/resources/RoomObjects/sofaCorner.png").getImage();
    private Image piano = new ImageIcon("EscapeFromKoc/resources/RoomObjects/piano.png").getImage();
    private Image table = new ImageIcon("EscapeFromKoc/resources/RoomObjects/table.png").getImage();
    private Image room = new ImageIcon("EscapeFromKoc/resources/RoomObjects/emptyRoom.png").getImage();

    private JLabel chairLabel;
    private JLabel sofaLabel;
    private JLabel pianoLabel;
    private JLabel tableLabel;
    private JLabel background;
    private static ArrayList<String> objects = new ArrayList<String>();
    private static ArrayList<Pair> objectsCoords = new ArrayList<Pair>();    

    Pair coords = new Pair(0,0);


    private Container container = getContentPane();
    
    public class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
           this.x = x;
           this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }


    public BuildMode(int atLeast) {
        setLayoutManager();
        imageResize();
        loadImages();
        setSizeandLocation();
        alignLabels();
        addActionEvent(atLeast);
        addComponentsToContainer();
    }
    private void imageResize() {
        chair = chair.getScaledInstance(96/2, 54/2, Image.SCALE_SMOOTH);
        sofa = sofa.getScaledInstance(96 * 3/2, 54 * 3/2, Image.SCALE_SMOOTH);
        piano = piano.getScaledInstance(96/2, 54/2, Image.SCALE_SMOOTH);
        table = table.getScaledInstance(96/2, 54/2, Image.SCALE_SMOOTH);
        room = room.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    private void loadImages() {
        background = new JLabel(new ImageIcon(room));
        chairLabel = new JLabel(new ImageIcon(chair));
        sofaLabel = new JLabel(new ImageIcon(sofa));
        pianoLabel = new JLabel(new ImageIcon(piano));
        tableLabel = new JLabel(new ImageIcon(table));
    }

    private void setSizeandLocation() {
        chairLabel.setBounds(0, 0, 192, 108);
        sofaLabel.setBounds(200, 0, 192, 108);
        pianoLabel.setBounds(400, 0, 192, 108);
        tableLabel.setBounds(600, 0, 192, 108);
        background.setBounds(0, 0, 960, 540);
    }

    private void addComponentsToContainer() {
        container.add(chairLabel);
        container.add(sofaLabel);
        container.add(pianoLabel);
        container.add(tableLabel);
        container.add(background);
    }

    private void alignLabels() {
        chairLabel.setAlignmentX(LEFT_ALIGNMENT);
        chairLabel.setAlignmentY(TOP_ALIGNMENT);
        sofaLabel.setAlignmentX(CENTER_ALIGNMENT);
        sofaLabel.setAlignmentY(CENTER_ALIGNMENT);
        pianoLabel.setAlignmentX(CENTER_ALIGNMENT);
        pianoLabel.setAlignmentY(CENTER_ALIGNMENT);
        tableLabel.setAlignmentX(CENTER_ALIGNMENT);
        tableLabel.setAlignmentY(CENTER_ALIGNMENT);
    }
    public ArrayList<String> getObjects(){
        for(int i = 0; i < container.getComponentCount(); i++){
            if (container.getComponent(i).toString().contains("JLabel")) {
                objects.add(container.getComponent(i).toString());
            }
        }
        return objects;

    }
    public int[] getObjectCoords(String object){
        int[] coords = new int[2];
        for(int i = 0; i < objectsCoords.size(); i++){
            if (objects.get(i).toString().equals(object)) {
                coords[0] = objectsCoords.get(i).getX();
                coords[1] = objectsCoords.get(i).getY();
                return coords;
            }
        }
        return coords;
    }

    public ArrayList<String> getBuiltObjects(){
        return objects;
    }

    public int[] getBuiltObjectCoords(String object){
        //System.out.println("object: " + object );
        int[] coords = new int[2];
        for(int i = 0; i < objectsCoords.size(); i++){
            if (objects.get(i).toString().equals(object)) {
                //System.out.println("Yes");
                coords[0] = objectsCoords.get(i).getX();
                coords[1] = objectsCoords.get(i).getY();
                return coords;
            }
        }
        return coords;
    }

    int totalComponents = container.getComponents().length;

    private void addActionEvent(int atLeast) {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (totalComponents - 5 < atLeast) {
                        JOptionPane.showMessageDialog(container, "You should add at least "+ atLeast + " objects");
                    }
                    else {
                        try {
                            GameControler.saveBuildMode();
                        } catch (NumberFormatException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        dispose();
                        GameControler.setCurrentLevelTime();
                        GameControler.setStartTime(System.nanoTime());
                        GameControler.startGame();
                    }
                }
            }
        });

        chairLabel.addMouseListener(new MouseListener() {
            private JLabel newLabel;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                newLabel = new JLabel(new ImageIcon(((ImageIcon) chairLabel.getIcon()).getImage()));
                newLabel.setBounds(chairLabel.getBounds());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
                
        }


            @Override
            public void mouseReleased(MouseEvent e) {
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
                        totalComponents = container.getComponents().length;

                    }
                });

                newLabel.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        coords.setX(getMousePosition().x - 24);
                        coords.setY(getMousePosition().y - 13);
                        GameControler.addObject("chair", coords.getX(), coords.getY());
                        objects.add("chair");
                        objectsCoords.add(coords);
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }
                });

                
           }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });



        sofaLabel.addMouseListener(new MouseListener() {
            private JLabel newLabel;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                newLabel = new JLabel(new ImageIcon(((ImageIcon) sofaLabel.getIcon()).getImage()));
                newLabel.setBounds(sofaLabel.getBounds());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96*2/3, getMousePosition().y - 54*2/3);
                        totalComponents = container.getComponents().length;
                        
                        
                    }
                });
                newLabel.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        coords.setX(getMousePosition().x - 24);
                        coords.setY(getMousePosition().y - 13);
                        GameControler.addObject("sofa", coords.getX(), coords.getY());
                        objects.add("sofa");
                        objectsCoords.add(coords);
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });




        pianoLabel.addMouseListener(new MouseListener() {
            private JLabel newLabel;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                newLabel = new JLabel(new ImageIcon(((ImageIcon) pianoLabel.getIcon()).getImage()));
                newLabel.setBounds(pianoLabel.getBounds());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
                        totalComponents = container.getComponents().length;
                        
                    }
                
                });

                newLabel.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        coords.setX(getMousePosition().x - 24);
                        coords.setY(getMousePosition().y - 13);
                        GameControler.addObject("piano", coords.getX(), coords.getY());
                        objects.add("piano");
                        objectsCoords.add(coords);
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        tableLabel.addMouseListener(new MouseListener() {
            private JLabel newLabel;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                newLabel = new JLabel(new ImageIcon(((ImageIcon) tableLabel.getIcon()).getImage()));
                newLabel.setBounds(tableLabel.getBounds());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
                

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
                        totalComponents = container.getComponents().length;
                        
                    }
                });

                newLabel.addMouseListener(new MouseListener(){
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        coords.setX(getMousePosition().x - 24);
                        coords.setY(getMousePosition().y - 13);
                        GameControler.addObject("table", coords.getX(), coords.getY());
                        objects.add("table");
                        objectsCoords.add(coords);
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        
                    }
                });
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

}
