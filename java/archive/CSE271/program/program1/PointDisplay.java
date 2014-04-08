/**
 * A class designed to take an array of Point objects and display
 * it.  DON'T MODIFY THIS FILE IN ANY WAY.
 * @author Norm Krumpe
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PointDisplay extends JPanel {
	private static final long serialVersionUID = 1L;
	private Point[] points;
	private int diameter;
	private boolean connectTheDots;

	JFrame frame;

	/**
	 * Constructs a PointDisplay with a given array of points and a
	 * diameter to be used for displaying those points. Add this
	 * display to a JFrame object and it will automatically show its
	 * points.
	 * 
	 * @param points An array of Point objects to be displayed
	 * @param diameter The diameter, in pixels, of each point to be
	 *           displayed
	 */
	public PointDisplay(Point[] points, int diameter,
			boolean connectTheDots) {
		this.points = points;
		this.diameter = diameter;
		this.connectTheDots = connectTheDots;
		try {
			frame = new JFrame("Show Some Points");
			frame.setBounds(0, 0, 300, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(this);
			frame.setVisible(true);
		} catch (HeadlessException he) {
			// Assume we are running under the auto-grader
			doAutoGraderChecks();
		}
	}

	private void compareFiles(final String fileName1, final String fileName2) throws FileNotFoundException {
		final Scanner input1 = new Scanner(new File(fileName1));
		final Scanner input2 = new Scanner(new File(fileName2));
		
		while (input1.hasNext() && input2.hasNext()) {
			final int num1 = input1.nextInt();
			final int num2 = input2.nextInt();
			if (num1 != num2) {
				break;
			}
		}
		// Check if all data is exhausted. If so then files match
		// otherwise report an error
		if (input1.hasNext() || input2.hasNext()) {
			System.out.println("Files " + fileName1 + " and " + fileName2 + " do not match!");
		} else {
			System.out.println("Files " + fileName1 + " and " + fileName2 + " are perfect match");
		}
		
		input1.close();
		input2.close();
	}
	
	private void doAutoGraderChecks() {
		try {
			compareFiles("sorted.txt", "sorted_expected.txt");
			compareFiles("sortedAndRemoved.txt", "sortedAndRemoved_expected.txt");
		} catch (Exception exp) {
			System.out.println("Error during comparison of files: ");
			exp.printStackTrace(System.out);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double brightness = 0.0;


		if (points != null) {
			for (int i = 0; i < points.length; i++) {
				if (points[i] != null) {

					g.setColor(new Color((int)brightness, 0, 255 - (int)brightness));
					brightness += 256.0/points.length;

					// Draw the point
					g.fillOval(points[i].x - diameter / 2, points[i].y
							- diameter / 2, diameter, diameter);

					// Connect the dots, if desired:
					if (connectTheDots) {
						if (i > 0 && points[i - 1] != null) {
							g.drawLine(points[i - 1].x, points[i - 1].y,
									points[i].x, points[i].y);
						}
					}

				}

			}
		}
	}

}
