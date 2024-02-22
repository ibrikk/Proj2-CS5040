
public class LeafNode implements QuadNode {
	LinkedList<Point> points;
	final static int CAPACITY = 3;

	 
	public LeafNode(){
		points = new LinkedList<>();
	}
	
    @Override
    public QuadNode insert(Point point, int currX, int currY, int split) {
        // TODO Auto-generated method stub
    	//should we check here the coordinates?
        if (!willPutOverCapacity(point)) {
        	points.add(point);
        	return this;
        }
        //if this insert will put over capacity, we need to split and return the new internal node
        QuadNode splitNode = split();
    	Node<Point> currPt  = points.getHead();
    	//insert existing points in the points list
    	while (currPt!=null) {
    		splitNode.insert(currPt.getData(), currX, currY, split);
    		currPt = currPt.getNext();
    	}
    	//insert the new point we wanted to insert
    	splitNode.insert(point, currX, currY, split);
    	return splitNode;
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
    		currPt = currPt.getNext();
    	}
    	return unique;
    }
    
    private QuadNode split() {
    	InternalNode newInternalNode = new InternalNode(EmptyNode.getInstance(), EmptyNode.getInstance(),EmptyNode.getInstance(),EmptyNode.getInstance());
    	return newInternalNode;
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
