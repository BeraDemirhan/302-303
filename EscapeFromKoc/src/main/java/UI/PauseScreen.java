package UI;

import javax.swing.*;

import Backend.GameControler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PauseScreen extends JFrame implements ActionListener {

    Container container = getContentPane();
    private Image backImage = new ImageIcon("EscapeFromKoc/resources/EscapeFomKoc.png").getImage();
    private Image pauseImage = new ImageIcon("EscapeFromKoc/resources/pause.png").getImage();

    private JButton resumeButton = new JButton("RESUME");
    private JButton exitButton = new JButton("EXIT");
    private JButton helpButton = new JButton("HELP");
    private JButton saveButton = new JButton("SAVE");
    private JButton loadButton = new JButton("LOAD");

    private JLabel background;
    private JLabel pauseLabel;

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
        resumeButton.setBounds(400, 100, 150, 50);
        exitButton.setBounds(400, 180, 150, 50);
        helpButton.setBounds(400, 260, 150, 50);
        saveButton.setBounds(400, 340, 150, 50);
        loadButton.setBounds(400, 420, 150, 50);
    }

    public void addComponentsToContainer() {
        container.add(resumeButton);
        container.add(exitButton);
        container.add(saveButton);
        container.add(helpButton);
        container.add(loadButton);
        container.add(background);
        container.add(pauseLabel);
        
        
    }

    public void addActionEvent() {
        resumeButton.addActionListener(this);
        exitButton.addActionListener(this);
        helpButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
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
            JFrame helpFrame = new Hint();
            helpFrame.setTitle("Help");
            helpFrame.setSize(1440, 810);
            helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            helpFrame.setResizable(false);
            helpFrame.setVisible(true);
        }
        if (e.getSource() == saveButton) {
            GameControler.saveGame();
        }
        if (e.getSource() == loadButton) {
            try {
                GameControler.loadPrevGame();
            } catch (NumberFormatException | IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
