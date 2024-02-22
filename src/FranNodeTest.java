
public class FranNodeTest {
	public static void main(String args[]) {
		System.out.println("test");
		QuadNode root = new LeafNode();
		root.insert(new Point("point a",10,10), 0, 0, 1024);
		root.insert(new Point("point b",100,100), 0, 0, 1024);
		root.insert(new Point("point c",200,200), 0, 0, 1024);
		root.insert(new Point("point X",600,200), 0, 0, 1024);
	}
}
