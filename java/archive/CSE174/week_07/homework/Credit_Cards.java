import java.util.*;

public class Credit_Cards {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final double max_single = 900;
        final double max_monthly = 3000;

        int transaction_number = 1;
        double maximum_transaction = 0;
        // Every transaction must be less or equal to the maximum single charge
        double minimum_transaction = max_single;
        double sum_transaction = 0;
        double transaction = 0;

        System.out.println("Welcome to Capital One!");
        while (true) {

            System.out.printf("Please start transaction #%d: $", transaction_number);
            transaction = input.nextDouble();

            if (transaction > max_single) {
                System.out.println("I'm sorry, you have exceeded the maximum single charge ($900).");
                break;
            }
            else if ((sum_transaction + transaction) > max_monthly) {
                System.out.println("I'm sorry, you have exceeded the maximum monthly charge ($3000).");
                break;
            }
            else {
                // Add
                sum_transaction += transaction;
                // Update minimum if necessary
                if (transaction < minimum_transaction) {
                    minimum_transaction = transaction;
                }
                // Update maximum if necessary
                if (transaction > maximum_transaction) {
                    maximum_transaction = transaction;
                }
            }

            // Increment the transaction number if
            // the transaction safely finishes
            transaction_number++;
        }

        // Change the minimum transaction to 0 if transaction_number is 1
        if (transaction_number == 1) {
            minimum_transaction = 0;
        }

        // Minus 1 since the last transaction is not included
        System.out.printf("There was a total of %d transactions this month.\n", transaction_number - 1);
        System.out.printf("Sum of all transactions: $%.2f\nMaximum transaction: $%.2f\nMinimum transaction: $%.2f\n",
                sum_transaction, maximum_transaction, minimum_transaction);
        System.out.println("Thank you for using Capital One.");
    }
}
