public abstract class Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    private String color;

    // Constructers
    /**
     * creates a new Shape object
     * @param x x value for the new object
     * @param y y value for the new object
     * @param width width for the new object
     * @param height height for the new object
     * @param color color of the new object
     */
    public Shape(int x, int y, int width, int height, String color) {
        this.x = x > 0 ? x : -x;
        this.y = y > 0 ? y : -y;
        this.width = width > 0 ? width : -width;
        this.height = height > 0 ? height : -height;
        if (color.equals("blue") || color.equals("green")
                || color.equals("yellow") || color.equals("red")
                || color.equals("white") || color.equals("gray"))
            this.color = color;
        else
            this.color = "blue";
    }

    /**
     * returns x value of the shape
     * @return the x value of the shape
     */
    public int getX() {
        return x;
    }

    /**
     * returns y value of the shape
     * @return the y value of the shape
     */
    public int getY() {
        return y;
    }

    /**
     * returns width of the shape
     * @return the width of the shape
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns height of the shape
     * @return the height of the shape
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns color of the shape
     * @return the color of the shape
     */
    public String getColor() {
        return color;
    }

    /**
     * sets x value to a new value
     * @param x the new x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * sets y value to a new value
     * @param y the new y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * sets width to a new value
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * sets height to a new value
     * @param height the new width
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * sets color to a new color
     * @param color the new color
     */
    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        String ret = "";
        ret += " x = " + getX();
        ret += " y = " + getY();
        ret += " width = " + getWidth();
        ret += " height = " + getHeight();
        return ret;
    }

    public abstract boolean equals(Object other);
    public abstract Point[] getVertices();

    public boolean isInsideBox(int maxX, int maxY) {
        // Since x, y, width, height are all positive
        return maxX >= x + width && maxY >= y + height;
    }
}
