
public class InternalNode implements QuadNode {
	QuadNode NW;
	QuadNode NE;
	QuadNode SE;
	QuadNode SW;
	
	public InternalNode(QuadNode NW, QuadNode NE, QuadNode SE, QuadNode SW){
		this.NW=NW;
		this.NE=NE;
		this.SE=SE;
		this.SW=SW;
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
