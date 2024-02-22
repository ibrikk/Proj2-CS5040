
public class QuadTree {
	QuadNode root;
	final static int WORLDVIEW=1024;
	
	public QuadTree() {
		root = new LeafNode();
	}
	
	public void insert(Point p) {
		root.insert(p, 0, 0, WORLDVIEW);
	}
}

