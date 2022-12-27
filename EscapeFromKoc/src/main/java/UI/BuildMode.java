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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        loadImages();
        setSize();
        setInitLocation();
        addComponentsToContainer();
        addActionEvent();
        imageResize();
    }

    private void imageResize() {
        chair = chair.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        sofa = sofa.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
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

    private void setSize() {
        chairLabel.setSize(100, 100);
        sofaLabel.setSize(100, 100);
        pianoLabel.setSize(100, 100);
        tableLabel.setSize(100, 100);
        background.setSize(960, 540);
    }

    private void addComponentsToContainer() {
        container.add(chairLabel);
        container.add(sofaLabel);
        container.add(pianoLabel);
        container.add(tableLabel);
        container.add(background);
    }

    private void setInitLocation() {
        chairLabel.setLocation(0, 0);
        sofaLabel.setLocation(150, 0);
        pianoLabel.setLocation(300, 0);
        tableLabel.setLocation(450, 0);
    }

    private void addActionEvent() {
        chairLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                chairLabel.setLocation(getMousePosition().x, getMousePosition().y);
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
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                sofaLabel.setLocation(getMousePosition().x, getMousePosition().y);
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
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pianoLabel.setLocation(getMousePosition().x, getMousePosition().y);
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
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                tableLabel.setLocation(getMousePosition().x, getMousePosition().y);
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
