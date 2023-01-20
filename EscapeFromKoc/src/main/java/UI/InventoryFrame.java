package UI;

import javax.swing.*;

import Backend.GameObjects.Key;
import Backend.GameObjects.PowerUps.*;

import java.awt.*;
import java.util.ArrayList;

public class InventoryFrame extends JFrame{
    private static int placer = 100;
    private Container container = getContentPane();
    private static JLabel empty = new JLabel("Inventory is empty");
    private ArrayList<Object> items = new ArrayList<Object>();

    public void openInventory() {
        // REQUIRES: container and label
        // Modifies: the label x
        // Effects: the x cord of label increases with placer
        int x = 0;
        int y = 0;
        container.removeAll();
        System.out.println("Inventory open function");
        
        if (items.size() == 0) {
            empty.setBounds(0, 0, 100, 100);
            container.add(empty);
        } else {
            for (int i = 0; i < items.size(); i++) {
                Object item = items.get(i);
                System.out.println("item class" + item.getClass().toString());
                if (item.getClass().equals(ThrowBottleImpl.class)) {
                    System.out.println("bottle added to inventory");
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/bottle.png", x, y, 96, 54));
                    System.out.println(container.getComponentCount());
                    x += placer;
                    placer = 100;
                }
                if (item.getClass().equals(Key.class)) {
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/key.png", x, y, 96, 54));
                    x += placer;
                    placer = 100;
                }
            }
        }
    }

    public void setItems(ArrayList<Object> items) {
        this.items = items;
    }

    public void closeFrame() {
        this.setVisible(false);
    }

    public  boolean isOpen() {
        return this.isVisible();
    }
}
