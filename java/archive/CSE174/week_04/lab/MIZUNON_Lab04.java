import java.util.*;

public class MIZUNON_Lab04 {
    static Scanner input = new Scanner(System.in);

    final static double mpg = 32.2;
    final static double tank = 23;
    final static double price = 3.46;
    static double current_gas = 0;
    static double odometer = 200;

    public static void main(String[] args) {

        current_gas = tank;

        while (true) {
            System.out.println("=====================\n1. Drive\n2. Refuel\n3. Show Vitals\n4. Park my Car\n=====================");
            int choice = input.nextInt();
            if (choice == 1)
                drive();
            else if (choice == 2)
                refuel();
            else if (choice == 3)
                show_vitals();
            else {
                System.out.println("Good Night");
                break;
            }
        }
    }

    public static void drive() {
        System.out.println("How many miles are you going to drive? ");
        double miles_to_drive = input.nextDouble();

        if (current_gas * mpg - miles_to_drive < 0)
            System.out.println("Too bad, you ran out of gas!");
        else {
            current_gas -= miles_to_drive / mpg;
            odometer += miles_to_drive;
        }
    }

    public static void refuel() {
        System.out.println("How many gallons do you wan to buy? ");
        double gallons_to_buy = input.nextDouble();

        if (current_gas + gallons_to_buy > tank)
            System.out.println("You just wasted gas - it is overflowing onto the ground!");
        else {
            current_gas += gallons_to_buy;
            System.out.printf("Please pay: $%.2f\n", gallons_to_buy * price);
        }
    }

    public static void show_vitals() {
        System.out.println("===========================");
        System.out.printf("Fuel Level: %.1f Gallons\n", current_gas);
        System.out.printf("Odometer Reading: %.1f Miles\n", odometer);
        System.out.println("---------------------------");
        System.out.printf("You have %d Miles left in your tank\n", (int)(current_gas * mpg));
        System.out.println("===========================");
    }
}
