package Backend.Player;

import Backend.GameObjects.Key;

import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import UI.UIUtils;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Inventory {
    private static ArrayList<Object> items = new ArrayList<Object>();
    private static JFrame inventoryFrame = new JFrame();
    private static boolean InventoryOpen = false;
    private static Container container = inventoryFrame.getContentPane();
    private static JLabel empty = new JLabel("Inventory is empty");
    private static int placer = 0;

    public static void addItem(Object obj) {
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

    public void removeItem(Object obj) {
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
