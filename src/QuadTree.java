
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


    public void insert(Point p) {
        if (checkIfInsideWorldView(p)) {
            root = root.add(p, 0, 0, WORLDVIEW);
            numOfNodes++;
        }
        else {
            System.out.println("Point falls outside of WORLDVIEW");
        }
    }


    public void duplicates() {
        LinkedList<String> outputList = new LinkedList<String>();
        root.findDuplicates(outputList);
        Node<String> curr = outputList.reverse().getHead();
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
            root.getOutputData(0, 0, 1024, outputList, 0, numOfVisits);
            outputList.add(numOfVisits[0] + " quadtree nodes printed");
        }
        Node<String> curr = outputList.reverse().getHead();
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNext();
        }
    }


    public Point remove(int x, int y) {
        LinkedList<Point> removedPointList = new LinkedList<>();
        root = root.remove(x, y, 0, 0, 1024, removedPointList);
        Point removedPointData = removedPointList.getHead().getData();
        if (removedPointData != null) {
            numOfNodes--;
        } else {
            System.out.println("Point not found: (" + x + ", " + y + ")");
        }
        return removedPointData;
    }
}

// We are merging if
// 1) E, L with 2 nodes, L with 1 node, E
// 2) Leaf with 3 nodes, E, E, E - we merge to the parent

// If 3 identical, and one different - split. Do not split only if all 4 or more
// are strictly identical
