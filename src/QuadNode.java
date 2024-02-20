
public interface QuadNode {

    public abstract QuadNode insert(
        Point point,
        int currX,
        int currY,
        int split);


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

}
