import java.util.*;

public class Coin_Flipping {
    public static void main(String[] args) {

        int consecutive_sequence = 0;
        int max_so_far = 0;

        for (int i = 0; i < 100000; i++) {

            // Evaluate randomly generated 1 or 0
            if ((int)(Math.random() + 0.5) == 0) {
                // Increment if head
                consecutive_sequence++;
            }
            else {
                // Reset count if tail
                consecutive_sequence = 0;
            }

            // Update maximum if necessary
            if (consecutive_sequence > max_so_far) {
                max_so_far = consecutive_sequence;
            }
        }

        System.out.printf("Maximum consecutive sequence of heads was %d times.\n", max_so_far);
    }
}
