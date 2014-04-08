import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads point data from a file, and allows user-interactive 
 * sorting, searching, and displaying of those points.
 * @author Norm Krumpe
 */
public class PointReader {

	
	public static void main(String[] args) throws Exception {
		ArrayList<Point> points = new ArrayList<Point>();
		points = readPointsFromFile("points.txt");
		Scanner kb = new Scanner(System.in);
		while (menu(points, kb));
	}

	/**
	 * Runs an interactive search, prompting the user for
	 * a distance, and displaying where that point was found.
	 * @param points an array list of points to search (must be sorted)
	 * @param kb a Scanner to allow for keyboard interaction
	 */
	public static void findPointWithDistance(ArrayList<Point> points, 
			Scanner kb) {

		int dist;
		int foundAt;

		System.out.print("Find a point with what distance from origin? ");
		dist = kb.nextInt();

		foundAt = binarySearch(points, dist);
		if (foundAt > -1)
			System.out.println("FOUND AT " + foundAt);
		else
			System.out.println("NOT FOUND");

	}

	/**
	 * Display an interactive menu of actions to perform with 
	 * a given set of points.
	 * @param points an array of points to work with
	 * @return false if the user chooses to quit, true otherwise
	 */
	public static boolean menu(ArrayList<Point> points, Scanner kb) {    
		int choice;

		System.out.println("What to do next:");
		System.out.println("1. Show all points");
		System.out.println("2. Sort the points");
		System.out.println("3. Find point with given distance from (0,0)");
		System.out.println("4. Quit");    

		do {
			System.out.print("Enter choice: ");
			choice = kb.nextInt();
		}  while (choice < 1 || choice > 4);

		switch (choice) {
		case 1: showAllPoints(points);
		break;
		case 2: sort(points);
		break;
		case 3: findPointWithDistance(points, kb);
		}
		return choice != 4;
	}
}