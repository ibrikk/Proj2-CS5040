
public class LeafNode implements QuadNode {
    private LinkedList<Point> pointsList;
    final static int CAPACITY = 3;
    private QuadNode flyNode;

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
        Node<Point> currPt = pointsList.reverse().getHead();
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
        boolean allAreIdentical = true;
        while (currPt != null && allAreIdentical) {
            if (currPt.getData().getxCoordinate() != point.getxCoordinate()
                || currPt.getData().getyCoordinate() != point
                    .getyCoordinate()) {
                allAreIdentical = false;
            }
            currPt = currPt.getNext();
        }
        return allAreIdentical;
    }


    private QuadNode createInternalNode() {
        InternalNode newInternalNode = new InternalNode(EmptyNode.getInstance(),
            EmptyNode.getInstance(), EmptyNode.getInstance(), EmptyNode
                .getInstance());
        return newInternalNode;
    }


    @Override
    public QuadNode remove(
        int originX,
        int originY,
        int currX,
        int currY,
        int split,
        LinkedList<Point> removedPoint) {
        pointsList = pointsList.reverse();
        Node<Point> curr = pointsList.getHead();
        while (curr != null) {
            if (curr.getData().getxCoordinate() == originX && curr.getData()
                .getyCoordinate() == originY) {
                removedPoint.setHead(curr);
                pointsList.remove();
                break;
            }
            else {
                curr = curr.getNext();
            }
        }
        if (pointsList.getNumberOfEntries() == 0) {
            return this.flyNode;
        }
        else {
            return this;
        }
    }


    @Override
    public LinkedList<String> findDuplicates(LinkedList<String> outputList) {
        Node<Point> curr = this.pointsList.reverse().getHead();
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


    @Override
    public LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        LinkedList<Point> result,
        int currentX,
        int currentY,
        int split,
        LinkedList<Integer> numOfVisits) {
        // TODO Auto-generated method stub
        return null;
    }


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


    public void print(int currX, int currY, int split) {
        String output = "Leaf Node with (X,Y) as: " + currX + ", " + currY
            + "and l/w of: " + split + toString();
        System.out.println(output);
    }


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

        Node<Point> current = pointsList.reverse().getHead();
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
