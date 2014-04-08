import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// This is a little practice with writing and reading
// files and using a Point object

// Author: Naoki Mizuno
public class File_point_pracice {

	public static void main(String[] args) throws FileNotFoundException {
		// 4 lines of code to write it to a file
		File myFile = new File("just1point.txt");
		PrintWriter pw = new PrintWriter(myFile);
		
		pw.println("3 4");
		pw.println("100 200");
		pw.close();
		
		// Read this data into a point
		Point p1, p2;
		Scanner reader = new Scanner(myFile);
		int x, y;
		
		x = reader.nextInt();
		y = reader.nextInt();
		p1 = new Point(x, y);
		
		x = reader.nextInt();
		y = reader.nextInt();
		p2 = new Point(x, y);
		
		reader.close();
		
		// Note that p1 by itself really just calls
		// p1's toString() method
		System.out.println(p1);
		System.out.println(p2.toString());
		
		// Let's make an array containing these points
		// plus an extra
		Point[] myPoints = {p1, p2, new Point(60, 60)};
		
		new PointDisplay(myPoints, 10, false);
	}
}