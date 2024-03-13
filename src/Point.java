
/**
 * Point class that stores coordinates
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class Point {
    private String name;
    private int x;
    private int y;

    /**
     * Constructor for Point
     */
    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    /**
     * Getter for the name
     *
     * @return the Point name
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
        return String.valueOf(this.x) + ", " + String.valueOf(this.y);
    }


    /**
     * Checks if the point has invalid parameters
     *
     * @return true if the point has invalid parameters, false if not
     */
    public boolean isInvalid() {
        if (this.x < 0 || this.y < 0 || this.x > 1023 || this.y > 1023) {
            return true;
        }
        return false;
    }

}
