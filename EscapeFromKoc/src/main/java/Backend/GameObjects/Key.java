package Backend.GameObjects;

import javax.swing.*;
import java.awt.*;

public class Key extends JLabel {
    private int x;
    private int y;
    private boolean revealed = false;
    private Image keyImg = new ImageIcon("EscapeFromKoc/resources/key.png").getImage();

    public boolean getRevealed() {
        return revealed;
    }

    public JLabel reveal() {
        revealed = true;
        keyImg = keyImg.getScaledInstance(48, 27, Image.SCALE_SMOOTH);
        JLabel keyFrame = new JLabel(new ImageIcon(keyImg));
        keyFrame.setBounds(x, y, 100, 100);
        return keyFrame;
    }

    public void spawnKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

}
