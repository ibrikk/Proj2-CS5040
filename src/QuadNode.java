/**
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public interface QuadNode {

    public abstract QuadNode add(Point point, int currX, int currY, int split);


    public abstract QuadNode remove(
        int originX,
        int originY,
        int currX,
        int currY,
        int split,
        Point[] removedPoints);


    public abstract QuadNode remove(
        Point point,
        int currX,
        int currY,
        int split,
        Point[] removedPoints);


    public abstract LinkedList<String> findDuplicates(LinkedList<String> list);


    public abstract LinkedList<Point> regionSearch(
        int x,
        int y,
        int width,
        int height,
        LinkedList<Point> points,
        int currentX,
        int currentY,
        int split,
        int[] numOfVisits);


    public abstract LinkedList<String> getOutputData(
        int currentX,
        int currentY,
        int split,
        LinkedList<String> list,
        int numOfIndents,
        int[] numOfVisits);

}
