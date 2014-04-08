
/**
 * A customized shape class that implements a Triangle with three sides.
 * This class extends Shape (establishing an "is a" relationship between
 * Triangle and Shape) and overrides specific methods to provide customized
 * information about a Triangle.
 */
public class Triangle extends Shape {
	/**
	 * First side of the Triangle
	 */
	private int side1;
	
	/**
	 * Second side of the Triangle
	 */
	private int side2;
	
	/**
	 * Third sides of the Triangle
	 */
	private int side3;
	
	/**
	 * Constructor to create a scalene triangle.
	 * 
	 * Note that the lengths of the sides must satisfy the triangle property
	 * that sum of any two sides must be greater than the third. If the
	 * triangle property is not met then an exception is thrown.
	 * 
	 * @param side1 The length of first side of the triangle.
	 * @param side2 The length of the second side of the triangle.
	 * @param side3 The length of the third side of the triangle.
	 */
	public Triangle(int side1, int side2, int side3) {
		super("triangle");
		// Check triangle property
		if (((side1 + side2) < side3) || ((side1 + side3) < side2) || ((side2 + side3) < side1)) {
			throw new IllegalArgumentException("The length of the sides does not meet requirements");
		}
		// Initialize instance variables
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	@Override
	public double getArea() {
		// Compute area of triangle using Hero's formula.
		double p = (side1 + side2 + side3) / 2.0;
		return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
	}

	@Override
	public double getPerimeter() {
		return (side1 + side2 + side3);
	}
	
	@Override
	public String toString() {
		return "Triangle(" + side1 + ", " + side2 + ", " + side3 + ")";
	}
}
