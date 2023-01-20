package Backend.Player;

import Backend.GameObjects.Key;
import Backend.GameObjects.PowerUps.PowerUpVest;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import UI.InventoryFrame;
import UI.UIUtils;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Inventory {
    protected static ArrayList<Object> items = new ArrayList<Object>();
    private static boolean InventoryOpen = false;
    protected static int placer = 0;
    private static InventoryFrame inventoryFrame = new InventoryFrame();

    public static void addItem(Object obj) {
        // REQUIRES: object an inventory list
        // Modifies: the inventory size
        // Effects: the inventory size increases
        items.add(obj);
        System.out.println("items size: " + items.size());
        inventoryFrame.setItems(items);
        inventoryFrame.openInventory();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < items.size(); i++) {
            str += items.get(i).toString() + " ";
        }
        return str;
    }



    public static void setFrame() {
        inventoryFrame.openInventory();
        UIUtils.createFrame(inventoryFrame);
        InventoryOpen = true;
        addActionEvent();
    }

    public static void closeFrame() {
        inventoryFrame.setVisible(false);
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
                inventoryFrame.setItems(items);
                inventoryFrame.openInventory();
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
