import java.util.ArrayList;

/**
 * A top-level convenience class that works on a family of shapes
 * (without worrying about the child class type) using
 * polymorphic method calls. 
 *
 */
public class ShapeWorker {
	/**
	 * Convenience method to print the set of shapes in a given shape list.
	 * This method is invoked from {@link ShapeTester#main(String[])} 
	 * method.
	 * 
	 * @param shapeList The list of shapes to be printed.
	 */
	public static void printShapes(ArrayList<Shape> shapeList) {
		for(Shape s: shapeList) {
			System.out.println(s);
		}
	}

	/**
	 * This method must compute the sum of the perimeters of all the
	 * shapes in the given shape list using the polymorphic
	 * {@link Shape#getPerimeter()} method.
	 * 
	 * @param shapeList The list of shapes whose average area is to
	 * be computed. It is assumed that the shapeList has at least
	 * one entry.
	 * 
	 * @return The sum of the perimeters of all the shapes in the given
	 * shapeList.
	 */
	public static int getTotalPerimeter(ArrayList<Shape> shapeList) {
		double sum = 0;
		for (int i = 0; i < shapeList.size(); i++)
			sum += shapeList.get(i).getPerimeter();
		return (int)sum;
	}
	
	/**
	 * This method must compute the average area of all the shapes in 
	 * the given shape list and return the average area back.
	 * 
	 * @param shapeList The list of shapes whose average area is to
	 * be computed. It is assumed that the shapeList has at least
	 * one entry.
	 * 
	 * @return The average area of the shapes in the shape list.
	 */
	public static double getAverageArea(ArrayList<Shape> shapeList) {
		double sum = 0;
		for (int i = 0; i < shapeList.size(); i++)
			sum += shapeList.get(i).getArea();
		return sum / shapeList.size();
	}
	
	/**
	 * This method determines the shape with the smallest area from
	 * a given list of shapes.
	 * 
	 * @param shapeList The list of shapes from where the smallest
	 * shape is to be determined.  It is assumed that the shapeList 
	 * has at least one entry.
	 * 
	 * @return The shape that has the smallest area.
	 */
	public static Shape getSmallestShape(ArrayList<Shape> shapeList) {
		Shape ret = null;
		for (int i = 0; i < shapeList.size(); i++) {
			if (ret == null)
				ret = shapeList.get(i);
			else if (ret.getArea() > shapeList.get(i).getArea())
				ret = shapeList.get(i);
		}
		return ret;
	}
}