
public class LeafNode implements QuadNode {
    LinkedList<Point> pointsList;
    final static int CAPACITY = 3;

    public LeafNode() {
        pointsList = new LinkedList<>();
    }


    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        // TODO Auto-generated method stub
        // should we check here the coordinates?
        if (!willPutOverCapacity(point)) {
            pointsList.add(point);
            return this;
        }
        // if this insert will put over capacity, we need to split and return
        // the new internal node
        QuadNode splitNode = split();
        Node<Point> currPt = pointsList.getHead();
        // insert existing points in the points list
        while (currPt != null) {
            splitNode.add(currPt.getData(), currX, currY, split);
            currPt = currPt.getNext();
        }
        // insert the new point we wanted to insert
        splitNode.add(point, currX, currY, split);
        return splitNode;
    }


// TODO: If 3 identical, and one different - split. Do not split only if all
// 4 or more
// are strictly identical
    private boolean willPutOverCapacity(Point point) {
        if (pointsList.getNumberOfEntries() < CAPACITY) {
            return false;
        }
        Node currPt = pointsList.getHead();
        boolean unique = true;
        while (currPt != null && unique) {
            if (currPt.getData().equals(point)) {
                unique = false;
            }
            currPt = currPt.getNext();
        }
        return unique;
    }


    private QuadNode split() {
        InternalNode newInternalNode = new InternalNode(EmptyNode.getInstance(),
            EmptyNode.getInstance(), EmptyNode.getInstance(), EmptyNode
                .getInstance());
        return newInternalNode;
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
}
