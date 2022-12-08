package Backend.Player;

import Backend.GameObjects.PowerUps.AddHealthImpl;
import Backend.GameObjects.PowerUps.PowerUp;

import java.util.ArrayList;

public class Inventory {
    private static ArrayList<Object> items;

    public Inventory() {
        items = new ArrayList<Object>();
    }

    public static void addItem(Object obj) {
        items.add(obj);
    }

    public static boolean contains(Object obj){
        return items.contains(obj);
    }

    public void removeItem(Object obj) {
        for (int i = 0; i < items.size(); i++) {
            Object item = items.get(i);
            if(item.getClass().equals(obj.getClass())){
                items.remove(i);
            }
        }

    }

}
