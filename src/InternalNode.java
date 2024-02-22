
public class InternalNode implements QuadNode {
    QuadNode NW;
    QuadNode NE;
    QuadNode SE;
    QuadNode SW;

    public InternalNode(QuadNode NW, QuadNode NE, QuadNode SE, QuadNode SW) {
        this.NW = NW;
        this.NE = NE;
        this.SE = SE;
        this.SW = SW;
    }


    @Override
    public QuadNode add(Point point, int currX, int currY, int split) {
        // TODO Auto-generated method stub
        // the return object here... should it just be the current node?
        // Figure out which quadrant to insert into
        if (point.getxCoordinate() < currX + (split / 2)) {
            if (point.getyCoordinate() < currY + (split / 2)) {
                // point to insert falls into NW. Check if empty node.
                if (NW instanceof EmptyNode) {
                    NW = new LeafNode();
                }
                NW.add(point, currX, currY, (split / 2));
            }
            // point to insert falls into SW. Check if empty node.
            if (SW instanceof EmptyNode) {
                SW = new LeafNode();
            }
            SW.add(point, currX, currY + (split / 2), (split / 2));
        }
        else {
            // point to insert falls into NE. Check if empty node.
            if (point.getyCoordinate() < currY + (split / 2)) {
                if (NE instanceof EmptyNode) {
                    NE = new LeafNode();
                }
                NE.add(point, currX + (split / 2), currY, (split / 2));
            }
            // point to insert falls into SE. Check if empty node.
            if (SE instanceof EmptyNode) {
                SE = new LeafNode();
            }
            SE.add(point, currX + (split / 2), currY + (split / 2), (split
                / 2));
        }

        return this;
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


    public void print(int currX, int currY, int split) {
        String nw = String.format("NW x, y, dimension: %d, %d, %d\n", currX,
            currY, (split / 2));
        String sw = String.format("SW x, y, dimension: %d, %d, %d\n", currX,
            currY + (split / 2), (split / 2));
        String ne = String.format("NE x, y, dimension: %d, %d, %d\n", currX
            + (split / 2), currY, (split / 2));
        String se = String.format("SE x, y, dimension: %d, %d, %d\n", currX
            + (split / 2), currY + (split / 2), (split / 2));

        System.out.println("Internal Node with (X,Y) as: " + currX + ", "
            + currY + "and l/w of: " + split
            + "\nthe coordinates of the children nodes are: \n" + nw + sw + ne
            + se);
        NW.print(currX, currY, (split / 2));
        SW.print(currX, currY + (split / 2), (split / 2));
        NE.print(currX + (split / 2), currY, (split / 2));
        SE.print(currX + (split / 2), currY + (split / 2), (split / 2));
    }
}
