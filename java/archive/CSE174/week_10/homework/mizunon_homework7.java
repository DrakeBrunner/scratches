import java.util.*;

public class mizunon_homework7 {
    public static void main(String[] args) {
        System.out.println("Hello World Revisited");
        Hello_World_Revisited();

        System.out.println("*********************************");

        System.out.println("Price Adjustment");
        Price_Adjustment();
    }

    // Hello World Revisited
    public static void Hello_World_Revisited() {
        int rounds = getPos();

        for (int i = 0; i < rounds; i++) {
            System.out.println("Hello There");
        }
    }
    public static int getPos() {
        Scanner input = new Scanner(System.in);

        int user_input;
        while (true) {

            System.out.println("Please input a positive integer");
            user_input = input.nextInt();

            if (user_input > 0)
                break;
        }

        return user_input;
    }
    // End of Hello World Revisited

    // Price Adjustment
    public static void Price_Adjustment() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter price, percent, and increase/decrease");
        System.out.println("Please pay: " +
               bumpMe(input.nextInt(), input.nextInt(), input.nextBoolean())
               );
    }

    public static int bumpMe(int price, int increase, boolean updown) {
        int inc_dec = 1;
        double rate = (double)increase / 100;

        if (updown == false)
            inc_dec *= -1;

        return (int)Math.round(price * (1 + inc_dec * rate));
    }
    // End of Price Adjustment
}
