import java.util.*;

public class Average {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double average = 0;
        double[] data;
        data = new double[5];

        for (int i = 0; i < data.length; i++) {
            System.out.printf("Input data %d: ", i + 1);
            data[i] = input.nextDouble();
        }

        for (int i = 0; i < data.length; i++) {
            average += data[i];
        }
        average /= data.length;

        System.out.println("Average: " + average);

    }
}
