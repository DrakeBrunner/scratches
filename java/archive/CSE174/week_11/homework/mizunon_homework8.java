import java.util.*;
import java.lang.Math;

public class mizunon_homework8 {
    public static void main(String[] args) {
        System.out.println("Present_Value_of_an_Investment\n");
        Present_Value_of_an_Investment();
    }

    public static void Present_Value_of_an_Investment() {
        System.out.format("3 arguments: %.2f\n", presentValue(100.34, 13, 30));
        System.out.format("4 arguments: %.2f\n", presentValue(100.34, 13, 30, 10));
    }

    public static double presentValue(double investment, int years, double rate_percent){
        double rate = rate_percent / 100;
        return (investment * Math.pow((1 + rate), years));
    }

    public static double presentValue(double investment, int years, double rate_percent, int compound){
        double rate = rate_percent / 100;
        return (investment * Math.pow((1 + rate / compound), years * compound));
    }
}
