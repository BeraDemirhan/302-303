/* 
package Backend.Player;

import Backend.GameObjects.Key;
import Backend.GameObjects.PowerUps.ThrowBottleImpl;
import UI.UIUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static Backend.Player.Inventory.items;
import static Backend.Player.Inventory.container;
import static Backend.Player.Inventory.empty;
import static Backend.Player.Inventory.placer;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {


    @Test
    void addItem() {
        Object obj = new Object();
        int prev = items.size();
        Inventory.addItem(obj);
        assertTrue(items.size() > prev);
    }

    @Test
    void openInventoryIf(){
        Inventory.openInventory();
        if(Inventory.items.size()==0) {
            assertEquals(0, empty.getX());
            assertEquals(0, empty.getY());
            assertEquals(100, empty.getWidth());
            assertEquals(100, empty.getHeight());
            container.add(empty);
        }
        else {
            openInventoryElse();
        }
    }
    @Test
    void openInventoryElse() {

        if (Inventory.items.size() != 0) {
            int x = 0;
            int y = 0;
            for (int i = 0; i < items.size(); i++) {
                Object item = items.get(i);
                if (item.getClass().equals(ThrowBottleImpl.class)) {
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/bottle.png", x, y, 96, 54));
                    assertEquals(x + placer, x);
                    placer=100;

                }
                if (item.getClass().equals(Key.class)) {
                    container.add(UIUtils.createLabel("EscapeFromKoc/resources/bottle.png", x, y, 96, 54));
                    assertEquals(x + placer, x);
                    placer=100;
                }
            }
        }
        else {
            openInventoryIf();
        }
    }

    @Test
    void removeItem() {
        int prev = items.size();
        Object obj = new Object();
        Inventory.removeItem(obj);
        assertTrue(items.size() < prev);
    }
}*/