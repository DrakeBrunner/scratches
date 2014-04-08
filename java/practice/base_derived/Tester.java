public class Tester {
    public static void main(String[] args) {
        Base b1 = new Base(50);                 // Q
        System.out.println();
        Base b2 = new Derived(3, 5);            // PT
        System.out.println();
        Derived d1 = new Derived(2, 4, 6);      // QRSU
        System.out.println();
        // This causes compile error
        // Derived d2 = new Base(7, 9);

        Base b = new Base();
        Derived d = new Derived();

        System.out.println();

        System.out.println(b.getValue());           // 200
        System.out.println(b);                      // a = 20
        System.out.println(d.getValue());           // 325
        System.out.println(d);                      // a = 30
        System.out.println(d instanceof Base);      // true
        System.out.println(b instanceof Derived);   // false
    }
}
