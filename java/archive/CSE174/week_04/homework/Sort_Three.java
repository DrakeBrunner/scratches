import java.util.*;

public class Sort_Three {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input first number: ");
        int a = input.nextInt();
        System.out.print("Input second number: ");
        int b = input.nextInt();
        System.out.print("Input third number: ");
        int c = input.nextInt();

        if (a > b) {
            if (b > c) {
                System.out.printf("%d < %d < %d\n", c, b, a);
            }
            else if (c > a) {
                System.out.printf("%d < %d < %d\n", b, a, c);
            }
            else {
                System.out.printf("%d < %d < %d\n", b, c, a);
            }
        }
        else {
            if (c > b) {
                System.out.printf("%d < %d < %d\n", a, b, c);
            }
            else if (a > c) {
                System.out.printf("%d < %d < %d\n", c, a, b);
            }
            else {
                System.out.printf("%d < %d < %d\n", a, c, b);
            }
        }
    }
}
