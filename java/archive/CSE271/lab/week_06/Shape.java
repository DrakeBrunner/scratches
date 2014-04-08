
/**
 * A common parent class (aka base class) for a family of shapes.
 *
 * This common base class defines selected methods that can be used 
 * in a polymorphic manner to operate on a collection of shapes.
 */
public class Shape {
	private String type;
	
	/**
	 * Constructor.
	 * 
	 * Creates a shape of a given type.
	 * 
	 * @param type The type of the shape. This value is either
	 * "circle", "triangle", or "rectangle"
	 */
	public Shape(String type) {
		if ("circle triangle rectangle".indexOf(type) < 0) {
			throw new IllegalArgumentException("The type of the shapce is invalid");
		}
		this.type = type;
	}
	
	/**
	 * Return the type of this shape.
	 * 
	 * @return The type of this shape. The returned value is either
	 * "circle", "triangle", or "rectangle" depending on the shape.
	 */
	public final String getType() {
		return type;
	}

	/**
	 * Method to determine the area of this shape.
	 * 
	 * @return Returns the area of this shape.
	 */
	public double getArea() {
		System.out.println("Shape has no idea how to compute area");
		return -1;
	}
	
	/**
	 * Method to determine the perimeter of this shape.
	 * 
	 * @return Returns the perimeter of this shape.
	 */
	public double getPerimeter() {
		System.out.println("Shape has no idea how to compute area");
		return -1;
	}
	
	@Override
	public String toString() {
		return "Shape(" + type + ")";
	}
}
