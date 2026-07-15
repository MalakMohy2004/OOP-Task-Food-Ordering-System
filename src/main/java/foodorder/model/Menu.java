package foodorder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void printMenu() {
        System.out.println("Menu:");
        for (MenuItem item : items) {
            System.out.printf("- %s: %.2f%n", item.getName(), item.getPrice());
        }
    }
}
