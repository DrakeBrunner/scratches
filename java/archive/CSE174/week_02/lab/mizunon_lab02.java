// NAME: Naoki Mizuno
// DATE COMPLETED: Sep 7, 2012

import java.util.*;
public class mizunon_lab02 {
    public static void Problem_01_Diamond () {
        // This method displays a diamond figure with asterisks
        // i and j are for controlling loop
        int i, j, level;
        // Set how many levels there are to the center
        // Sample had 4 levels
        level = 4;

        // The upper part of diamond
        for (i = 1; i <= level; i++) {
            // Print spaces
            for (j = 0; j < level - i; j++) {
                System.out.print(" ");
            }
            // Print asterisks
            for (j = 0; j < i * 2 - 1; j++) {
                System.out.print("*");
            }
            // New line at the end of each line
            System.out.print('\n');
        }

        // Lower part of diamond
        for (i = 1; i <= level - 1; i++) {
            // Print spaces
            for (j = 0; j < i; j++) {
                System.out.print(" ");
            }
            // Print asterisks
            for (j = 0; j < (level - i) * 2 - 1; j++) {
                System.out.print("*");
            }
            // New line at the end of each line
            System.out.print('\n');
        }


    }
    public static void Problem_02_LeapYear () {
        // "one will be set to equal 525600"
        int normal_year = 525600;
        // "the other will be set to the number of minutes in one full day"
        int minutes_a_day;
        // Since a normal year has 365 days,
        minutes_a_day = normal_year / 365;
        // And since there are 366 days in a Leap Year,
        // using only one print statement,
        System.out.print("There are " + (366 * minutes_a_day) + " minutes in a Leap Year!\n");

    }
    public static void Problem_03_Stocks () {
        // Number of shares bought
        int shares = 600;
        // Price per share in dollars
        double price_per_share = 21.77;
        // Commission rate in percent
        double commission = 2;
        // Amount paid
        double amount_paid;

        // Change into o-to-1 display;
        commission /= 100;

        amount_paid = shares * price_per_share;

        // Print the amount paid
        System.out.println("Amount paid for the stock alone is $" + amount_paid);

        // Print the amount of commission
        System.out.println("Amount of the commission is $" + amount_paid * commission);

        // Print the total amount paid
        System.out.println("Total amount paid is $" + amount_paid * (1 + commission) );

    }

    public static void border() {
        for (int i = 0; i < 30; i++) {
            System.out.print("*");
        }
    }

    public static void main(String[] args) {
        // Print border and message for Problem_01
        border();
        System.out.println("Problem_01");

        Problem_01_Diamond();

        // Print border and message for Problem_02
        border();
        System.out.println("Problem_02");

        Problem_02_LeapYear();

        // Print border and message for Problem_03
        border();
        System.out.println("Problem_03");

        Problem_03_Stocks();
    }

}
