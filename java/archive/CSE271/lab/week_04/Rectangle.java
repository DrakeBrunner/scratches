
public class Rectangle {
	private Point p1;
	private Point p2;
	
	public Rectangle(Point p1, Point p2) {
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
	}
	
	public int getWidth() {
		return Math.abs(p1.getX() - p2.getX());
	}
	
	public int getHeight() {
		return Math.abs(p1.getY() - p2.getY());
	}
	
	public int getArea() {
		return getWidth() * getHeight();
	}
	
	public int getPerimeter() {
		return 2 * (getWidth() + getHeight());
	}
	
	public String toString() {
		return "Rectangle [p1=" + p1 + ", p2=" + p2 + "]";
	}
	
	// Adds dx to both x coordinates, and dy to both y coordinates
	public void translate(int dx, int dy) {
		p1.setX(p1.getX() + dx);
		p2.setX(p2.getX() + dx);
		p1.setY(p1.getY() + dy);
		p2.setY(p2.getY() + dy);
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Rectangle))
			return false;
		
		if (this == other)
			return true;
		
		return (this.p1.equals(((Rectangle)other).p1) &&
				this.p2.equals(((Rectangle)other).p2));
	}
}
