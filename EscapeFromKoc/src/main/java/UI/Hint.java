package UI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import javax.swing.*;

public class Hint extends JFrame {

    Container container = getContentPane();

    private Image hintImage = new ImageIcon("EscapeFromKoc/resources/HelpScreen.png").getImage();
    private JLabel hintLabel;

    public Hint() {
        // setBounds(0, 0, 1920, 1080);
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        setAlwaysOnTop(true);
        imageResize();
        addActionEvent();
        setVisible(true);
    }

    public void imageResize() {
        hintImage = hintImage.getScaledInstance(1440, 810, Image.SCALE_SMOOTH);
        hintLabel = new JLabel(new ImageIcon(hintImage));
        hintLabel.setBounds(0, 0, 1440, 810);
        container.add(hintLabel);
    }

    public void addActionEvent() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("Escape");
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

}
