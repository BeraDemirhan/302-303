package UI;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UIUtils {
    public static JLabel createLabel(String path, int x, int y, int width, int height) {
        Image img = new ImageIcon(path).getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(x, y, width, height);
        return label;
    }

    public static JLabel createLabel(String path, int x, int y) {
        JLabel label = new JLabel(new ImageIcon(path));
        label.setBounds(x, y, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        return label;
    }

    public static JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setLayout(null);
        frame.setBounds(0, 380, 960, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setFocusable(true);
        return frame;
    }

}
