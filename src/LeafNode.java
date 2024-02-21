
public class LeafNode implements QuadNode {
	LinkedList<Point> points;
	int currX;
	int currY;
	int split;
	final static int CAPACITY = 3;

	 
	public LeafNode(Point point, int currX, int currY, int split){
		LinkedList<Point> points = new LinkedList<>();
		points.add(point);
		this.points=points;
	}
	
    @Override
    public QuadNode insert(Point point, int currX, int currY, int split) {
        // TODO Auto-generated method stub
        if (!willPutOverCapacity(point)) {
        	points.add(point);
        	return this;
        }
        //if this insert will put over capacity, we need to split and return the new internal node
        return null;
    }
    
    private boolean willPutOverCapacity(Point point) {
    	if (points.getNumberOfEntries()<CAPACITY) {
    		return false;
    	}
    	Node currPt  = points.getHead();
    	boolean unique = true;
    	while (currPt!=null&&unique) {
    		if (currPt.getData().equals(point)) {
    			unique=false;
    		}
    	}
    	return unique;
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
