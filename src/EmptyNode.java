/**
 * This class is the empty node that half of the tree references to
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 * 
 */

/**
 * Represents an empty node in a Quad Tree.
 * An empty node does not contain any points and is used to represent an empty
 * region in the Quad Tree.
 */
/**
 * Represents an empty node in a Quad Tree.
 */
public class EmptyNode implements QuadNode {

    private static final EmptyNode INSTANCE = new EmptyNode();

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    public EmptyNode() {
    }


    /**
     * Returns the singleton instance of EmptyNode.
     *
     * @return the singleton instance of EmptyNode
     */
    public static EmptyNode getInstance() {
        return INSTANCE;
    }


    /**
     * Adds a point to the Quad Tree.
     *
     * @param point
     *            the point to be added
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @return the updated Quad Tree node
     */
    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        LeafNode tempNode = new LeafNode();
        tempNode.add(point, currX, currY, split);
        return tempNode;
    }


    /**
     * Removes a point from the Quad Tree.
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
     * @param removedPoint
     *            an array to store the removed point
     * @return the updated Quad Tree node
     */
    @Override
    public QuadNode remove(
        int originX,
        int originY,
        int currX,
        int currY,
        int split,
        Point[] removedPoint) {
        return this;
    }


    /**
     * Removes a point from the Quad Tree.
     *
     * @param point
     *            the point to be removed
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param removedPoint
     *            an array to store the removed point
     * @return the updated Quad Tree node
     */
    @Override
    public QuadNode remove(
        Point point,
        int currX,
        int currY,
        int split,
        Point[] removedPoint) {
        return this;
    }


    /**
     * Finds duplicates in the Quad Tree.
     *
     * @param outputList
     *            the list to store the duplicate strings
     * @return the list of duplicate strings
     */
    @Override
    public LinkedList<String> findDuplicates(LinkedList<String> outputList) {
        return outputList;
    }


    /**
     * Performs a region search in the Quad Tree.
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
     *            the list to store the points within the region
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param numOfVisits
     *            an array to store the number of visits
     * @return the list of points within the region
     */
    @Override
    public LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        LinkedList<Point> points,
        int currentX,
        int currentY,
        int split,
        int[] numOfVisits) {
        numOfVisits[0]++;
        return points;
    }


    /**
     * Gets the output data of the Quad Tree.
     *
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param list
     *            the list to store the output data
     * @param numOfIndents
     *            the number of indents for formatting
     * @param numOfVisits
     *            an array to store the number of visits
     * @return the list of output data
     */
    public LinkedList<String> getOutputData(
        int currentX,
        int currentY,
        int split,
        LinkedList<String> list,
        int numOfIndents,
        int[] numOfVisits) {
        String temp = "";
        for (int i = 0; i < numOfIndents; i++) {
            temp = temp + "  ";
        }
        temp = temp + "Node at " + ((Integer)currentX).toString() + ", "
            + ((Integer)currentY).toString() + ", " + ((Integer)split)
                .toString() + ": Empty";
        list.add(temp);
        numOfVisits[0]++;
        return list;
    }
}
