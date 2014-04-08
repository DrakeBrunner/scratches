// NAME: Naoki Mizuno
// DATE COMPLETED: Sep 7th, 2021

import java.util.*;

public class mizunon_lab03 {
    static Scanner stdin = new Scanner(System.in);

    public static void Question_01_Swap() {
        /* Scanner stdin = new Scanner(System.in); */

        int num1, num2, tmp;

        // Let the user input 2 integers
        System.out.print("Input num1: "); num1 = stdin.nextInt();
        System.out.print("Input num2: "); num2 = stdin.nextInt();

        // Show the 2 integers before swapping
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        // Print "SWAP" and swap
        System.out.println("SWAP");
        // Copy num1 to tmp
        tmp = num1;
        // Overwrite num1 with num2
        num1 = num2;
        // Finally, overwrite num2 with tmp, which is num1
        num2 = tmp;

        // Show the 2 integers after swapping
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
    }

    public static void Question_02_Digits() {
        /* Scanner stdin = new Scanner(System.in); */

        // i is for controlling loop
        int i, digits = 5;
        // divide_by is the number input is divided by
        // For loop sets the initial number to divide input
        // This is 10^digits
        int divide_by = 1;
        for (i = digits; i > 1; i--) {
            divide_by *= 10;
        }

        // Have the user input
        System.out.printf("Enter a %s digit number: ", digits);
        int input = stdin.nextInt();

        while (input > 0) {
            // Get only the first digit by dividing it by 10^digits (e.g. 4 from 42931)
            System.out.printf("Digit %d = %d\n", i, input / divide_by);

            // Leave the rest digits (e.g. 2931 of 42931)
            input %= divide_by;
            // Repeat the loop for the next digit
            divide_by /= 10;

            i++;
        }
    }

    public static void border() {
        for (int i = 0; i < 30; i++) {
            System.out.print("*");
        }
    }

    public static void main(String[] args) {
        border();
        System.out.println("Question_01");

        Question_01_Swap();

        border();
        System.out.println("Question_02");

        Question_02_Digits();
    }

}
