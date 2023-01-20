package Backend.Player;

import Backend.GameObjects.Key;
import Backend.GameObjects.PowerUps.PowerUpVest;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import UI.UIUtils;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Inventory {
    protected static ArrayList<Object> items = new ArrayList<Object>();
    private static JFrame inventoryFrame = new JFrame();
    private static boolean InventoryOpen = false;
    protected static Container container = inventoryFrame.getContentPane();
    protected static JLabel empty = new JLabel("Inventory is empty");
    protected static int placer = 0;

    public static void addItem(Object obj) {
        // REQUIRES: object an inventory list
        // Modifies: the inventory size
        // Effects: the inventory size increases
        items.add(obj);
        System.out.println("items size: " + items.size());
        openInventory();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < items.size(); i++) {
            str += items.get(i).toString() + " ";
        }
        return str;
    }


    public static void openInventory() {
        // REQUIRES: container and label
        // Modifies: the label x
        // Effects: the x cord of label increases with placer
        int x = 0;
        int y = 0;
        container.removeAll();
        if (items.size() == 0) {
            empty.setBounds(0, 0, 100, 100);
            container.add(empty);
        } else {
            for (int i = 0; i < items.size(); i++) {
                Object item = items.get(i);
                if (item.getClass().equals(ThrowBottleImpl.class)) {
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/bottle.png", x, y, 96, 54));
                    x += placer;
                    placer = 100;
                }
                if (item.getClass().equals(Key.class)) {
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/RoomObjects/key.png", x, y, 96, 54));
                    x += placer;
                    placer = 100;
                }
                if (item.getClass().equals(PowerUpVest.class)) {
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/vest.png", x, y, 196, 154));
                    x += placer;
                    placer = 100;
                }
            }
        }
    }

    public static void setFrame() {
        inventoryFrame = UIUtils.createFrame("Inventory");
        InventoryOpen = true;
        addActionEvent();
    }

    public static void closeFrame() {
        inventoryFrame.dispose();
        InventoryOpen = false;

    }

    public static boolean isOpen() {
        return InventoryOpen;
    }

    public static boolean contains(Object obj) {
        return items.contains(obj);
    }

    public static void removeItem(Object obj) {
        // REQUIRES: object and inventory list
        // Modifies: inventory list
        // Effects: the size of inventory decreases
        for (int i = 0; i < items.size(); i++) {
            Object item = items.get(i);
            if (item.getClass().equals(obj.getClass())) {
                items.remove(i);
                openInventory();
            }
        }
    }

    public static void addActionEvent() {
        if (InventoryOpen) {
            inventoryFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {

                    super.keyPressed(e);

                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        System.out.println("Inventory closed");
                        closeFrame();
                    }

                }
            });
        }
    }
}
