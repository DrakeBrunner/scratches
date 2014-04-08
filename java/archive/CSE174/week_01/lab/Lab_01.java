// NAME: Naoki Mizuno
// DATE COMPLETED: Sep 7, 2012
import java.util.*;
public class Lab_01 {
    public static void main(String[] args) {
        int cost;
        double price;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cost as an integer: ");
        cost = input.nextInt();
        price = 1.25 * cost;
        System.out.println("The price is: " + price);
    }
}
