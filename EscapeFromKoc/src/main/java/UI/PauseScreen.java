package UI;

import javax.swing.*;

import Backend.GameControler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JFrame implements ActionListener {

    Container container = getContentPane();
    private Image backImage = new ImageIcon("EscapeFromKoc/resources/EscapeFomKoc.png").getImage();
    private Image pauseImage = new ImageIcon("EscapeFromKoc/resources/pause.png").getImage();

    private JButton resumeButton = new JButton("RESUME");
    private JButton exitButton = new JButton("EXIT");
    private JButton helpButton = new JButton("HELP");

    private JLabel background;
    private JLabel pauseLabel;
    private JLabel helpText = new JLabel("Press 'P' to pause the game");

    public PauseScreen() {
        resizeImage();
        setLayoutManager();
        loadImages();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void resizeImage() {
        backImage = backImage.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        pauseImage = pauseImage.getScaledInstance(960, 540, Image.SCALE_SMOOTH);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void loadImages() {
        background = new JLabel(new ImageIcon(backImage));
        pauseLabel = new JLabel(new ImageIcon(pauseImage));
    }

    public void setLocationAndSize() {
        background.setBounds(0, 0, 960, 540);
        pauseLabel.setBounds(0, 0, 960, 540);
        resumeButton.setBounds(100, 200, 150, 50);
        helpButton.setBounds(300, 200, 150, 50);
        exitButton.setBounds(500, 200, 150, 50);
        helpText.setBounds(300, 300, 150, 50);
    }

    public void addComponentsToContainer() {
        container.add(resumeButton);
        container.add(exitButton);
        container.add(helpButton);
        container.add(background);
        container.add(pauseLabel);
    }

    public void addActionEvent() {
        resumeButton.addActionListener(this);
        exitButton.addActionListener(this);
        helpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resumeButton) {
            GameControler.setGameStatus(GameControler.RUNNING);
            this.dispose();
        }
        if (e.getSource() == exitButton) {
            GameControler.setGameStatus(GameControler.EXIT);
            GameControler.exit();
            this.dispose();
        }
        if (e.getSource() == helpButton) {
            System.out.println("yes");
            container.remove(background);
            container.add(helpText);
            container.add(background);
        }
    }
}
