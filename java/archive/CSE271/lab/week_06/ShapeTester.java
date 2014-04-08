import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Top-level class to test implementation of various methods in Rectangle and Circle.
 * 
 * DO NOT MODIFY THIS CLASS.
 */
public class ShapeTester {
	/**
	 * @param args
	 * @throws FileNotFoundException Throws an exception if the shapes.txt file could not
	 * be read.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Shape> shapeList = new ArrayList<Shape>();
		Scanner shpData = new Scanner(new File("shapes.txt"));
		while (shpData.hasNext()) {
			switch (shpData.next().charAt(0)) {
			case 'R': shapeList.add(new Rectangle(shpData.nextInt(), shpData.nextInt()));
			break;
			case 'T': shapeList.add(new Triangle(shpData.nextInt(), shpData.nextInt(), shpData.nextInt()));
			break;
			case 'C': shapeList.add(makeCircle(shpData.nextInt()));
			}
		}
		shpData.close();
		// Use helper methods to print some information
		System.out.println("List of shapes:\n-------------------------------------");
		ShapeWorker.printShapes(shapeList);
		System.out.println("-----------------------------------");
		// Print the total perimeter
		System.out.println("The total perimeter of all shapes : " + ShapeWorker.getTotalPerimeter(shapeList));
		System.out.println("Average area of all shapes        : " + ShapeWorker.getAverageArea(shapeList));
		System.out.println("The smallest shape is             : " + ShapeWorker.getSmallestShape(shapeList));
	}
	
	private static final Shape makeCircle(int radius) {
		Shape circle = null;
		try {
			Class<?> circleClass = Class.forName("Circle");
			circle = (Shape) circleClass.getConstructor(int.class).newInstance(radius);
		} catch (Exception e) {
			// Until Circle class is correctly implemented an exception will be thrown
			System.out.println("Circle class has not been implemented?");
		}		
		return circle;
	}
}
