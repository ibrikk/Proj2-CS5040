
public class InternalNode implements QuadNode {
    private QuadNode NW;
    private QuadNode NE;
    private QuadNode SE;
    private QuadNode SW;
    private QuadNode flyNode = EmptyNode.getInstance();

    public InternalNode(QuadNode NW, QuadNode NE, QuadNode SE, QuadNode SW) {
        this.NW = NW;
        this.NE = NE;
        this.SE = SE;
        this.SW = SW;
    }


    public InternalNode() {
        this.NW = flyNode;
        this.NE = flyNode;
        this.SE = flyNode;
        this.SW = flyNode;
    }


    /**
     * @return NW
     *         Getter
     */
    public QuadNode getNW() {
        return this.NW;
    }


    /**
     * @param NW
     *            Setter
     */
    public void setNW(QuadNode NW) {
        this.NW = NW;
    }


    /**
     * @return NE
     *         Getter
     */
    public QuadNode getNE() {
        return this.NE;
    }


    /**
     * @param NE
     *            Setter
     */
    public void setNE(QuadNode NE) {
        this.NE = NE;
    }


    /**
     * @return SW
     *         Getter
     */
    public QuadNode getSW() {
        return this.SW;
    }


    /**
     * @param SW
     *            Setter
     */
    public void setSW(QuadNode SW) {
        this.SW = SW;
    }


    /**
     * @return SE
     *         Getter
     */
    public QuadNode getSE() {
        return this.SE;
    }


    /**
     * @param SE
     *            Setter
     */
    public void setSE(QuadNode SE) {
        this.SE = SE;
    }


    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        int newBound = split / 2;
        int newXBound = currX + newBound;
        int newYBound = currY + newBound;
        if (point.getxCoordinate() < newXBound && point
            .getyCoordinate() < newYBound) {

            NW = NW.add(point, currX, currY, newBound);
            return this;
        }
        else if (point.getxCoordinate() >= newXBound && point
            .getyCoordinate() < newYBound) {

            NE = NE.add(point, newXBound, currY, newBound);
            return this;
        }
        else if (point.getxCoordinate() < newXBound && point
            .getyCoordinate() >= newYBound) {

            SW = SW.add(point, currX, newYBound, newBound);
            return this;
        }
        else {

            SE = SE.add(point, newXBound, newYBound, newBound);
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
        list = NW.getOutputData(currentX, currentY, split, list, numOfIndents
            + 1, numOfVisits);
        list = NE.getOutputData(currentX + split, currentY, split, list,
            numOfIndents + 1, numOfVisits);
        list = SW.getOutputData(currentX, currentY + split, split, list,
            numOfIndents + 1, numOfVisits);
        list = SE.getOutputData(currentX + split, currentY + split, split, list,
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
        LinkedList<Point> removedPoint) {
        int half = split / 2;
        if (originX < currX + half && originY < currY + half) {
            NW = NW.remove(originX, originY, currX, currY, half, removedPoint);
        }
        else if (originX >= currX + half && originY < currY + half) {
            NE = NE.remove(originX, originY, currX + half, currY, half,
                removedPoint);
        }
        else if (originX < currX + half && originY >= currY + half) {
            SW = SW.remove(originX, originY, currX, currY + half, half,
                removedPoint);
        }
        else {
            SE = SE.remove(originX, originY, currX + half, currY + half, half,
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
        LinkedList<Point> removedPoint) {
        int half = split / 2;
        int originX = point.getxCoordinate();
        int originY = point.getyCoordinate();

        if (originX < currX + half && originY < currY + half) {
            NW = NW.remove(point, currX, currY, half, removedPoint);
        }
        else if (originX >= currX + half && originY < currY + half) {
            NE = NE.remove(point, currX + half, currY, half, removedPoint);
        }
        else if (originX < currX + half && originY >= currY + half) {
            SW = SW.remove(point, currX, currY + half, half, removedPoint);
        }
        else {
            SE = SE.remove(point, currX + half, currY + half, half,
                removedPoint);
        }
        return merge();
    }


    public QuadNode merge() {
        if (NW instanceof LeafNode && NE == flyNode && SW == flyNode
            && SE == flyNode) {
            return NW;
        }
        else if (NE instanceof LeafNode && NW == flyNode && SW == flyNode
            && SE == flyNode) {
            return NE;
        }
        else if (SW instanceof LeafNode && NW == flyNode && NE == flyNode
            && SE == flyNode) {
            return SW;
        }
        else if (SE instanceof LeafNode && NW == flyNode && NE == flyNode
            && SW == flyNode) {
            return SE;
        }
        else {
            LinkedList<Point> pointsListCopy = new LinkedList<>();

            if (NW instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)NW).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }
            if (NE instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)NE).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }
            if (SW instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)SW).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }
            if (SE instanceof LeafNode) {
                Node<Point> curr = ((LeafNode)SE).getPointsList().getHead();
                while (curr != null) {
                    pointsListCopy.add(curr.getData());
                    curr = curr.getNext();
                }
            }

            if (pointsListCopy.getNumberOfEntries() == LeafNode.CAPACITY) {
                LeafNode newLeaf = new LeafNode();
                newLeaf.setPointsList(pointsListCopy);
                return newLeaf;
            }
            return this;
        }
    }


    @Override
    public LinkedList<String> findDuplicates(LinkedList<String> outputList) {
        if (NW != flyNode) {
            outputList = NW.findDuplicates(outputList);
        }
        if (NE != flyNode) {
            outputList = NE.findDuplicates(outputList);
        }
        if (SW != flyNode) {
            outputList = SW.findDuplicates(outputList);
        }
        if (SE != flyNode) {
            outputList = SE.findDuplicates(outputList);
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

}
