
public class QuadTree {
    QuadNode root;
    QuadNode flyWeightNode;
    int numOfNodes;
    final static int WORLDVIEW = 1024;
    final static int xStart = 0;
    final static int yStart = 0;

    public QuadTree() {
        flyWeightNode = new EmptyNode();
        root = flyWeightNode;
        numOfNodes = 0;
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
