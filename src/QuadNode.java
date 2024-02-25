
public interface QuadNode {

    public abstract QuadNode add(Point point, int currX, int currY, int split);


    public abstract QuadNode remove(
        Point point,
        int currX,
        int currY,
        int split,
        LinkedList<Point> removedPoints);


    public abstract LinkedList<String> findDuplicates(LinkedList<String> list);


    public abstract LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        // list of points
        LinkedList<Point> result,
        int currentX,
        int currentY,
        int split,
        LinkedList<Integer> numOfVisits);
    
    public abstract LinkedList<String> getContents(int currentX, int currentY,
        int split, LinkedList<String> list, int numOfIndents, 
        int[] numOfVisits);

// public abstract void print(
// int currX,
// int currY,
// int split);
}
