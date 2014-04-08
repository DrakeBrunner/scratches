import java.util.*;
import java.io.*;
import java.awt.Point;

public class PointPractice {
    // Read points from a file specified and returns ArrayList
    // containing the data read from the file
    // Parameters: String fileName
    // Returns ArrayList<Point>
    public ArrayList<Point> readPointsFromFile(String fileName) throws Exception {
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
    public int cabDistance(Point point) {
        int ret = 0;
        ret += (point.getX() > 0) ? point.getX() : -1 * point.getX();
        ret += (point.getY() > 0) ? point.getY() : -1 * point.getY();
        return ret;
    }

    // Print point's coordinates and cab distance
    // Parameters: Point point
    // Returns void
    public void showPoint(Point point) {
        System.out.printf("%d, %d\t%d\n", (int)point.getX(), (int)point.getY(), cabDistance(point));
    }

    // Print all the points in an ArrayList
    // Parameters: ArrayList<Point> points
    // Returns void
    public void showAllPoints(ArrayList<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            System.out.printf("[%d] ", i);
            showPoint(points.get(i));
        }
    }

    // Sort given array using bubble sort
    // Parameters: ArrayList<Point> points
    // Returns void
    public void sort(ArrayList<Point> points){
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
    public int binarySearch(ArrayList<Point> points, int distanceKey) {
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
