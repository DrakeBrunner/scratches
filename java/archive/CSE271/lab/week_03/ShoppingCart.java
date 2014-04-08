import java.util.ArrayList;

public class ShoppingCart {
	// Data
	private String owner;
	private ArrayList<String> items;
	
	// Constructors
	public ShoppingCart(String owner) {
		this.owner = owner;
		this.items = new ArrayList<String>();
	}
	
	public ShoppingCart(String owner, String[] items) {
		this.owner = owner;
		this.items = new ArrayList<String>();
		for (int i = 0; i < items.length; i++)
			this.items.add(items[i]);
	}
	
	public ShoppingCart() {
		this("anonymous");
	}
	
	// Methods
	// @returns the owner of the cart
	public String getOwner() {
		return owner;
	}
	
	// @returns ArrayList of items in cart
	public ArrayList<String> getItems() {
		return items;
	}
	
	// @returns String representation of items in cart
	public String checkCart() {
		return items.toString();
	}
	
	// @returns String in the format of owner: [items]
	public String toString() {
		return owner + ": " + this.checkCart();
	}
	
	// Receives String item as parameter and adds it to items
	public void addItem(String item) {
		this.items.add(item);
	}
	
	// Receives String item as parameter and removes if that item exists
	public void removeItem(String item) {
		int index;
		while ((index = items.indexOf(item)) != -1)
			items.remove(index);
	}
	
	// Moves all of the items from other shopping car and add to
	// the items in this shopping cart.
	// The other shopping cart will have nothing left after this method
	public void transferItems(ShoppingCart other) {
		this.items.addAll(other.items);
		other.items.clear();
	}
}
