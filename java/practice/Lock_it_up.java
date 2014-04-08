import java.util.*;

public class Lock_it_up {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int code;

        for (int i = 0; i < 15; i++) {

            // Initially [0]
            int current_position = 0;
            // 1 represents counterclockwise
            int spin_direction = 1;
            int output[] = new int[4];

            for (int j = 0; j < 4; j++) {
                code = input.nextInt();
                if (code > 7 || code < 0)
                    System.out.println("Invalid code will result in error!");

                if (current_position + spin_direction * code > 0) {
                    output[j] = (current_position + spin_direction * code) % 8;
                    current_position = (current_position + spin_direction * code) % 8;
                }
                else {
                    output[j] = 8 - abs_diff(current_position, code) % 8;
                    current_position = 8 - abs_diff(current_position, code) % 8;
                }

                // Set the rotation to opposite direction
                spin_direction *= -1;
            }

            System.out.printf("%d %d %d %d\n", output[0], output[1], output[2], output[3]);


        }
    }

    public static int abs_diff(int a, int b) {
        if (a > b)
            return a - b;

        return b - a;
    }
}