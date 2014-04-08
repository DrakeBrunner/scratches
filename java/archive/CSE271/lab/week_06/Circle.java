
public class Circle extends Shape {
	// The radius of the circle
	private int radius;
	
	// Constructor
	public Circle (int radius) {
		super("circle");
		
		if (radius < 0)
			throw new IllegalArgumentException("Radius for circle must be positive");
		this.radius = radius;
	}
	
	/**
	 * gets the area of the circle in double
	 * @return the area of the circle in double
	 */
	public double getArea() {
		return Math.PI * (double)radius * (double)radius;
	}
	
	/**
	 * gets the perimeter of the circle in double
	 * @return the perimeter of the circle in double
	 */
	public double getPerimeter() {
		return 2 * Math.PI * (double)radius;
	}
	
	/**
	 * returns a String representation for the circle
	 * @return the String representation of this circle
	 */
	public String toString() {
		return "Circle(" + radius + ")";
	}
}