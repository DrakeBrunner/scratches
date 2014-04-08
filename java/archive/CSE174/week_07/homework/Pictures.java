import java.util.*;

public class Pictures {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = input.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.printf("%2d  ", i);

            for (int j = 0; j < i; j++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }
}
