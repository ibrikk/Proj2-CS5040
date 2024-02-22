
public class QuadTree {
	QuadNode root;
	final static int WORLDVIEW = 1024;
	final static int xStart = 0;
	final static int yStart = 0;

	public QuadTree() {
		root = new LeafNode();
	}

	public void insert(Point p) {
		if (p.getxCoordinate() >= xStart && p.getxCoordinate() <= xStart + WORLDVIEW && p.getyCoordinate() >= yStart
				&& p.getyCoordinate() <= yStart + WORLDVIEW) {
			root = root.insert(p, 0, 0, WORLDVIEW);
		} else {
			System.out.println("Point falls outside of WORLDVIEW");
		}
	}

	public void printTree() {
		System.out.println("printing tree...");
		root.print(0,0,1024);
	}
}
