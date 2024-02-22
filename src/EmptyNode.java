public class EmptyNode implements QuadNode {
	private static final EmptyNode instance = new EmptyNode();

	private EmptyNode() {}
	
	public static EmptyNode getInstance() {
		return instance;
	}
	
    @Override
    public QuadNode insert(Point point, int currX, int currY, int split) {
        // TODO Auto-generated method stub
        return null;
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

}
