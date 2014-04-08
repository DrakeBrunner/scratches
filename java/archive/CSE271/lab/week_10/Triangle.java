import java.util.Arrays;

/**
 * Defines a simple triangle with integer side lengths,
 * along with methods for computing perimeter and area.
 * @author Norm
 *
 */

public class Triangle {

	private int[] sides;
	
	/**
	 * Constructs a triangle with specified side lengths.
	 * Note that side lengths must be positive, and must
	 * satisfy the triangle inequality theorem which says,
	 * informally, that the shortest distance between two
	 * points is along a straight line.
	 * @param side1 length of side 1
	 * @param side2 length of side 2
	 * @param side3 length of side 3
	 */
	public Triangle(int side1, int side2, int side3) {
		// Don't allow numbers that are less than or equal to zero
		if (side1 <= 0)
			throw new InvalidTriangleException(side1);
		if (side2 <= 0)
			throw new InvalidTriangleException(side2);
		if (side3 <= 0)
			throw new InvalidTriangleException(side3);
		
		// Make sure any two sides add to more than the other side
		if (side1 + side2 <= side3)
			throw new InvalidTriangleException(side1, side2);
		if (side2 + side3 <= side1)
			throw new InvalidTriangleException(side2, side3);
		if (side3 + side1 <= side2)
			throw new InvalidTriangleException(side3, side1);
		
		// Todo: figure out how to handle when
		// three valid sides add to more than Integer.MAX_VALUE
		
		sides = new int[] {side1, side2, side3};
	}
	
	/**
	 * Constructs a triangle with specified side lengths.
	 * Note that side lengths must be positive, and must
	 * satisfy the triangle inequality theorem which says,
	 * informally, that the shortest distance between two
	 * points is along a straight line.
	 * @param sides an array of 3 sides to be used as lengths
	 * for this triangle
	 */
	public Triangle(int[] sides) {
		// Best to make a setter
		this(sides[0], sides[1], sides[2]);
		
		if (sides.length != 3)
			throw new InvalidTriangleException();
		this.sides[0] = sides[0];
		this.sides[1] = sides[1];
		this.sides[2] = sides[2];
	}
	
	/**
	 * Constructs a deep copy of a specified triangle.
	 * @param orig the triangle to be copied.  The new triangle
	 * will be an independent copy of the original.
	 */
	public Triangle(Triangle orig) {
		this.sides = orig.sides;
	}
	
	/**
	 * Gets this triangle's perimeter.
	 * @return this triangle's perimeter (the sum of the lengths
	 * of the three sides)
	 */
	public double getPerimeter() {
		return sides[0] + sides[1] + sides[2];
	}
	
	/**
	 * Gets this triangle's area. Uses Heron's Formula which
	 * makes light work of computing the area given the triangle's
	 * three side lengths: area = sqrt(s*(s-a)*(s-b)*(s-c)) where
	 * a, b, and c are the side lengths, and s is the semiperimeter
	 * of the triangle: s=(a+b+c)/2. 
	 * @return this triangle's area
	 */
	public double getArea() {
		double semiPerimeter = getPerimeter()/2.0;
		
		return Math.sqrt(
				semiPerimeter * 
				(semiPerimeter - sides[0]) *
				(semiPerimeter - sides[1]) *
				(semiPerimeter - sides[2])
				);		
	}

	@Override
	public String toString() {
		return "Triangle [sides=" + Arrays.toString(sides)
				+ ", perimeter=" + getPerimeter() + ", area="
				+ String.format("%.1f", getArea()) + "]";
	}
}
