
public class Point {
    private String name;
    private int x;
    private int y;

    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    /**
     * Getter for the name
     *
     * @return the name of point
     */
    public String getName() {
        return this.name;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return this.x;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return this.y;
    }


    /**
     * Checks, if the invoking point has the same coordinates as p.
     *
     * @param p
     *            the point parameter
     * @return true if the point has the same coordinates as point, false if
     *         not
     */
    public boolean equals(Object p) {
        if (this == p) {
            return true;
        }
        if (p == null || getClass() != p.getClass()) {
            return false;
        }
        Point other = (Point)p;
        return this.x == other.x && this.y == other.y;
    }


    /**
     * Outputs a human readable string with information about the point
     * which includes the x and y coordinate
     *
     * @return a human readable string containing information about the
     *         point
     */
    public String toString() {
        return String.valueOf(this.x) + ", " + String.valueOf(this.y) + ", ";
    }


    /**
     * Checks if the point has invalid parameters
     *
     * @return true if the point has invalid parameters, false if not
     */
    public boolean isInvalid() {
        // Check for negative coordinates or non-positive dimensions
        // TODO: Edit this logic to suit Point class
        if (this.name.isEmpty() || this.x < 0 || this.y < 0) {
            return true;
        }

        // Check if the rectangle extends beyond the right or bottom edge of the
        // 1024x1024 world box
        if (this.x > 1024 || this.y > 1024) {
            return true;
        }

        // The rectangle is valid and within bounds
        return false;
    }

}
