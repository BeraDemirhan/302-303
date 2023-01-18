package UI.HintPowerUpUI;

import javax.swing.*;
import java.util.TimerTask;

public class HintPowerUpThread {
    HintPowerUpHighlight a = new HintPowerUpHighlight();

    JFrame frame = new JFrame("Rectangles");
    TimerTask newtask = new TimerTask() {
        @Override
        public void run() {
            frame.setVisible(false);

        }
    };
    public void doHintPowerUpTask(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(a);
        frame.setSize(360, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public TimerTask getNewtask() {
        return newtask;
    }
}
