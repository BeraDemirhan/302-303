package UI.HintPowerUpUI;

import javax.swing.*;
import java.awt.*;

public class HintPowerUpHighlight extends JPanel {
    public void paintComponent(Graphics g, int x, int y) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(new Color(0x00FF42));
        g2d.fillRect(x, y, 40, 40);

    }

}
