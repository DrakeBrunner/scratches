import java.util.*;

public class Min_and_Max {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input 2 numbers: ");
        System.out.println("The maximum number is " +
            myMax(input.nextInt(), input.nextInt()) );

        System.out.print("Input 2 numbers: ");
        System.out.println("The minimum number is " +
            myMin(input.nextInt(), input.nextInt()) );
    }

    public static int myMax(int x, int y) {
        if (x > y)
            return x;
        else
            return y;
    }

    public static int myMin(int x, int y) {
        if (x < y)
            return x;
        else
            return y;
    }
}
