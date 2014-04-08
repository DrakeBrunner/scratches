import java.util.*;

public class Taxes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input income: ");
        double income = input.nextDouble();

        if (income <= 19440) {
            System.out.printf("Tax is $%f.\n", income * 0.535);
        }
        else if (income <= 63860) {
            System.out.printf("Tax is $%f.\n", income * 0.705);
        }
        else {
            // Meaning if it's over $63860
            System.out.printf("Tax is $%f.\n", income * 0.785);
        }
    }
}
