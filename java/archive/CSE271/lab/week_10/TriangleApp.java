import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TriangleApp {

	public static void main(String[] args) {
		
		Triangle tri = new Triangle(4, 3, 5);
		System.out.println(tri);
		
		int[] s = {3, 4};
		Triangle t;
		
		try {
			t = new Triangle(s);
			System.out.println(t);
		}
		catch (InvalidTriangleException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		// Catches everything
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Grabs integers from a file, three at a time. Turns each into a
	 * triangle.
	 */
	public static void makeTrianglesFromIntegerFile(String filename,
			ArrayList<Triangle> tri) throws Exception {

		File f = new File(filename);
		Scanner in = new Scanner(f);

		while (in.hasNextInt()) {
			Triangle t = new Triangle(in.nextInt(), in.nextInt(),
					in.nextInt());
			tri.add(t);
		}

		in.close();
	}

	/*
	 * Writes each triangle to a file.
	 */
	public static void writeTrianglesToFile(String filename,
			ArrayList<Triangle> tri) throws Exception {
		File f = new File(filename);
		PrintWriter p = new PrintWriter(f);

		for (Triangle t : tri) {
			p.println(t);
		}

		p.close();
	}

}
