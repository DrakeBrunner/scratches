/**
 * A customized shape class that implements a Rectangle with width and height.
 * This class extends Shape (establishing an "is a" relationship between
 * Rectangle and Shape) and overrides specific methods to provide customized
 * information about a Rectangle.
 */
public class Rectangle extends Shape {
	/**
	 * The width of the rectangle.
	 */
	private int width;
	
	/**
	 * The height of the rectangle.
	 */
	private int height;
	
	/**
	 * Creates a rectangle with the given width and height.
	 * 
	 * If the width and height of the rectangle are negative then this method throws
	 * an exception.
	 * 
	 * @param width The width of the rectangle.
	 * @param height The height of the rectangle.
	 */
	public Rectangle(int width, int height) {
		super("rectangle");
		if ((width < 0) || (height < 0)) {
			throw new IllegalArgumentException("Width & height for rectangle must be positive");
		}
		this.width = width;
		this.height = height;
	}
	
	/**
	 * get the area for the rectangle
	 * @return the Area in double
	 */
	public double getArea() {
		return (double)width * (double)height;
	}
	
	/**
	 * get the perimeter of the rectangle
	 * @return the perimeter in double
	 */
	public double getPerimeter() {
		return ((double)width + (double)height) * 2;
	}
	
	/**
	 * returns a String representation of the rectangle
	 * @return String representation of the rectangle
	 */
	public String toString() {
		return "Rectangle(" + width + ", " + height + ")";
	}
}