package Backend.Player;

import java.util.ArrayList;

public class Inventory {
    private static ArrayList<Object> items = null;

    public Inventory() {
        items = new ArrayList<Object>();
    }

    public static void addItem(Object obj) {
        items.add(obj);
    }

    public void removeItem(Object obj) {
        items.remove(obj);
    }

}
