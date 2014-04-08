import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    // Data
    private ArrayList<Item> inventory;

    // Constructors
    public Store() {
        inventory = new ArrayList<Item>();
    }

    // Input format should be
    // itemName unitPrice quantity
    // each separated by space
    public Store(Scanner keyboard) {
        this();

        String item;
        while (!(item = keyboard.nextLine()).equals("*")) {
            String[] line = item.split(" ");    // Split to tokens
            String name = line[0];
            double unitPrice = Double.parseDouble(line[1]);
            int quantity = Integer.parseInt(line[2]);

            Item tmp = new Item(name, (int)(unitPrice * 100), quantity);
            add(tmp);
        }
    }

    // Methods
    // Updates existing item or adds a new item to the inventory
    public void add(Item item) {
        Item tmp;
        if ((tmp = findItem(item.getName())) != null) {
            inventory.get(inventory.indexOf(tmp)).changeQuantity(item.getQuantity());
        }
        else
            inventory.add(item);
    }

    // Finds an item by its name if it's part of the store's inventory
    // @Returns Item
    public Item findItem(String name) {
        for (int i = 0; i < inventory.size(); i++)
            if (name.equals(inventory.get(i).getName()))
                return inventory.get(i);
        return null;
    }

    // Performs operations reflecting selling an item back to the store's inventory
    // @Returns true if the given item was found in the store's inventory and
    // the return was accepted. Otherwise, prints a suitable message and
    // returns false
    public boolean returnItemToInventory(String name, int quantity) {
        Item tmp = findItem(name);
        if (tmp == null) {
            System.out.println("Sorry invalid return as store does not have the item");
            return false;
        }
        tmp.changeQuantity(quantity);
        return true;
    }

    // Performs operations reflecting sellin an item from the store's inventory
    // @Returns Item that was sold
    public Item sellItem(String name, int quantity) {
        Item tmp = findItem(name);
        if (tmp == null) {
            System.out.println("The requested item " + name + " is not in the inventory.");
            return null;
        }
        if (tmp.getQuantity() - quantity <= 0) {
            System.out.println("Sorry the store does not have " + quantity + " quantities of " + name);
            return null;
        }

        tmp.changeQuantity(-quantity);
        Item ret = new Item(name, tmp.getUnitPrice(), quantity);
        return ret;
    }

    // @Returns a String representation of this store, consisting of all items
    // and the net value (in dollars) of the store's inventory
    public String toString() {
        String ret = "";
        double netValue = 0;

        for (int i = 0; i < inventory.size(); i++) {
            ret += inventory.get(i).toString() + "\n";
            netValue += (double)(inventory.get(i).getTotalPrice()) / 100;
        }

        ret += String.format("Net inventory price: $%.2f\n", netValue);

        return ret;
    }
}
