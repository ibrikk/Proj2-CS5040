/**
 * LeafNode represents a leaf node in a QuadTree data structure.
 * It implements the QuadNode interface.
 * 
 * 
 * @author Ibrahim Khalilov{ibrahimk},Francisca Wood{franciscawood}**
 * @version 2024-03-12
 **/
public class LeafNode implements QuadNode {
    private LinkedList<Point> pointsList;
    /**
     * 3 is the Max capacity of the list
     */
    final static int CAPACITY = 3;
    private QuadNode flyNode = EmptyNode.getInstance();

    /**
     * Constructor for LeafNode
     */
    public LeafNode() {
        pointsList = new LinkedList<>();
    }


    /**
     * 
     * @return pointsList
     *         Getter for the points list
     */
    public LinkedList<Point> getPointsList() {
        return pointsList;
    }


    /**
     * 
     * @param pointsList
     *            Setter for the points list
     */
    public void setPointsList(LinkedList<Point> pointsList) {
        this.pointsList = pointsList;
    }


    /**
     * 
     * 
     * If 3 identical, and one different - split. Do not split only if all
     * 4 or more are strictly identical
     *
     */
    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        if (checkIfDuplicate(point)) {
            return null;
        }
        if ((pointsList.getNumberOfEntries() < CAPACITY
            || checkIfAllCoordsIdentical(point))) {
            pointsList.add(point);
            return this;
        }
        // if this insert will put over capacity, we need to split and return
        // the new internal node
        QuadNode internalNode = createInternalNode();
        Node<Point> currPt = pointsList.getHead();
        // insert existing points in the points list
        while (currPt != null) {
            internalNode.add(currPt.getData(), currX, currY, split);
            currPt = currPt.getNext();
        }
        // insert the new point we wanted to insert
        internalNode.add(point, currX, currY, split);
        return internalNode;
    }


    /**
     * @param point
     * @return duplicateExists
     * 
     *         Checks if identical point already exists in the pointsList.
     *         It is permissible for two or more points to have the same name,
     *         and it is permissible for two or more points to have the same
     *         spatial position, but not both
     */
    private boolean checkIfDuplicate(Point point) {
        Node<Point> currPt = pointsList.getHead();
        boolean duplicateExists = false;
        while (currPt != null && !duplicateExists) {
            if (currPt.getData().getName().equals(point.getName()) && currPt
                .getData().getxCoordinate() == point.getxCoordinate() && currPt
                    .getData().getyCoordinate() == point.getyCoordinate()) {
                duplicateExists = true;
            }
            currPt = currPt.getNext();
        }
        if (duplicateExists) {
            System.out.println(
                "Failed to insert: This point already exists in the list");
        }
        return duplicateExists;
    }


    /**
     * @param point
     * @return exceededCapacity
     * 
     *         Check if all coordinates are identical in the points list
     */
    private boolean checkIfAllCoordsIdentical(Point point) {
        Node<Point> currPt = pointsList.getHead();
        while (currPt != null) {
            if (currPt.getData().getxCoordinate() != point.getxCoordinate()
                || currPt.getData().getyCoordinate() != point
                    .getyCoordinate()) {
                return false;
            }
            currPt = currPt.getNext();
        }
        return true;
    }


    /**
     * Create an Internal Node
     */
    private QuadNode createInternalNode() {
        InternalNode newInternalNode = new InternalNode(EmptyNode
            .getInstance(), EmptyNode.getInstance(), EmptyNode.getInstance(),
            EmptyNode.getInstance());
        return newInternalNode;
    }


    /**
     * Removes a point from the leaf node based on the origin coordinates.
     * If the point is found and removed, it is stored in the removedPoint
     * array.
     * If the pointsList becomes empty after removal, the flyNode is returned.
     * Otherwise, this leaf node is returned.
     *
     * @param originX
     *            the x-coordinate of the origin
     * @param originY
     *            the y-coordinate of the origin
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param removedPoint
     *            the array to store the removed point
     * @return the flyNode if the pointsList becomes empty, otherwise returns
     *         this leaf node
     */
    @Override
    public QuadNode remove(
        int originX,
        int originY,
        int currX,
        int currY,
        int split,
        Point[] removedPoint) {

        for (int i = pointsList.getNumberOfEntries() - 1; i >= 0; i--) {
            if (originX == pointsList.get(i).getxCoordinate()
                && originY == pointsList.get(i).getyCoordinate()) {
                removedPoint[0] = pointsList.get(i);
                pointsList.remove(i);
                break;

            }
        }

        if (pointsList.getNumberOfEntries() == 0) {
            return this.flyNode;
        }
        else {
            return this;
        }

    }


    /**
     * Removes a point from the leaf node based on the point object.
     * If the point is found and removed, it is stored in the removedPoint
     * array.
     * If the pointsList becomes empty after removal, the flyNode is returned.
     * Otherwise, this leaf node is returned.
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
     *            the array to store the removed point
     * @return the flyNode if the pointsList becomes empty, otherwise returns
     *         this leaf node
     */
    @Override
    public QuadNode remove(
        Point point,
        int currX,
        int currY,
        int split,
        Point[] removedPoint) {

        for (int i = 0; i < pointsList.getNumberOfEntries(); i++) {
            if (point == pointsList.get(i)) {

                removedPoint[0] = pointsList.get(i);
                pointsList.remove(i);
                break;

            }
        }

        if (pointsList.getNumberOfEntries() == 0)

        {
            return this.flyNode;
        }
        else {
            return this;
        }
    }


    /**
     * Finds duplicate points in the leaf node and adds them to the outputList.
     * A point is considered a duplicate if it has the same spatial position (x
     * and y coordinates)
     * as another point in the pointsList.
     *
     * @param outputList
     *            the list to store the duplicate points
     * @return the updated outputList with duplicate points
     */
    @Override
    public LinkedList<String> findDuplicates(LinkedList<String> outputList) {
        Node<Point> curr = this.pointsList.getHead();
        while (curr != null) {
            Point currentPoint = curr.getData();
            Node<Point> runner = curr.getNext();
            while (runner != null) {
                Point nextPoint = runner.getData();
                if (currentPoint.getxCoordinate() == nextPoint.getxCoordinate()
                    && currentPoint.getyCoordinate() == nextPoint
                        .getyCoordinate()) {
                    String duplicatePoint = "(" + currentPoint.getxCoordinate()
                        + ", " + currentPoint.getyCoordinate() + ")";
                    if (!outputList.contains(duplicatePoint)) {
                        outputList.add(duplicatePoint);
                    }
                }
                runner = runner.getNext();
            }
            curr = curr.getNext();
        }
        return outputList;
    }


    /**
     * Searches for points within a specified region in the leaf node.
     * Adds the points that fall within the region to the points list.
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
     *            the array to store the number of visits
     * @return the updated points list with points within the region
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
        for (int i = 0; i < pointsList.getNumberOfEntries(); i++) {
            int tempXCoordinate = pointsList.get(i).getxCoordinate();
            int tempYCoordinate = pointsList.get(i).getyCoordinate();
            if (tempXCoordinate >= x && tempXCoordinate < x + width
                && tempYCoordinate >= y && tempYCoordinate < y + height) {
                points.add(pointsList.get(i));
            }
        }
        return points;
    }


    /**
     * Returns a string representation of the leaf node.
     *
     * @return a string representation of the leaf node
     */
    public String toString() {
        String output = "\nLeaf node with the following points:\n";
        Node<Point> currPt = pointsList.getHead();
        // insert existing points in the points list
        while (currPt != null) {
            output += currPt.getData() + "\n";
            currPt = currPt.getNext();
        }
        return output;
    }


    /**
     * Prints the leaf node information to the console.
     *
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     */
    public void print(int currX, int currY, int split) {
        String output = "Leaf Node with (X,Y) as: " + currX + ", " + currY
            + "and l/w of: " + split + toString();
        System.out.println(output);
    }


    /**
     * Returns the output data of the leaf node.
     * Adds the node information and points to the result list.
     *
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param split
     *            the split value of the node
     * @param result
     *            the list to store the output data
     * @param numOfIndents
     *            the number of indents for formatting
     * @param numOfVisits
     *            the array to store the number of visits
     * @return the updated result list with the output data
     */
    public LinkedList<String> getOutputData(
        int currentX,
        int currentY,
        int split,
        LinkedList<String> result,
        int numOfIndents,
        int[] numOfVisits) {
        String indents = "";
        String temp = "";
        for (int i = 0; i < numOfIndents; i++) {
            indents = indents + "  ";
        }
        temp = temp + indents;
        temp = temp + "Node at " + ((Integer)currentX).toString() + ", "
            + ((Integer)currentY).toString() + ", " + ((Integer)split)
                .toString() + ":";
        result.add(temp);
        temp = "";

        Node<Point> current = pointsList.getHead();
        while (current != null) {
            temp = indents + "(" + current.getData().getName() + ", " + current
                .getData().toString() + ")";
            result.add(temp);
            current = current.getNext();
        }

        numOfVisits[0]++;
        return result;
    }
}
