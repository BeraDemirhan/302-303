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

    private Container container = getContentPane();

    public BuildMode() {
        setLayoutManager();
        imageResize();
        loadImages();
        setSizeandLocation();
        alignLabels();
        addActionEvent();
        addComponentsToContainer();

    }

    private void imageResize() {
        chair = chair.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        sofa = sofa.getScaledInstance(96 * 3, 54 * 3, Image.SCALE_SMOOTH);
        piano = piano.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        table = table.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
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
    private JLabel newLabel;

    private void addActionEvent() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                    ScreenCoordinator.startGame();
                }
            }
        });

        chairLabel.addMouseListener(new MouseListener() {

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
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
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
