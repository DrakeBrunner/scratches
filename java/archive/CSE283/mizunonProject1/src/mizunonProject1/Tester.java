package mizunonProject1;

public class Tester {
    public static void main(String[] args) {
        testHit();

        System.out.println("Tester has ended!");
    }

    public static void testHit() {
        Ship s = new Ship(1, 2, 3, false);
        System.out.println(s.isHit(1, 1));
        System.out.println(s.isHit(1, 2));
        System.out.println(s.isHit(2, 1));
        System.out.println(s.isHit(1, 5));
    }
}