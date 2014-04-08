public class Derived extends Base {
    private int x;

    public Derived() {
        super(10, 20);
        this.x = 25;
        System.out.print("S");
    }

    public Derived(int x, int y) {
        this.x = x;
        System.out.print("T");
    }

    public Derived(int x, int y, int z) {
        this();
        this.x = x + y + z;
        System.out.print("U");
    }

    public int getValue() {
        return super.getValue() + this.x;
    }
}
