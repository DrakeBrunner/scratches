
public class Point {
	private int x, y;

	// Constructors
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this(1, 1);
	}
	
	public Point(Point other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}
	
	public boolean equals(Object other) {
		// if the reference is the same
		if (this == other)
			return true;
		
		//if (other == null)
			//return false;
		
		// return false if it's not a Point object
		// This will check if other is null
		if (!(other instanceof Point))
			return false;
		
		return (x == ((Point)other).x && y == ((Point)other).y);
	}
}
