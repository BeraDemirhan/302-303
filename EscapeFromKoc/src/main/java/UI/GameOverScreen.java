package UI;

import javax.swing.*;

import Backend.GameControler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class GameOverScreen extends JFrame{
    Container container = getContentPane();
    private Image backImage = new ImageIcon("EscapeFromKoc/resources/EscapeFomKoc.png").getImage();

    private JButton exitButton = new JButton("EXIT");
    
    private JLabel background;
    private JLabel gameOverLabel;

    public GameOverScreen() {
        resizeImage();
        setLayoutManager();
        loadImages();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void resizeImage() {
        backImage = backImage.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void loadImages() {
        background = new JLabel(new ImageIcon(backImage));
        gameOverLabel = new JLabel("GAME OVER");
    }

    public void setLocationAndSize() {
        background.setBounds(0, 0, 960, 540);
        gameOverLabel.setBounds(400, 100, 150, 50);
        exitButton.setBounds(400, 180, 150, 50);
    }

    public void addComponentsToContainer() {
        container.add(background);
        background.add(gameOverLabel);
        background.add(exitButton);
    }

    public void addActionEvent() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
