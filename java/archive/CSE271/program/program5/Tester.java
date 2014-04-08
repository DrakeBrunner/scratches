public class Tester {
    public static void main(String[] args) {

        String[] array = {"-10/2034", "-5/3", "3002/-5", "6/21056810", "10/-31", "-5/-8", "28/14"};

        for (int i = 0; i < array.length; i++) {
            Fraction f = new Fraction(array[i]);
            System.out.println(f.toMixedNumber());
            System.out.println(f.toDouble());
            System.out.println(f.toString());
            f.simplify();
            System.out.println(f.toPrettyString());
            Fraction f2 = f.getReciprocal();
            Fraction f3 = f2.multiply(f);
            System.out.println(f2.toString());
            System.out.println(f3.toString());
            System.out.println();
            System.out.println();
        }
    }
}
