/**
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
/**
 * The QuadNode interface represents a node in a quadtree data structure.
 * It provides methods for adding and removing points, finding duplicates,
 * performing region searches, and getting output data.
 */
public interface QuadNode {

    /**
     * Adds a point to the quadtree.
     *
     * @param point
     *            the point to be added
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @return the updated QuadNode after adding the point
     */
    public abstract QuadNode add(Point point, int currX, int currY, int split);


    /**
     * Removes points from the quadtree based on the given origin and current
     * coordinates.
     *
     * @param originX
     *            the x-coordinate of the origin node
     * @param originY
     *            the y-coordinate of the origin node
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param removedPoints
     *            an array to store the removed points
     * @return the updated QuadNode after removing the points
     */
    public abstract QuadNode remove(
        int originX,
        int originY,
        int currX,
        int currY,
        int split,
        Point[] removedPoints);


    /**
     * Removes a specific point from the quadtree.
     *
     * @param point
     *            the point to be removed
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param removedPoints
     *            an array to store the removed points
     * @return the updated QuadNode after removing the point
     */
    public abstract QuadNode remove(
        Point point,
        int currX,
        int currY,
        int split,
        Point[] removedPoints);


    /**
     * Finds duplicates in the quadtree and returns them as a linked list of
     * strings.
     *
     * @param list
     *            the linked list to store the duplicate strings
     * @return a linked list of duplicate strings
     */
    public abstract LinkedList<String> findDuplicates(LinkedList<String> list);


    /**
     * Performs a region search in the quadtree and returns the points within
     * the specified region.
     *
     * @param x
     *            the x-coordinate of the region
     * @param y
     *            the y-coordinate of the region
     * @param width
     *            the width of the region
     * @param height
     *            the height of the region
     * @param points
     *            the linked list to store the points within the region
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param numOfVisits
     *            an array to store the number of visits to each node
     * @return a linked list of points within the region
     */
    public abstract LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        LinkedList<Point> points,
        int currentX,
        int currentY,
        int split,
        int[] numOfVisits);


    /**
     * Gets the output data of the quadtree as a linked list of strings.
     *
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param list
     *            the linked list to store the output data
     * @param numOfIndents
     *            the number of indents for each level of the quadtree
     * @param numOfVisits
     *            an array to store the number of visits to each node
     * @return a linked list of strings representing the output data
     */
    public abstract LinkedList<String> getOutputData(
        int currentX,
        int currentY,
        int split,
        LinkedList<String> list,
        int numOfIndents,
        int[] numOfVisits);

}
