import java.util.*;
import java.io.*;
import java.awt.Point;

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
    // Read points from a file specified and returns ArrayList
    // containing the data read from the file
    // Parameters: String fileName
    // Returns ArrayList<Point>
    public static ArrayList<Point> readPointsFromFile(String fileName) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader(fileName) );

        ArrayList<Point> ret = new ArrayList<Point>();

        String line = br.readLine();
        while (line != null) {
            String[] x_y = line.split(" ");
            Point temp = new Point(Integer.parseInt(x_y[0]), Integer.parseInt(x_y[1]));
            ret.add(temp);

            line = br.readLine();
        }

        return ret;
    }

    // Returns the taxicab distance of point from the origin.
    // Parameters: Point point
    // Returns int
    public static int cabDistance(Point point) {
        int ret = 0;
        ret += (point.getX() > 0) ? point.getX() : -1 * point.getX();
        ret += (point.getY() > 0) ? point.getY() : -1 * point.getY();
        return ret;
    }

    // Print point's coordinates and cab distance
    // Parameters: Point point
    // Returns void
    public static void showPoint(Point point) {
        System.out.printf("(%d, %d)\t%d\n", (int)point.getX(), (int)point.getY(), cabDistance(point));
    }

    // Print all the points in an ArrayList
    // Parameters: ArrayList<Point> points
    // Returns void
    public static void showAllPoints(ArrayList<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            System.out.printf("[%d] ", i);
            showPoint(points.get(i));
        }
    }

    // Sort given array using bubble sort
    // Parameters: ArrayList<Point> points
    // Returns void
    public static void sort(ArrayList<Point> points){
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = 1; j < points.size() - i; j++) {
                // Swap
                if (cabDistance(points.get(j - 1)) > cabDistance(points.get(j))) {
                    Point temp = new Point(points.get(j - 1));
                    points.set(j - 1, points.get(j));
                    points.set(j, temp);
                }
            }
        }
    }

    // Returns the index of the first point in the array
    // that has the taxicab distance of distanceKey
    // Parameters: ArrayList<Point> points, int distanceKey
    // Returns int
    public static int binarySearch(ArrayList<Point> points, int distanceKey) {
        int low = 0;
        int high = points.size();
        while (low < high) {
            int ret = low + (high - low) / 2;
            if (cabDistance(points.get(ret)) > distanceKey)
                high = ret - 1;
            else if (cabDistance(points.get(ret)) < distanceKey)
                low = ret + 1;
            else
                return ret;
        }
        return -1;
    }
}
