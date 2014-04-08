import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Store store = new Store();
        store.add(new Item("Bread", 175, 10));
        store.add(new Item("Apple", 25, 10));
        store.add(new Item("Bread", 175, 10));
        store.add(new Item("Apple", 25, 10));
        store.add(new Item("Coupon", -10, 10));
        System.out.println("Initial inventory of the store: \n" + store);

        store.sellItem("Bread", 100);
        store.sellItem("BreadLoaf", 100);
        store.sellItem("Bread", 5);
        System.out.println("Store after purchase of 5 Bread:\n" + store);

        store.returnItemToInventory("Blah", 1);
        store.returnItemToInventory("Bread", 1);
        System.out.println("Store after return of 1 Bread:\n" + store);
        /* Scanner input = new Scanner(System.in); */
        /* Store store = new Store(input); */
        /* Store store = new Store(); */
        /* Item item = new Item("Bread", 175, 10); */
        /* store.add(item); */
        /* Item item3 = new Item("Bread", 175, 20); */
        /* store.add(item3); */
        /* Item item2 = new Item("Apple", 50, 20); */
        /* store.add(item2); */
        /* // This should override the first bread */
        /* Item item3 = new Item("Bread", 200, 20); */
        /* store.add(item3); */

        /* System.out.println(store.toString()); */

        /* Item tmp; */
        /* System.out.println("selling item more than they got"); */
        /* System.out.println((tmp = store.sellItem("Bread", 13))); */
        /* System.out.println("sell item that doesn't exist"); */
        /* store.sellItem("no", 10); */

        /* System.out.println("num of Bread should be 7"); */
        /* System.out.println(store.findItem("Bread").getQuantity()); */
        /* System.out.println("what the customer got"); */
        /* System.out.println(tmp.getName() + " " + tmp.getQuantity()); */
        /* System.out.println("testing whether the price is same"); */
        /* System.out.println(tmp.toString()); */

        /* tmp = store.findItem("Snapple"); */
        /* System.out.println("this is what was found"); */
        /* System.out.println(tmp.toString()); */

        /* System.out.println("returning item"); */
        /* if (store.returnItemToInventory("nope", 4)) { */
        /*     System.out.println("successfully returned"); */
        /*     System.out.println(store.toString()); */
        /* } */
        /* else */
        /*     System.out.println("failed to return"); */

        /* System.out.println("tst getQuantity"); */
        /* System.out.println(item.getQuantity()); */

        /* System.out.println("tst changeQuantity"); */
        /* item.changeQuantity(10); */
        /* System.out.println(item.getQuantity()); */

        /* System.out.println("tst toString"); */
        /* System.out.println(item.toString()); */

        /* System.out.println("tst getName"); */
        /* System.out.println(item.getName()); */

        /* System.out.println("tst getTotalPrice"); */
        /* System.out.println(item.getTotalPrice()); */
        /* Scanner keyboard = new Scanner(System.in); */
        /* Store store = new Store(keyboard); */
    }
}
