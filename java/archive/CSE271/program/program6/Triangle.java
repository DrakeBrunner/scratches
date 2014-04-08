public class Triangle extends Shape {
    /**
     * creates a new Triangle object
     * @param x x value for the new object
     * @param y y value for the new object
     * @param height height for the new object
     * @param width width for the new object
     * @param color color of the new object
     */
    public Triangle(int xPos, int yPos, int newWidth, int newHeight, String newColor) {
        super(xPos, yPos, newWidth, newHeight, newColor);
    }

    /**
     * copies the given Triangle object
     * @param other original Triangle object
     */
    public Triangle(Triangle other) {
        super(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.getColor());
    }

    /**
     * returns 3 points of the triangle
     * @return an array containing the 3 verticies of this triangle
     */
    @Override
    public Point[] getVertices() {
        Point[] ret = {
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
        String ret =  "Triangle:";
        ret += super.toString();
        return ret;
    }

    /**
     * check to see if other has the exact same value as this object
     * @param other a Triangle object that will be compared
     * @return true if "other" has the same value as this object. false if not
     */
    @Override
    public boolean equals(Object other) {
        // Return false if other is null
        if (other == null)
            return false;

        // If other is not an instance of Triangle,
        // it's not worth going further
        if (!(other instanceof Triangle))
            return false;
        // Cast Object other to Triangle and compare everything
        Triangle that = (Triangle)other;

        return  (
                this.getX() == that.getX() &&
                this.getY() == that.getY() &&
                this.getWidth() == that.getWidth() &&
                this.getHeight() == that.getHeight() &&
                this.getColor() == that.getColor()
                );
    }
}
