import java.util.*;

public class Celsius_to_Fahrenheit {
    public static int cToF(int x) {
        return (int)(Math.round( (9.0 / 5.0) * x + 32 ));
    }
    public static void main(String[] args) {
        for (int i = -40; i <= 100; i += 5) {
            System.out.printf("%3dC = %3dF\n", i, cToF(i));
        }
    }
}
