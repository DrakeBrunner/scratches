
public class TriangleCompileCheck {

	public static void main(String[] args) {
		Triangle t = new Triangle(10, 10, 10, 10, "red");
		t.setColor("blue");
		t.setHeight(20);
		t.setWidth(20);
		t.setX(20);
		t.setY(20);
		String s = t.getColor();
		int i = t.getHeight();
		i = t.getWidth();
		i = t.getX();
		i = t.getY();
		Point[] ps = t.getVertices();
		boolean b = t.equals(t);
		b = t.isInsideBox(400, 400);
		Triangle t2 = new Triangle(t);
        System.out.println(t);
        System.out.println(t2);
        System.out.println(t.equals(t2));
        System.out.println(t == t2);
	}

}
