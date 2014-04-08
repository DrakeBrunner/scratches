import java.util.*;

public class Celsius_to_Fahrenheit {
    public static void main(String[] args) {
        double[] temperature = {-5, 0, 12, 68, 22.7, 100, 6};
        for (int i = 0; i < temperature.length; i++) {
            System.out.print(temperature[i] + "C is ");
            System.out.println(((9 / 5 ) * temperature[i] + 32) + "F.");
        }
    }
}
