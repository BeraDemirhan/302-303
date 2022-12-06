package Backend.GameObjects;

import javax.swing.*;

import Backend.GameControler;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chair extends JLabel {
    private int x;
    private int y;
    private Image chairImg = new ImageIcon("EscapeFromKoc/resources/chair.png").getImage();
    private JLabel chairLabel;

    public Chair(int x, int y) {
        spawnChair(x, y);

        addActionEvent();
    }

    public void spawnChair(int x, int y) {
        this.x = x;
        this.y = y;

        chairImg = chairImg.getScaledInstance(96, 54, Image.SCALE_SMOOTH);
        chairLabel = new JLabel(new ImageIcon(chairImg));
        chairLabel.setBounds(x, y, 100, 100);
    }

    public JLabel getChair() {
        return chairLabel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addActionEvent() {
        chairLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Chair clicked");
                if (GameControler.getGameStatus() == GameControler.RUNNING) {
                    int[] playerCoords = GameControler.getPlayerCoords();
                    int[] chairCoords = { x, y };

                    System.out.println("0 -> " + playerCoords[0] + " " + chairCoords[0]);
                    System.out.println("1 -> " + playerCoords[1] + " " + chairCoords[1]);
                    /*
                     * chair.setVisible(false);
                     * pCont.remove(background);
                     * pCont.add(key.reveal());
                     * pCont.add(background);
                     */
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

}
