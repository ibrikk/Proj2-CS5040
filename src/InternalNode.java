
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
    	//Figure out which quadrant to insert into
    	if (point.getxCoordinate() < currX + (split/2)) {
    		if (point.getyCoordinate() < currY + (split/2)) {
    			//point to insert falls into NW. Check if empty node.
    			if (NW instanceof EmptyNode) {
    				NW = new LeafNode();
    			}
    			return NW.insert(point, currX, currY, (split/2));
    		}
    		//point to insert falls into SW. Check if empty node.
			if (SW instanceof EmptyNode) {
				SW = new LeafNode();
			}
			return SW.insert(point, currX, currY+(split/2), (split/2));
    	}
    	else {
    		//point to insert falls into NE. Check if empty node.
    		if (point.getyCoordinate() < currY + (split/2)) {
    			if (NE instanceof EmptyNode) {
    				NE = new LeafNode();
    			}
				return NE.insert(point, currX+(split/2), currY, (split/2));
    		}
    		//point to insert falls into SE. Check if empty node.
			if (SE instanceof EmptyNode) {
				SE = new LeafNode();
			}
			return SE.insert(point, currX+(split/2), currY+(split/2), (split/2));
    	}
        
        
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

//    private QuadNode quadrantToInsert(Point point, int currX, int currY, int split) {
//    	System.out.println("x,y"+point.getxCoordinate()+" "+point.getyCoordinate());
//    	System.out.println(currX);
//    	System.out.println(currY);
//    	if (point.getxCoordinate() < currX + split/2) {
//    		if (point.getyCoordinate() < currY + split/2) {
//    			System.out.println("NW");
//    			if (NW instanceof EmptyNode) {
//    				NW = new LeafNode();
//    			}
//				NW.insert(point, currX, currY, split/2);
//    		}
//    		System.out.println("SW");
//			if (SW instanceof EmptyNode) {
//				NW = new LeafNode();
//			}
//			NW.insert(point, currX, currY, split/2);
//    	}
//    	else {
//    		if (point.getyCoordinate() < currY + split/2 ) {
//    			System.out.println("NE");
//    			if (NE instanceof EmptyNode) {
//    				NE = new LeafNode();
//    			}
//				NE.insert(point, currX, currY, split/2);
//    		}
//    		System.out.println("SE");
//			if (SE instanceof EmptyNode) {
//				SE = new LeafNode();
//			}
//			SE.insert(point, currX, currY, split/2);
//    	}
//    }
    
    public void print(int currX, int currY, int split) {
    	String nw = String.format("NW x, y, dimension: %d, %d, %d", currX, currY,(split/2));
    	String sw = String.format("SW x, y, dimension: %d, %d, %d", currX, currY+(split/2), (split/2));
    	String ne = String.format("NE x, y, dimension: %d, %d, %d", currX+(split/2), currY, (split/2));
    	String se = String.format("SE x, y, dimension: %d, %d, %d", currX+(split/2), currY+(split/2), (split/2));
    	
    	
    	System.out.println("Internal Node with (X,Y) as: " + currX + ", " + currY
    			+ "and l/w of: " + split
    			+ "the coordinates of the children nodes are: "
    			+ nw + sw + ne + se);
    	NW.print(currX, currY,(split/2));
    	SW.print(currX, currY+(split/2), (split/2));
    	NE.print(currX+(split/2), currY, (split/2));
    	SE.print(currX+(split/2), currY+(split/2), (split/2));
}
}