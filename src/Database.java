/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation. Also note that the Database class
 * will have a clearer role in Project2, where we will have two data structures.
 * The Database class will then determine which command should be directed to
 * which data structure.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-01-27
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;

    private QuadTree tree;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
        tree = new QuadTree();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will add
     * the KVPair specified into the sorted SkipList appropriately
     *
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Point> pair) {
        if (!(isValidAscii(pair.getKey()))) {
            System.out.println("The name must begin with a letter, "
                + "and may contain letters, digits,"
                + " and underscore characters.");
            return;
        }
        if (pair.getValue().isInvalid()) {
            System.out.println("Point rejected: (" + pair.getKey() + ", " + pair
                .getValue().toString() + ")");
            return;
        }
        list.insert(pair);
        tree.insert(pair.getValue());
        System.out.println("Point inserted: (" + pair.getKey() + ", " + pair
            .getValue().toString() + ")");
    }


    /**
     * Validates if a given string adheres to a specific ASCII format. The name
     * (key) must begin with a letter, and may contain letters, digits, and
     * underscore characters. The method checks each character of the string to
     * ensure it conforms to these rules.
     *
     * @param key
     *            The string to be validated.
     * @return true if the string is valid according to the specified ASCII
     *         format, false otherwise.
     */
    public boolean isValidAscii(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }

        // Check first character is a letter
        char firstChar = key.charAt(0);
        if (!((firstChar >= 'A' && firstChar <= 'Z') || (firstChar >= 'a'
            && firstChar <= 'z'))) {
            return false;
        }

        // Check remaining characters
        for (int i = 1; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
                || (ch >= '0' && ch <= '9') || (ch == '_'))) {
                return false;
            }
        }

        return true;
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name, boolean removedFromTree) {
        KVPair<String, Point> removedPair = list.remove(name);
        if (removedPair == null) {
            return;
        }
        String output = "Point removed: (" + removedPair.getKey() + ", "
            + removedPair.getValue().toString() + ")";
        if (removedFromTree) {
            System.out.println(output);
            return;
        }
        tree.remove(removedPair.getValue());
        System.out.println(output);
    }


    /**
     * Removes a Point with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the point to be removed
     * @param y
     *            x-coordinate of the point to be removed
     * 
     */
    public void remove(int x, int y) {
        if (x < 0 || y < 0 || x > 1023 || y > 1023) {
            System.out.println("Point rejected: (" + x + ", " + y + ")");
            return;
        }
        Point removedPoint = tree.remove(x, y);
        if (removedPoint == null) {
            return;
        }
        remove(removedPoint.getName(), true);
    }


    /**
     * Displays all the points inside the specified region. The point
     * must have some area inside the area that is created by the region,
     * meaning, points that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public LinkedList<String> regionSearch(int x, int y, int w, int h) {
        if (w <= 0 || h <= 0) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return null;
        }
        int[] numOfVisits = { 0 };
        LinkedList<Point> result = tree.regionSearch(x, y, w, h, numOfVisits);
        LinkedList<String> output = new LinkedList<>();
        output.add("Points intersecting region (" + x + ", " + y + ", " + w
            + ", " + h + "):");
        Node<Point> curr = result.getHead();
        while (curr != null) {
            output.add("Point found: (" + curr.getData().getName() + ", " + curr
                .getData().toString() + ")");
            curr = curr.getNext();
        }
        output.add(numOfVisits[0] + " quadtree nodes visited");
        Node<String> currPointer = output.getHead();
        while (currPointer != null) {
            System.out.println(currPointer.getData());
            currPointer = currPointer.getNext();
        }
        return output;
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     *
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        list.search(name);
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
        tree.dump();
    }


    public void duplicates() {
        tree.duplicates();
    }


    /**
     * Returns the total size of the database ie the size of the SkipList
     *
     * @return size
     */
    public int size() {
        return list.size();
    }
}
