/**
 * The InternalNode class represents an internal node in a QuadTree data
 * structure.
 * It implements the QuadNode interface.
 * 
 * @author Ibrahim Khalilov
 * @version 2024-03-12
 */
public class InternalNode implements QuadNode {

    private QuadNode nW;
    private QuadNode nE;
    private QuadNode sE;
    private QuadNode sW;
    private QuadNode flyNode = EmptyNode.getInstance();

    /**
     * Constructs an InternalNode with the specified child nodes.
     * 
     * @param nW
     *            the north-west child node
     * @param nE
     *            the north-east child node
     * @param sE
     *            the south-east child node
     * @param sW
     *            the south-west child node
     */
    public InternalNode(QuadNode nW, QuadNode nE, QuadNode sE, QuadNode sW) {
        this.nW = nW;
        this.nE = nE;
        this.sE = sE;
        this.sW = sW;
    }


    /**
     * Constructs an InternalNode with all child nodes set to the flyNode.
     */
    public InternalNode() {
        this.nW = flyNode;
        this.nE = flyNode;
        this.sE = flyNode;
        this.sW = flyNode;
    }


    /**
     * Returns the north-west child node.
     * 
     * @return the north-west child node
     */
    public QuadNode getNW() {
        return this.nW;
    }


    /**
     * Sets the north-west child node.
     * 
     * @param northWest
     *            the north-west child node to set
     */
    public void setNW(QuadNode northWest) {
        this.nW = northWest;
    }


    /**
     * Returns the north-east child node.
     * 
     * @return the north-east child node
     */
    public QuadNode getNE() {
        return this.nE;
    }


    /**
     * Sets the north-east child node.
     * 
     * @param northEast
     *            the north-east child node to set
     */
    public void setNE(QuadNode northEast) {
        this.nE = northEast;
    }


    /**
     * Returns the south-west child node.
     * 
     * @return the south-west child node
     */
    public QuadNode getSW() {
        return this.sW;
    }


    /**
     * Sets the south-west child node.
     * 
     * @param southWest
     *            the south-west child node to set
     */
    public void setSW(QuadNode southWest) {
        this.sW = southWest;
    }


    /**
     * Returns the south-east child node.
     * 
     * @return the south-east child node
     */
    public QuadNode getSE() {
        return this.sE;
    }


    /**
     * Sets the south-east child node.
     * 
     * @param southEast
     *            the south-east child node to set
     */
    public void setSE(QuadNode southEast) {
        this.sE = southEast;
    }


    /**
     * Adds a point to the QuadTree.
     * 
     * @param point
     *            the point to add
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the size of the node
     * @return the updated QuadNode
     */
    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        int newBound = split / 2;
        int newXBound = currX + newBound;
        int newYBound = currY + newBound;

