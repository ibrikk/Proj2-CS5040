
public class QuadTree {
    private QuadNode root;
    private QuadNode flyWeightNode;
    private int numOfNodes;
    final static int WORLDVIEW = 1024;
    final static int xStart = 0;
    final static int yStart = 0;

    public QuadTree() {
        flyWeightNode = new EmptyNode();
        root = flyWeightNode;
        numOfNodes = 0;
    }


    public int getNumOfNodes() {
        return numOfNodes;
    }


    public QuadNode getRoot() {
        return root;
    }


    public void insert(Point p) {
        if (checkIfInsideWorldView(p)) {
            root = root.add(p, xStart, yStart, WORLDVIEW);
            numOfNodes++;
        }
        else {
            System.out.println("Point falls outside of WORLDVIEW");
        }
    }


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


    private boolean checkIfInsideWorldView(Point p) {
        return p.getxCoordinate() >= xStart && p.getxCoordinate() < xStart
            + WORLDVIEW && p.getyCoordinate() >= yStart && p
                .getyCoordinate() < yStart + WORLDVIEW;
    }


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
            root.getOutputData(xStart, yStart, WORLDVIEW, outputList, 0,
                numOfVisits);
            outputList.add(numOfVisits[0] + " quadtree nodes printed");
        }
        Node<String> curr = outputList.getHead();
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
    }


    public Point remove(int x, int y) {
        Point[] removedPointList = { null };
        root = root.remove(x, y, xStart, yStart, WORLDVIEW, removedPointList);
        if (removedPointList[0] == null) {
            System.out.println("Point not found: (" + x + ", " + y + ")");
            return null;
        }
        numOfNodes--;
        return removedPointList[0];
    }


    public Point remove(Point point) {
        Point[] removedPointList = { null };
        QuadNode updatedNode = root.remove(point, xStart, yStart, WORLDVIEW,
            removedPointList);
        if (updatedNode != null && updatedNode != root) {
            root = updatedNode;
            numOfNodes--;
        }

        return removedPointList[0];
    }


    public LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        int[] numOfVisits) {
        LinkedList<Point> points = new LinkedList<>();
        points = root.regionSearch(x, y, width, height, points, xStart, yStart,
            WORLDVIEW, numOfVisits);
        return points;
    }
}

// We are merging if
// 1) E, L with 2 nodes, L with 1 node, E
// 2) Leaf with 3 nodes, E, E, E - we merge to the parent

// If 3 identical, and one different - split. Do not split only if all 4 or more
// are strictly identical
