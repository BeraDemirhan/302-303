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
        JLabel originalChairLabel = chairLabel;
        originalChairLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JLabel newLabel = new JLabel();
                newLabel.setIcon(new ImageIcon(((ImageIcon) originalChairLabel.getIcon()).getImage()));
                System.out.println(originalChairLabel.getX());
                System.out.println(originalChairLabel.getY());
                newLabel.setBounds(originalChairLabel.getX(), originalChairLabel.getY(),192, 108);
                System.out.println(newLabel.getX());
                System.out.println(newLabel.getY());
                newLabel.setAlignmentX(originalChairLabel.getAlignmentX());
                newLabel.setAlignmentY(originalChairLabel.getAlignmentY());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
                    }
                    @Override
                    public void mouseMoved(MouseEvent e) {

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
        JLabel originalSofaLabel = sofaLabel;
        originalSofaLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JLabel newLabel = new JLabel();
                newLabel.setIcon(new ImageIcon(((ImageIcon) originalSofaLabel.getIcon()).getImage()));
                System.out.println(originalSofaLabel.getX());
                System.out.println(originalSofaLabel.getY());
                newLabel.setBounds(originalSofaLabel.getX(), originalSofaLabel.getY(), 192,100);
                System.out.println(newLabel.getX());
                System.out.println(newLabel.getY());
                newLabel.setAlignmentX(originalSofaLabel.getAlignmentX());
                newLabel.setAlignmentY(originalSofaLabel.getAlignmentY());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);                    }
                    @Override
                    public void mouseMoved(MouseEvent e) {

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
        JLabel originalPianoLabel = pianoLabel;
        originalPianoLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JLabel newLabel = new JLabel();
                newLabel.setIcon(new ImageIcon(((ImageIcon) originalPianoLabel.getIcon()).getImage()));
                System.out.println(originalPianoLabel.getX());
                System.out.println(originalPianoLabel.getY());
                newLabel.setBounds(originalPianoLabel.getX(),originalPianoLabel.getY(),192,108);
                System.out.println(newLabel.getX());
                System.out.println(newLabel.getY());
                newLabel.setAlignmentX(originalPianoLabel.getAlignmentX());
                newLabel.setAlignmentY(originalPianoLabel.getAlignmentY());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
                    }
                    @Override
                    public void mouseMoved(MouseEvent e) {

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
        JLabel originalTableLabel = tableLabel;
        originalTableLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                JLabel newLabel = new JLabel();
                newLabel.setIcon(new ImageIcon(((ImageIcon) originalTableLabel.getIcon()).getImage()));
                System.out.println(originalTableLabel.getX());
                System.out.println(originalTableLabel.getY());
                newLabel.setBounds(originalTableLabel.getX(),originalTableLabel.getY(),192,108);
                System.out.println(newLabel.getX());
                System.out.println(newLabel.getY());
                newLabel.setAlignmentX(originalTableLabel.getAlignmentX());
                newLabel.setAlignmentY(originalTableLabel.getAlignmentY());
                newLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                container.add(newLabel);
                container.setComponentZOrder(newLabel, 0);
                container.validate();
                container.repaint();
                newLabel.addMouseMotionListener(new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        newLabel.setLocation(getMousePosition().x - 96, getMousePosition().y - 54);
                    }
                    @Override
                    public void mouseMoved(MouseEvent e) {

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
