import java.util.*;
import java.io.*;
import java.awt.Point;

public class PointPractice {

	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        // Read from file and put it in an array
        System.out.print("Enter File Name to Read: ");
        String fileName = input.nextLine();

        Point[] myPoints = new Point[1];
        try {
            myPoints = readPoints(fileName);
        }
        catch (Exception e) {
            System.out.println("Error reading file" + e);
        }


        // Sort points
        sortPoints(myPoints);

        // Write to file
        try {
            writePoints(myPoints, "sorted.txt");
        }
        catch (Exception e) {
            System.out.println("Error writing file" + e);
        }


        // Remove points
        myPoints = removeSomePoints(myPoints);

        // Write to file
        try {
            writePoints(myPoints, "sortedAndRemoved.txt");
        }
        catch (Exception e) {
            System.out.println("Error writing file " + e);
        }

        // Graph it
        new PointDisplay(myPoints, 10, false);
	}

    // 1
    // Write the passed array to a file
	public static void writePoints(Point[] points, String fileName) throws Exception {
		PrintWriter pw = new PrintWriter(fileName);

        for (int i = 0; i < points.length; i++)
            pw.write((int)points[i].getX() + " " + (int)points[i].getY() + "\n");
        pw.flush();
        pw.close();
	}

    // 2
    // Read points from a file
    // Returns array
    public static Point[] readPoints(String fileName) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader(fileName) );

        ArrayList<Point> readPoints = new ArrayList<Point>();

        String line = br.readLine();
        while (line != null) {
            String[] x_y = line.split(" ");
            Point temp = new Point(Integer.parseInt(x_y[0]), Integer.parseInt(x_y[1]));
            readPoints.add(temp);

            line = br.readLine();
        }

        Point[] ret = readPoints.toArray(new Point[readPoints.size()]);

        return ret;
    }

    // 3
    // Sort given array
    public static void sortPoints(Point[] myPoints) {
        for (int i = 0; i < myPoints.length - 1; i++) {
            for (int j = 1; j < myPoints.length - i; j++) {
                double distanceA = (myPoints[j - 1].getX() * myPoints[j - 1].getX()) + (myPoints[j - 1].getY() * myPoints[j - 1].getY());
                double distanceB = (myPoints[j].getX() * myPoints[j].getX()) + (myPoints[j].getY() * myPoints[j].getY());

                // Swap
                if (distanceA > distanceB) {
                    Point temp = new Point(myPoints[j - 1]);
                    myPoints[j - 1] = myPoints[j];
                    myPoints[j] = temp;
                }
            }
        }
    }

    // 4
    // Remove points that are closer than 20
    public static Point[] removeSomePoints(Point[] myPoints) {
        ArrayList<Point> list = new ArrayList<Point>();

        for (int i = 0; i < myPoints.length; i++) {
            double diff = myPoints[i].getX() - myPoints[i].getY();

            if (diff >= 20 || diff <= -20)
                list.add(myPoints[i]);
        }

        Point[] ret = list.toArray(new Point[list.size()]);
        return ret;
    }
}
