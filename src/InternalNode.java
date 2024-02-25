
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
        // TODO Auto-generated method stub
        return null;
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


//    public void print(int currX, int currY, int split) {
//        String nw = String.format("NW x, y, dimension: %d, %d, %d\n", currX,
//            currY, (split / 2));
//        String sw = String.format("SW x, y, dimension: %d, %d, %d\n", currX,
//            currY + (split / 2), (split / 2));
//        String ne = String.format("NE x, y, dimension: %d, %d, %d\n", currX
//            + (split / 2), currY, (split / 2));
//        String se = String.format("SE x, y, dimension: %d, %d, %d\n", currX
//            + (split / 2), currY + (split / 2), (split / 2));
//
//        System.out.println("Internal Node with (X,Y) as: " + currX + ", "
//            + currY + "and l/w of: " + split
//            + "\nthe coordinates of the children nodes are: \n" + nw + ne + sw
//            + se);
//        NW.print(currX, currY, (split / 2));
//        NE.print(currX + (split / 2), currY, (split / 2));
//        SW.print(currX, currY + (split / 2), (split / 2));
//        SE.print(currX + (split / 2), currY + (split / 2), (split / 2));
//    }
}
