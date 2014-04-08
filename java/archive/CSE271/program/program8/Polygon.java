import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Point;

/**
 * This class represents a polygon with its points,
 * color, and whether the points are filled or not.
 *
 * @author Naoki Mizuno
 */

public class Polygon {
    private ArrayList<Point> points;
    private Color color;
    private boolean isFilled;
    private boolean isClosed;

    /**
     * Creates a new polygon. Sets the color to a
     * random color, and set isFilled to true because
     * it is only not filled when the user right clicks.
     */
    public Polygon() {
        this.points = new ArrayList<Point>();
        this.color = new Color((int)(Math.random() * 256),
                               (int)(Math.random() * 256),
                               (int)(Math.random() * 256));
        this.isFilled = false;
        this.isClosed = false;
    }

    /**
     * Returns how many points there are in the polygon.
     * @return The number of points this polygon has.
     */
    public int getNumOfPoints() {
        return points.size();
    }

    /**
     * Returns the color of the polygon as a Color object.
     * This method does not return values like (32, 139, 174) because
     * it just returns a Color object. Use getColorString() if you
     * want to get a String representation of this polygon's color.
     * @return The color of this polygon in Color
     */
    public Color getColor() {
        return new Color(color.getRGB());
    }

    /**
     * Returns a String representation of the color of this polygon.
     * It shows the R, G, and B value with 3 numbers.
     * @return A String that represents the color of this polygon.
     */
    public String getColorString() {
        String ret = String.format("(R = %d, G = %d, B = %d)\n",
                color.getRed(), color.getGreen(), color.getBlue());
        return ret;
    }
    
    /**
     * Returns an ArrayList of Point that contains all the points in this polygon.
     * @return A deep-copied ArrayList of the points in this polygon.
     */
    public ArrayList<Point> getPoints() {
    	ArrayList<Point> ret = new ArrayList<Point>(points);
    	return ret;
    }

    /**
     * Returns whether each point should be filled or not.
     * @return Whether the points should be filled or not.
     */
    public boolean getIsFilled() {
        return isFilled;
    }

    /**
     * Returns whether this polygon is closed.
     * The polygon can be closed when it has more than 2 points.
     * @return True if this polygon is closed, false if not.
     */
    public boolean getIsClosed() {
        return isClosed;
    }

    /**
     * Sets the value of isFilled to the given value.
     * @param isFilled True if the points should be filled, false if not.
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    /**
     * Sets the isClosed value to the given value.
     * When this is set to true, it means that the polygon is
     * completed and that the points form a closed loop.
     * @param isClosed True if the polygon is to be closed.
     */
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    /**
     * Adds one point to this polygon from the given Point object.
     * It makes a deep copy of the object given.
     * @param point The point to be added to this polygon in Point
     */
    public void addPoint(Point point) {
        addPoint(point.x, point.y);
    }

    /**
     * Adds a point to this polygon by using the given X and Y coordinates.
     * @param x The X coordinate of the point to be added.
     * @param y The Y coordinate of the point to be added.
     */
    public void addPoint(int x, int y) {
        Point p = new Point(x, y);
        this.points.add(p);
    }
    
    /**
     * Empties the ArrayList of Points, clearing out all the points in this polygon 
     */
    public void clearPoints() {
    	points.clear();
    }

    /**
     * Returns a String representation of this polygon.
     * It is useful both when checking the property of the polygon
     * and when writing to a file. The format is the following:
     * color in one integer
     * whether it's filled or not
     * x y
     * ...
     * (an empty line)
     */
    public String toString() {
        String ret = "";
        ret += color.getRGB() + "\n";
        ret += (isFilled ? "true" : "false") + "\n";
        ret += (isClosed ? "true" : "false") + "\n";
        for (int i = 0; i < points.size(); i++)
            ret += points.get(i).x + " " +  points.get(i).y + "\n";
        ret += "\n";

        return ret;
    }

    /**
     * A static method that reads the polygon data from the given input and creates
     * a new polygon from it. The format must be the same as the toString method.
     * @param input The input in which the polygon data is stored.
     * @return The new Polygon object that was created according to the input data.
     */
    public static Polygon readPolygon(Scanner input) {
        // First, create a new empty polygon
        Polygon ret = new Polygon();

        String line = input.nextLine();
        // The first line is Color
        ret.color = new Color(Integer.parseInt(line));

        // The second line is whether it's filled or not
        line = input.nextLine();
        if (line.equals("true"))
            ret.isFilled = true;

        // The third line is whether it's closed or not
        line = input.nextLine();
        if (line.equals("true"))
            ret.isClosed = true;

        line = input.nextLine();
        // An empty line represents the end of one polygon
        while ((!(line.equals("")))) {
            // Split the line by space
            String[] coordinates = line.split(" ");
            ret.addPoint(Integer.parseInt(coordinates[0]),
                         Integer.parseInt(coordinates[1]));
            line = input.nextLine();
        }

        return ret;
    }
}
