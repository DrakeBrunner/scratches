
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * A class that draws various shapes. You should NOT modify anything in this
 * file (but feel free to read through the code) . If you think there is a
 * problem with this code, please contact your instructor.
 * 
 * @author DJ Rao, Keith Frikken, Lukasz Opyrchal
 * 
 */
public class ShapeDisplayer extends JFrame {

	/**
	 * To keep eclipse happy
	 */
	private static final long serialVersionUID = -2029318760329821513L;
	private JLabel mouseLocation;
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();

	public ShapeDisplayer() {
		super("Shape Display");
		setLayout(new BorderLayout(1, 1));
		setPreferredSize(new Dimension(400, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		// Create a scroll pane to contain the components.
		JScrollPane jsp = new JScrollPane(new Board());
		add(jsp, BorderLayout.CENTER);
		// Create a JLabel to display mouse coordinates.
		mouseLocation = new JLabel("  Mouse Location: x=  " + ", y=");
		add(mouseLocation, BorderLayout.SOUTH);
		// Pack and show
		pack();
		setVisible(true);

	}

	/**
	 * Adds a shape to the ShapeDisplayer. There is a better way to do this, but
	 * we need to cover polymorphism and abstract classes first. Shape must be a
	 * triangle, rectangle, or star.
	 * 
	 * @param newShape
	 *            The shape to add.
	 */
	public void addShape(Shape newShape) {

		if (newShape.isInsideBox(300, 300))
			shapeList.add(newShape);

		repaint();
	}

	/**
	 * For backwards compatibility.
	 * 
	 * @param newTriangle
	 */
	public void addTriangle(Triangle newTriangle) {
		addShape(newTriangle);
	}

	/**
	 * A board class that extends JComponent, represents the grid area
	 * 
	 * @author DJ Rao, Keith Frikken
	 * 
	 */
	private class Board extends JComponent {
		/**
		 * To keep eclipse happy.
		 */
		private static final long serialVersionUID = -7223666151237066589L;

		/**
		 * A default constructor.
		 */
		public Board() {
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent me) {
					mouseLocation.setText("  Mouse Location: x=  " + me.getX()
							+ ", y=" + me.getY());
				}
			});
		}

		/**
		 * Converts a string color to a java.awt.Color. If the color is not
		 * valid it returns gray.
		 * 
		 * @param colorWord
		 *            The string for the color
		 * @return The java.awt.Color corresponding to the string.
		 */
		private Color toColor(String colorWord) {
			Color FixedColors[] = { Color.BLUE, Color.GREEN, Color.YELLOW,
					Color.RED, Color.LIGHT_GRAY, Color.WHITE };
			int colorCode = "blue   green  yellow red    gray   white"
					.indexOf(colorWord.toLowerCase());
			if (colorCode < 0)
				return Color.pink;
			return FixedColors[colorCode / 7];
		}

		/**
		 * Sets the preferred size, but it is not explicitly called.
		 */
		public Dimension getPreferredSize() {
			Dimension size = new Dimension(200, 200);
			return size;
		}

		/**
		 * Paints all triangles in the triangle list.
		 */
		public void paintComponent(Graphics g) {
			paintBackground(g);
			for (int i = 0; (i < shapeList.size()); i++) {
				Point[] points = new Point[0];
				Shape current = shapeList.get(i);

				points = current.getVertices();

				Color shapeColor = toColor(current.getColor());
				Polygon vertices = new Polygon();
				for (Point p : points) {
					vertices.addPoint(p.getXCoor(), p.getYCoor());
				}
				g.setColor(new Color(shapeColor.getRed(),
						shapeColor.getGreen(), shapeColor.getBlue(), 200));
				g.fillPolygon(vertices);
				g.setColor(shapeColor);
				g.drawPolygon(vertices);
			}

		}

		/**
		 * Creates the background. Makes the grid and sets the backgroun to
		 * black.
		 * 
		 * @param g
		 *            A graphic component.
		 */
		public void paintBackground(Graphics g) {
			final int Width = getWidth();
			final int Height = getHeight();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			// Draw horizontal lines and print labels with small font.
			g.setFont(g.getFont().deriveFont(g.getFont().getSize() - 2.0f));
			for (int y = 0; (y < Height); y += 50) {
				g.drawLine(0, y, Width, y);
				g.drawString("" + y, 0, y - 2);
				g.drawString("" + y, Width - 25, y - 2);
			}
			// Draw vertical lines and print labels.
			for (int x = 0; (x < Width); x += 50) {
				g.drawLine(x, 0, x, Height);
				g.drawString("" + x, x + 1, 10);
				g.drawString("" + x, x + 1, Height - 1);
			}
		}
	}

}
