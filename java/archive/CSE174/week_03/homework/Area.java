import java.util.*;

public class Area {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double length =0, width = 0, height = 0;

        System.out.print("What's the length? ");
        length = input.nextDouble();

        System.out.print("What's the width? ");
        width = input.nextDouble();

        System.out.print("What's the height? ");
        height = input.nextDouble();

        System.out.println("The total area is: " +
                2 * ((length * width) + (width * height) + (height * length)));
    }
}
