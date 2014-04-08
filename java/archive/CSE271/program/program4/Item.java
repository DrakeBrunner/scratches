public class Item {
    // Data
    private String name;
    private int quantity;
    private int unitPrice;  // This is in cents, not dollars

    // Constructor
    public Item(String name, int unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // Methods
    // Increases or decreases the quantity of this item
    public void changeQuantity(int value) {
        quantity += value;
    }

    // Gets the name for this item
    // @Returns The name of this item
    public String getName() {
        return name;
    }

    // Gets the number of units of this item
    // @Returns The number of units of this item
    public int getQuantity() {
        return quantity;
    }

    // Gets the total price of this item, in cents
    // @Returns The total price of this item in cents
    public int getTotalPrice() {
        return unitPrice * quantity;
    }

    // Gets the unit price of this item, in cents
    // @Returns The unit price in cents
    public int getUnitPrice() {
        return unitPrice;
    }

    // @Returns a String representation of this item, including its name and
    // its unit price (in dollars), formatted like this:
    // Bread: 16 ($1.75 per unit)
    public String toString() {
        String ret = "";

        ret = String.format("%s: %d ($%.2f per unit)", name, quantity, (double)(unitPrice) / 100);
        return ret;
    }
}
