public class Rectangle extends Shape {
    /**
     * creates a new Rectangle object
     * @param x x value for the new object
     * @param y y value for the new object
     * @param height height for the new object
     * @param width width for the new object
     * @param color color of the new object
     */
    public Rectangle(int xPos, int yPos, int newWidth, int newHeight, String newColor) {
        super(xPos, yPos, newWidth, newHeight, newColor);
    }

    /**
     * copies the given Rectangle object
     * @param other original Rectangle object
     */
    public Rectangle(Rectangle other) {
        super(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.getColor());
    }

    /**
     * returns 4 points of the rectangle
     * @return an array containing the 4 verticies of this rectangle
     */
    @Override
    public Point[] getVertices() {
        Point[] ret = {
            new Point(getX(), getY()),
            new Point(getX() + getWidth(), getY()),
            new Point(getX() + getWidth(), getY() + getHeight()),
            new Point(getX(), getY() + getHeight()),
        };
        return ret;
    }

    /**
     * returns a String representation of the variables in this object
     * @return a String representation of variables
     */
    @Override
    public String toString() {
        String ret =  "Rectangle:";
        ret += super.toString();
        return ret;
    }

    /**
     * check to see if other has the exact same value as this object
     * @param other a Rectangle object that will be compared
     * @return true if "other" has the same value as this object. false if not
     */
    @Override
    public boolean equals(Object other) {
        // Return false if other is null
        if (other == null)
            return false;

        // If other is not an instance of Rectangle,
        // it's not worth going further
        if (!(other instanceof Rectangle))
            return false;
        // Cast Object other to Rectangle and compare everything
        Rectangle that = (Rectangle)other;

        return  (
                this.getX() == that.getX() &&
                this.getY() == that.getY() &&
                this.getWidth() == that.getWidth() &&
                this.getHeight() == that.getHeight() &&
                this.getColor() == that.getColor()
                );
    }
}
