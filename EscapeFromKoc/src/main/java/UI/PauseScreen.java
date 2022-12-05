package UI;

import javax.swing.*;

import Backend.BackendManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PauseScreen extends JFrame implements ActionListener {

    Container container = getContentPane();
    private JLabel background = new JLabel(new ImageIcon("EscapeFromKoc/resources/EscapeFomKoc.png"));
    private JLabel pauseLabel = new JLabel(new ImageIcon("EscapeFromKoc/resources/PAUSED.png"));
    private JButton resumeButton = new JButton("RESUME");

    public PauseScreen() {
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLocationAndSize() {
        pauseLabel.setBounds(100, 100, 300, 100);
        resumeButton.setBounds(150, 300, 100, 30);
        background.setBounds(0, 0, 900, 950);
    }

    public void addComponentsToContainer() {
        container.add(pauseLabel);
        container.add(resumeButton);
        container.add(background);
    }

    public void addActionEvent() {
        resumeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resumeButton) {
            BackendManager.setGameStatus(BackendManager.RUNNING);
            this.dispose();
        }
    }
}
