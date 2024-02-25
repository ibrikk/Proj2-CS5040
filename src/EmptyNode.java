public class EmptyNode implements QuadNode {
    private static final EmptyNode instance = new EmptyNode();

    public EmptyNode() {
    }


    public static EmptyNode getInstance() {
        return instance;
    }


    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        LeafNode tempNode = new LeafNode();
        tempNode.add(point, currX, currY, split);
        return tempNode;
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


    public void print(int currX, int currY, int split) {
        System.out.println("empty node.");
    }


    public LinkedList<String> getContents(
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
