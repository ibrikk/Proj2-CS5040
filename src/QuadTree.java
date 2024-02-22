
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
        // Checks if point falls outside the world view
        if (p.getxCoordinate() >= xStart && p.getxCoordinate() <= xStart
            + WORLDVIEW && p.getyCoordinate() >= yStart && p
                .getyCoordinate() <= yStart + WORLDVIEW) {
            root = root.add(p, 0, 0, WORLDVIEW);
            numOfNodes++;
        }
        else {
            System.out.println("Point falls outside of WORLDVIEW");
        }
    }


    public void printTree() {
        System.out.println("printing tree...");
        root.print(0, 0, 1024);
    }
}

// We are merging if
// 1) E, L with 2 nodes, L with 1 node, E
// 2) Leaf with 3 nodes, E, E, E - we merge to the parent

// If 3 identical, and one different - split. Do not split only if all 4 or more
// are strictly identical
