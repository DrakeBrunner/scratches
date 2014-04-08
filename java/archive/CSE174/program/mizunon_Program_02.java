import java.util.*;

public class mizunon_Program_02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean continue_loop = true;

        while (continue_loop == true) {
            System.out.println("1. Bar Graph\n2. Seconds to Time\n3. Change Marker\n4. Exit");

            System.out.print("What do you want to do? ");

            switch (input.nextInt()) {
                case 1:
                        BarGraph();
                        break;
                case 2:
                        SecondsToTime();
                        break;
                case 3:
                        ChangeMaker();
                        break;
                default:
                        continue_loop = false;

            }
        }
    }

    public static void BarGraph() {
        Scanner input = new Scanner(System.in);

        int[] numbers;
        numbers = new int[5];

        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number from 1 to 50: ");
            // Have the user input 5 values
            numbers[i] = input.nextInt();
        }

        // Generates random number
        int standard = (int)(Math.random() * 50 + 1);
        System.out.println("Random Number: " + standard);


        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > standard) {
                // Print + if larger than standard
                for (int j = 0; j < numbers[i]; j++) {
                    System.out.print("+");
                }
            }
            else if (numbers[i] < standard) {
                // Print - if smaller than standard
                for (int j = 0; j < numbers[i]; j++) {
                    System.out.print("-");
                }
            }
            else {
                // Otherwise, print =
                for (int j = 0; j < numbers[i]; j++) {
                    System.out.print("=");
                }
            }
            System.out.println("");
        }

        // Display the scales
        for (int i = 0; i <= 50; i++) {
            System.out.print(i % 10);
        }
        System.out.println("");

    }

    public static void SecondsToTime() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the total number of seconds? ");
        int seconds = input.nextInt();

        // One hour is 3600 seconds
        int hours = seconds / 3600;
        seconds = seconds % 3600;
        int minutes = seconds / 60;
        seconds %= 60;

        System.out.printf("Your time is: %02d:%02d:%02d\n", hours, minutes, seconds);
    }

    public static void ChangeMaker() {
        Scanner input = new Scanner(System.in);

        System.out.print("How much is the bill for? ");
        double cost = input.nextDouble();

        System.out.print("How much are you paying? ");
        double payment = input.nextDouble();

        // Multiply by 100 so that it can be calculated as integers
        int balance = (int)(100 * (payment - cost));

        // But display it normally
        System.out.println("Change Due: " + balance / 100);

        System.out.printf("$100's:%4d\n", balance / 10000);
        balance %= 10000;
        System.out.printf("$ 50's:%4d\n", balance / 5000);
        balance %= 5000;
        System.out.printf("$ 20's:%4d\n", balance / 2000);
        balance %= 2000;
        System.out.printf("$ 10's:%4d\n", balance / 1000);
        balance %= 1000;
        System.out.printf("$  5's:%4d\n", balance / 500);
        balance %= 500;
        System.out.printf("$  1's:%4d\n", balance / 100);
        balance %= 100;
        System.out.printf("$ 0.25:%4d\n", balance / 25);
        balance %= 25;
        System.out.printf("$ 0.10:%4d\n", balance / 10);
        balance %= 10;
        System.out.printf("$ 0.05:%4d\n", balance / 5);
        balance %= 5;
        System.out.printf("$ 0.01:%4d\n", balance);         // Same as divided by 1
    }
}