        if (point.getxCoordinate() < newXBound && point
            .getyCoordinate() < newYBound) {
            nW = nW.add(point, currX, currY, newBound);
            return this;
        }
        else if (point.getxCoordinate() >= newXBound && point
            .getyCoordinate() < newYBound) {
            nE = nE.add(point, newXBound, currY, newBound);
            return this;
        }
        else if (point.getxCoordinate() < newXBound && point
            .getyCoordinate() >= newYBound) {
            sW = sW.add(point, currX, newYBound, newBound);
            return this;
        }
        else {
            sE = sE.add(point, newXBound, newYBound, newBound);
            return this;
        }
    }


    /**
     * Retrieves the output data of the QuadTree in a LinkedList.
     * 
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param bound
     *            the size of the node
     * @param list
     *            the LinkedList to store the output data
     * @param numOfIndents
     *            the number of indents for formatting
     * @param numOfVisits
     *            the number of nodes visited
     * @return the updated LinkedList with the output data
     */
    public LinkedList<String> getOutputData(
        int currentX,
        int currentY,
        int bound,
        LinkedList<String> list,
        int numOfIndents,
        int[] numOfVisits) {
        int split = bound / 2;
        String temp = "";

        for (int i = 0; i < numOfIndents; i++) {
            temp = temp + "  ";
        }

        temp = temp + "Node at " + ((Integer)currentX).toString() + ", "
            + ((Integer)currentY).toString() + ", " + ((Integer)bound)
                .toString() + ": Internal";
        list.add(temp);

        list = nW.getOutputData(currentX, currentY, split, list, numOfIndents
            + 1, numOfVisits);
        list = nE.getOutputData(currentX + split, currentY, split, list,
            numOfIndents + 1, numOfVisits);
        list = sW.getOutputData(currentX, currentY + split, split, list,
            numOfIndents + 1, numOfVisits);
        list = sE.getOutputData(currentX + split, currentY + split, split,
            list, numOfIndents + 1, numOfVisits);

        numOfVisits[0]++;
        return list;
    }


    /**
     * Removes a point from the QuadTree.
     * 
     * @param originX
     *            the x-coordinate of the point to remove
     * @param originY
     *            the y-coordinate of the point to remove
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the size of the node
     * @param removedPoint
     *            an array to store the removed point
     * @return the updated QuadNode after removal
     */
    @Override
    public QuadNode remove(
        int originX,
        int originY,
        int currX,
        int currY,
        int split,
        Point[] removedPoint) {
        int half = split / 2;

        if (originX < currX + half && originY < currY + half) {
            nW = nW.remove(originX, originY, currX, currY, half, removedPoint);
        }
        else if (originX >= currX + half && originY < currY + half) {
            nE = nE.remove(originX, originY, currX + half, currY, half,
                removedPoint);
        }
        else if (originX < currX + half && originY >= currY + half) {
            sW = sW.remove(originX, originY, currX, currY + half, half,
                removedPoint);
        }
        else {
            sE = sE.remove(originX, originY, currX + half, currY + half, half,
                removedPoint);
        }

        return merge();
    }


    /**
     * Removes a point from the QuadTree.
     * 
     * @param point
     *            the point to remove
     * @param currX
     *            the current x-coordinate of the node
     * @param currY
     *            the current y-coordinate of the node
     * @param split
     *            the size of the node
     * @param removedPoint
     *            an array to store the removed point
     * @return the updated QuadNode after removal
     */
    @Override
    public QuadNode remove(
        Point point,
        int currX,
        int currY,
        int split,
        Point[] removedPoint) {
        int half = split / 2;
        int originX = point.getxCoordinate();
        int originY = point.getyCoordinate();

        if (originX < currX + half && originY < currY + half) {
            nW = nW.remove(point, currX, currY, half, removedPoint);
        }
        else if (originX >= currX + half && originY < currY + half) {
            nE = nE.remove(point, currX + half, currY, half, removedPoint);
        }
        else if (originX < currX + half && originY >= currY + half) {
            sW = sW.remove(point, currX, currY + half, half, removedPoint);
        }
        else {
            sE = sE.remove(point, currX + half, currY + half, half,
                removedPoint);
        }

        return merge();
    }


    /**
     * Merges the child nodes of the InternalNode.
     * 
     * @return the merged QuadNode
     */
    public QuadNode merge() {
        if (nW instanceof LeafNode && nE == flyNode && sW == flyNode
            && sE == flyNode) {
            return nW;
        }
        else if (nE instanceof LeafNode && nW == flyNode && sW == flyNode
            && sE == flyNode) {
            return nE;
        }
        else if (sW instanceof LeafNode && nW == flyNode && nE == flyNode
            && sE == flyNode) {
            return sW;
        }
        else if (sE instanceof LeafNode && nW == flyNode && nE == flyNode
            && sW == flyNode) {
            return sE;
        }
        else if (!(nW instanceof InternalNode) && !(nE instanceof InternalNode)
            && !(sW instanceof InternalNode)
            && !(sE instanceof InternalNode)) {
            LinkedList<Point> pointsListCopy = new LinkedList<>();

            if (nW instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)nW).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }
            if (nE instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)nE).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }
            if (sW instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)sW).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }
            if (sE instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)sE).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }

            if (pointsListCopy.getNumberOfEntries() <= LeafNode.CAPACITY
                || checkIfAllIdentical(pointsListCopy)) {
                LeafNode newLeaf = new LeafNode();
                newLeaf.setPointsList(pointsListCopy);
                return newLeaf;
            }
            return this;
        }

        return this;
    }


    /**
     * Checks if all points in the LinkedList are identical.
     * 
     * @param pointsListCopy
     *            the LinkedList of points to check
     * @return true if all points are identical, false otherwise
     */
    private boolean checkIfAllIdentical(LinkedList<Point> pointsListCopy) {
        for (int i = 0; i < pointsListCopy.getNumberOfEntries() - 1; i++) {
            if (pointsListCopy.get(i) != pointsListCopy.get(i + 1)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Finds duplicate points in the QuadTree and adds them to the outputList.
     * 
     * @param outputList
     *            the LinkedList to store the duplicate points
     * @return the updated LinkedList with the duplicate points
     */
    @Override
    public LinkedList<String> findDuplicates(LinkedList<String> outputList) {
        if (nW != flyNode) {
            outputList = nW.findDuplicates(outputList);
        }
        if (nE != flyNode) {
            outputList = nE.findDuplicates(outputList);
        }
        if (sW != flyNode) {
            outputList = sW.findDuplicates(outputList);
        }
        if (sE != flyNode) {
            outputList = sE.findDuplicates(outputList);
        }
        return outputList;
    }


    /**
     * Searches for points within a specified region in the QuadTree.
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
     *            the LinkedList to store the points within the region
     * @param currentX
     *            the current x-coordinate of the node
     * @param currentY
     *            the current y-coordinate of the node
     * @param split
     *            the size of the node
     * @param numOfVisits
     *            the number of nodes visited
     * @return the updated LinkedList with the points within the region
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
        int half = split / 2;
        int xBound = currentX + half;
        int yBound = currentY + half;
        int yMax = y + height - 1;
        int xMax = x + width - 1;
        LinkedList<Point> foundList = points;

        if (xBound > x && yBound > y) {
            foundList = nW.regionSearch(x, y, width, height, foundList,
                currentX, currentY, half, numOfVisits);
        }

        if (xBound <= xMax && yBound > y) {
            foundList = nE.regionSearch(x, y, width, height, foundList, xBound,
                currentY, half, numOfVisits);
        }

        if (xBound > x && yBound <= yMax) {
            foundList = sW.regionSearch(x, y, width, height, foundList,
                currentX, yBound, half, numOfVisits);
        }

        if (xMax >= xBound && yMax >= yBound) {
            foundList = sE.regionSearch(x, y, width, height, foundList, xBound,
                yBound, half, numOfVisits);
        }

        return foundList;
    }
}
