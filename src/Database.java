import java.util.Iterator;

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

    // This is an Iterator object over the SkipList to loop through it from
    // outside
    // the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Point>> itr1; // only to traverse by
                                                  // value

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
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
    // TODO: implement insert properly to utilize Point class
    public void insert(KVPair<String, Point> pair) {
        // Delegates

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
        // TODO: Check if a node with that value and name exists
        // TODO: Implement insert logic into Quadtree
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
    public void remove(String name) {
        list.remove(name);
        // TODO: the method above returns the pair. Get the coordinates and
        // remove from QuadTree
    }


    /**
     * Removes a Point with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the point to be removed
     * @param y
     *            x-coordinate of the point to be removed
     * @param w
     *            width of the point to be removed
     * @param h
     *            height of the point to be removed
     */
    public void remove(int x, int y) {
        // TODO: remove the point from QuadTree and get its Name then remove the
        // point from the
        // SkipList
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
    public void regionsearch(int x, int y, int w, int h) {

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
        // TODO: properly implement the dump method.
// list.dump();
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
