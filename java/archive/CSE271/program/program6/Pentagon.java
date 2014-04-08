public class Pentagon extends Shape {
    /**
     * creates a new Pentagon object
     * @param x x value for the new object
     * @param y y value for the new object
     * @param width width for the new object
     * @param height height for the new object
     * @param color color of the new object
     */
    public Pentagon(int xPos, int yPos, int newWidth, int newHeight, String newColor) {
        super(xPos, yPos, newWidth, newHeight, newColor);
    }

    /**
     * copies the given Pentagon object
     * @param other original Pentagon object
     */
    public Pentagon(Pentagon other) {
        super(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.getColor());
    }

    /**
     * returns 5 points of the pentagon
     * @return an array containing the 5 verticies of this pentagon
     */
    @Override
    public Point[] getVertices() {
        Point[] ret = {
            new Point(getX() + (int)(getWidth() / 2), getY()),
            new Point(getX() + getWidth(), getY() + (int)(getHeight() / 3)),
            new Point(getX() + (int)(getWidth() * 3 / 4), getY() + getHeight()),
            new Point(getX() + (int)(getWidth() / 4), getY() + getHeight()),
            new Point(getX(), getY() + (int)(getHeight() / 3)),
        };
        return ret;
    }

    /**
     * returns a String representation of the variables in this object
     * @return a String representation of variables
     */
    @Override
    public String toString() {
        String ret =  "Pentagon:";
        ret += super.toString();
        return ret;
    }

    /**
     * check to see if other has the exact same value as this object
     * @param other a Pentagon object that will be compared
     * @return true if "other" has the same value as this object. false if not
     */
    @Override
    public boolean equals(Object other) {
        // Return false if other is null
        if (other == null)
            return false;

        // If other is not an instance of Pentagon,
        // it's not worth going further
        if (!(other instanceof Pentagon))
            return false;
        // Cast Object other to Pentagon and compare everything
        Pentagon that = (Pentagon)other;

        return  (
                this.getX() == that.getX() &&
                this.getY() == that.getY() &&
                this.getWidth() == that.getWidth() &&
                this.getHeight() == that.getHeight() &&
                this.getColor() == that.getColor()
                );
    }
}
