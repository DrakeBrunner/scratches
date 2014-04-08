
public class Tester {
	public static void main(String[] args) {
		System.out.println("Starting test...");
		
		Point p1 = new Point();
		Point p2 = new Point(3, 4);
		Point p3 = new Point(p1);
		Point p4 = p2;
		Point pn = null;
		
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p4));
		System.out.println(p3.equals(p2));
		System.out.println(p1.equals(pn));

		Rectangle r1 = new Rectangle(p1, p2);
		Rectangle r2 = new Rectangle(p2, p3);
		
		System.out.println(r2.getArea());
		r1.translate(3, 7);
		System.out.println(r2.getArea());
	}
}
