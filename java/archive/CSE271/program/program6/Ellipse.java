public class Ellipse extends Shape {
    /**
     * creates a new Ellipse object
     * @param x x value for the new object
     * @param y y value for the new object
     * @param height height for the new object
     * @param width width for the new object
     * @param color color of the new object
     */
    public Ellipse(int xPos, int yPos, int newWidth, int newHeight, String newColor) {
        super(xPos, yPos, newWidth, newHeight, newColor);
    }

    /**
     * copies the given Ellipse object
     * @param other original Ellipse object
     */
    public Ellipse(Ellipse other) {
        super(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.getColor());
    }

    /**
     * returns points of the circle
     * @return an array containing the points of this circle
     */
    @Override
    public Point[] getVertices() {
        Point[] ret = new Point[500];
        int a = (int)(getWidth() / 2);
        int b = (int)(getHeight() / 2);
        /* int x_temp = getX() + a; */
        /* int y_temp = getY() + b; */
        for (int i = 0; i < ret.length; i++) {
            int x_temp = (int)((a * Math.cos(-i * 2 * Math.PI / ret.length)) + getX() + a);
            int y_temp = (int)((b * Math.sin(-i * 2 * Math.PI / ret.length)) + getY() + b);

            ret[i] = new Point(x_temp, y_temp);
        }
        return ret;
    }

    /**
     * returns a String representation of the variables in this object
     * @return a String representation of variables
     */
    @Override
    public String toString() {
        String ret =  "Ellipse:";
        int major = getWidth() > getHeight() ? (int)(getWidth() / 2) : (int)(getHeight() / 2);
        int minor = getWidth() < getHeight() ? (int)(getWidth() / 2) : (int)(getHeight() / 2);
        ret += " semi-major axis = " + major;
        ret += " semi-minor axis = " + minor;
        ret += super.toString();
        return ret;
    }

    /**
     * check to see if other has the exact same value as this object
     * @param other an Ellipse object that will be compared
     * @return true if "other" has the same value as this object. false if not
     */
    @Override
    public boolean equals(Object other) {
        // Return false if other is null
        if (other == null)
            return false;

        // If other is not an instance of Ellipse,
        // it's not worth going further
        if (!(other instanceof Ellipse))
            return false;
        // Cast Object other to Ellipse and compare everything
        Ellipse that = (Ellipse)other;

        return  (
                this.getX() == that.getX() &&
                this.getY() == that.getY() &&
                this.getWidth() == that.getWidth() &&
                this.getHeight() == that.getHeight() &&
                this.getColor() == that.getColor()
                );
    }
}
