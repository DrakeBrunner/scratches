public class Base {
    private int a;

    public Base() {
        a = 20;
        System.out.print("P");
    }

    public Base(int a) {
        this.a = a;
        System.out.print("Q");
    }

    public Base(int a, int b) {
        this(a + b);
        System.out.print("R");
    }

    public String toString() {
        return "a = " + a;
    }

    public int getValue() {
        return 10 * this.a;
    }
}
