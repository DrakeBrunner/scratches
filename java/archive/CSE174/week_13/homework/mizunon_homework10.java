import java.util.*;

public class mizunon_homework10 {
    public static void main(String[] args) {
        Sieve_of_Eratosthenes();
    }

    public static void Sieve_of_Eratosthenes() {
        Scanner input = new Scanner(System.in);

        System.out.print("Input a number between 2 and 500: ");
        int[] numbers = new int[input.nextInt() - 1];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = i + 2;

        // The mark  that it's not a prime number is -1
        for (int i = 0; i < numbers.length; i++) {
            // Start from the next value
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == -1)
                    break;
                if (numbers[j] % numbers[i] == 0)
                    // Set mark
                    numbers[j] = -1;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == -1)
                continue;
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
}
