

public class RecPentCompileCheck {

	private static void testRect() {
		Rectangle t = new Rectangle(10, 10, 10, 10, "red");
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
		Rectangle r2 = new Rectangle(t);
	}

	private static void testPentagon() {
		Pentagon h = new Pentagon(10, 10, 10, 10, "red");
		h.setColor("blue");
		h.setHeight(20);
		h.setWidth(20);
		h.setX(20);
		h.setY(20);
		String s = h.getColor();
		int i = h.getHeight();
		i = h.getWidth();
		i = h.getX();
		i = h.getY();
		Point[] ps = h.getVertices();
		boolean b = h.equals(h);
		b = h.isInsideBox(400, 400);
		Pentagon h2 = new Pentagon(h);
	}

	public static void main(String[] args) {

	}

}
