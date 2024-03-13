/**
 * Implements a QuadTree data structure to manage spatial data efficiently. The
 * QuadTree organizes
 * points in a two-dimensional space, enabling fast querying for operations like
 * insertion, removal,
 * and region search. This structure is particularly effective for applications
 * that require
 * spatial querying and manipulation of large datasets.
 * 
 * The QuadTree is composed of nodes that divide the space into four quadrants
 * recursively. Each
 * node can either be an internal node, which partitions the space further, or a
 * leaf node, which
 * contains actual data points. This implementation uses a flyweight pattern for
 * empty leaf nodes
 * to save memory.
 * 
 * The class provides methods for inserting points, removing points, searching
 * for points within
 * a specified region, finding duplicates, and dumping the tree's structure for
 * debugging purposes.
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-03-12
 */
public class QuadTree {
    private QuadNode root;

    private QuadNode flyWeightNode;

    private int numOfNodes;

    /**
     * The maximum coordinate value that the QuadTree can accommodate, defining
     * the spatial boundary.
     */
    final static int WORLDVIEW = 1024;

    /**
     * Starting X coordinate of the QuadTree's boundary.
     */
    final static int XSTART = 0;

    /**
     * Starting Y coordinate of the QuadTree's boundary.
     */
    final static int YSTART = 0;

    /**
     * Constructs an empty QuadTree with a flyweight node as its root.
     * Initializes the node counter to zero.
     */
    public QuadTree() {
        flyWeightNode = new EmptyNode();
        root = flyWeightNode;
        numOfNodes = 0;
    }


    /**
     * Retrieves the total number of nodes in the QuadTree.
     * 
     * @return the number of nodes in the QuadTree.
     */
    public int getNumOfNodes() {
        return numOfNodes;
    }


    /**
     * Provides access to the root node of the QuadTree.
     * 
     * @return the root node of the QuadTree.
     */
    public QuadNode getRoot() {
        return root;
    }


    /**
     * Inserts a point into the QuadTree if it lies within the defined WORLDVIEW
     * boundary. This
     * method increments the node counter upon a successful insertion.
     * 
     * @param p
     *            the Point to be inserted.
     */
    public void insert(Point p) {
        if (checkIfInsideWorldView(p)) {
            root = root.add(p, XSTART, YSTART, WORLDVIEW);
            numOfNodes++;
        }
        else {
            System.out.println("Point falls outside of WORLDVIEW");
        }
    }


    /**
     * Identifies and prints duplicates within the QuadTree. Duplicates are
     * determined based on
     * specific criteria defined in the implementation.
     */
    public void duplicates() {
        LinkedList<String> outputList = new LinkedList<String>();
        root.findDuplicates(outputList);
        Node<String> curr = outputList.getHead();
        System.out.println("Duplicate points:");
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
    }


    /**
     * Checks if a given point lies within the WORLDVIEW boundary of the
     * QuadTree.
     * 
     * @param p
     *            the Point to check.
     * @return true if the point lies within the WORLDVIEW boundary; false
     *         otherwise.
     */
    private boolean checkIfInsideWorldView(Point p) {
        return p.getxCoordinate() >= XSTART && p.getxCoordinate() < XSTART
            + WORLDVIEW && p.getyCoordinate() >= YSTART && p
                .getyCoordinate() < YSTART + WORLDVIEW;
    }


    /**
     * Prints the structure of the QuadTree for debugging purposes, including
     * the positions
     * and types of nodes within the tree.
     */
    public void dump() {
        System.out.println("QuadTree dump:");
        int[] numOfVisits = { 0 };
        LinkedList<String> outputList = new LinkedList<String>();
        if (this.numOfNodes == 0) {
            String temp = "Node at 0, 0, 1024: Empty";
            outputList.add(temp);
            temp = "1 quadtree nodes printed";
            outputList.add(temp);
        }
        else {
            root.getOutputData(XSTART, YSTART, WORLDVIEW, outputList, 0,
                numOfVisits);
            outputList.add(numOfVisits[0] + " quadtree nodes printed");
        }
        Node<String> curr = outputList.getHead();
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
    }


    /**
     * Removes a point from the QuadTree based on its coordinates. If the point
     * is not found,
     * a message indicating such is printed.
     * 
     * @param x
     *            the X coordinate of the point to remove.
     * @param y
     *            the Y coordinate of the point to remove.
     * @return the removed Point, or null if the point was not found.
     */
    public Point remove(int x, int y) {
        Point[] removedPointList = { null };
        root = root.remove(x, y, XSTART, YSTART, WORLDVIEW, removedPointList);
        if (removedPointList[0] == null) {
            System.out.println("Point not found: (" + x + ", " + y + ")");
            return null;
        }
        numOfNodes--;
        return removedPointList[0];
    }


    /**
     * Removes a specific point from the QuadTree. If the removal leads to a
     * change in the
     * tree's structure, the root is updated accordingly.
     * 
     * @param point
     *            the Point to be removed.
     * @return the removed Point, or null if the point was not found.
     */
    public Point remove(Point point) {
        Point[] removedPointList = { null };
        QuadNode updatedNode = root.remove(point, XSTART, YSTART, WORLDVIEW,
            removedPointList);
        if (updatedNode != null && updatedNode != root) {
            root = updatedNode;
            numOfNodes--;
        }

        return removedPointList[0];
    }


    /**
     * Performs a region search in the QuadTree, returning all points that lie
     * within a specified
     * rectangular region. This method also updates the number of nodes visited
     * during the search.
     * 
     * @param x
     *            the X coordinate of the region's top-left corner.
     * @param y
     *            the Y coordinate of the region's top-left corner.
     * @param width
     *            the width of the region.
     * @param height
     *            the height of the region.
     * @param numOfVisits
     *            an array to count the number of nodes visited.
     * @return a LinkedList containing all points found within the specified
     *         region.
     */
    public LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        int[] numOfVisits) {
        LinkedList<Point> points = new LinkedList<>();
        points = root.regionSearch(x, y, width, height, points, XSTART, YSTART,
            WORLDVIEW, numOfVisits);
        return points;
    }
}
