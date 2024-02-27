
public class InternalNode implements QuadNode {
    private QuadNode NW;
    private QuadNode NE;
    private QuadNode SE;
    private QuadNode SW;
    private QuadNode flyNode;

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
     * @return NE
     *         Getter
     */
    public QuadNode getNE() {
        return this.NE;
    }


    /**
     * @return SW
     *         Getter
     */
    public QuadNode getSW() {
        return this.SW;
    }


    /**
     * @return SE
     *         Getter
     */
    public QuadNode getSE() {
        return this.SE;
    }


// TODO: Need to work on properly inserting
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
        Point point,
        int currX,
        int currY,
        int split,
        LinkedList<Point> removedPoints) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public LinkedList<String> findDuplicates(LinkedList<String> list) {
        if (NW != flyNode) {
            list = NW.findDuplicates(list);
        }
        if (NE != flyNode) {
            list = NE.findDuplicates(list);
        }
        if (SW != flyNode) {
            list = SW.findDuplicates(list);
        }
        if (SE != flyNode) {
            list = SE.findDuplicates(list);
        }
        return list;
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
