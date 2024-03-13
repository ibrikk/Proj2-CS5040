/**
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class InternalNode implements QuadNode {
    private QuadNode nW;
    private QuadNode nE;
    private QuadNode sE;
    private QuadNode sW;
    private QuadNode flyNode = EmptyNode.getInstance();

    public InternalNode(QuadNode nW, QuadNode nE, QuadNode sE, QuadNode sW) {
        this.nW = nW;
        this.nE = nE;
        this.sE = sE;
        this.sW = sW;
    }


    public InternalNode() {
        this.nW = flyNode;
        this.nE = flyNode;
        this.sE = flyNode;
        this.sW = flyNode;
    }


    /**
     * @return nW
     *         Getter
     */
    public QuadNode getNW() {
        return this.nW;
    }


    /**
     * @param nW
     *            Setter
     */
    public void setNW(QuadNode nW) {
        this.nW = nW;
    }


    /**
     * @return NE
     *         Getter
     */
    public QuadNode getNE() {
        return this.nE;
    }


    /**
     * @param NE
     *            Setter
     */
    public void setNE(QuadNode nE) {
        this.nE = nE;
    }


    /**
     * @return sW
     *         Getter
     */
    public QuadNode getSW() {
        return this.sW;
    }


    /**
     * @param SW
     *            Setter
     */
    public void setSW(QuadNode sW) {
        this.sW = sW;
    }


    /**
     * @return SE
     *         Getter
     */
    public QuadNode getSE() {
        return this.sE;
    }


    /**
     * @param SE
     *            Setter
     */
    public void setSE(QuadNode sE) {
        this.sE = sE;
    }


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
        list = sE.getOutputData(currentX + split, currentY + split, split, list,
            numOfIndents + 1, numOfVisits);
        numOfVisits[0]++;
        return list;
    }


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
            && !(sW instanceof InternalNode) && !(sE instanceof InternalNode)) {
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


    private boolean checkIfAllIdentical(LinkedList<Point> pointsListCopy) {
        for (int i = 0; i < pointsListCopy.getNumberOfEntries() - 1; i++) {
            if (pointsListCopy.get(i) != pointsListCopy.get(i + 1)) {
                return false;
            }
        }
        return true;
    }


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
