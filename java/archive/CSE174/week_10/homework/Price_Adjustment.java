import java.util.*;

public class Price_Adjustment {
    public static void main(String[] args) {
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
}
