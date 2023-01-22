package UI;


import javax.swing.*;

import Backend.GameControler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class LoadOptions extends JFrame implements ActionListener{
    private JButton loadButton = new JButton("Load Previous Game");
    private JButton newGameButton = new JButton("New Game");

    public LoadOptions() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        Container container = getContentPane();
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        loadButton.setBounds(400, 100, 150, 50);
        newGameButton.setBounds(400, 180, 150, 50);
    }

    public void addComponentsToContainer() {
        Container container = getContentPane();
        container.add(loadButton);
        container.add(newGameButton);
    }

    public void addActionEvent() {
        loadButton.addActionListener(this);
        newGameButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            try {
                GameControler.loadGame();
                
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (e.getSource() == newGameButton) {
            GameControler.newGame();
        }
    }


}
