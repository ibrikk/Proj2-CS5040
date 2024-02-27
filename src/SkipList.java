import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-01-27
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     *
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     *
     * @param key
     *            key to be searched for
     * @return foundPoints
     *         array list of found rectangles
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> foundPoints = new ArrayList<>();
        SkipNode x = head; // Start at header node

        // Traverse down the levels
        for (int i = head.level; i >= 0; i--) {
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
        }

        // Move to the first element at the bottom level that is not less than
        // the key
        x = x.forward[0];

        // Traverse along the bottom level and collect all KVPairs with the
        // matching key
        while ((x != null) && (x.element().getKey().compareTo(key) == 0)) {
            foundPoints.add(x.pair);
            x = x.forward[0];
        }

        // Print found rectangles or a not-found message
        if (!foundPoints.isEmpty()) {
            for (KVPair<K, V> pair : foundPoints) {
                System.out.println("Found (" + pair.getKey() + ", " + pair.getValue()
                    .toString() + ")");
            }
        }
        else {
            System.out.println("Point not found: (" + key + ")");
        }

        return foundPoints;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * @return the headElement of the SkipList
     */
    public KVPair<K, V> getHeadElement() {
        return head.element();
    }


    /**
     * @return the headLevel of the SkipList
     */
    public int getHeadLevel() {
        return head.level;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     *
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        if (it == null) {
            return;
        }

        int newLevel = randomLevel();
        if (newLevel > head.level) {
            adjustHead(newLevel);
        }

        // Array to track the path where we need to insert
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);

        SkipNode x = head; // Start at header node

        for (int i = head.level; i >= 0; i--) {
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }
            update[i] = x;
        }

        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            x.forward[i] = update[i].forward[i];
            update[i].forward[i] = x;
        }

        size++;

    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     *
     * @param newLevel
     *            the number of levels to be added to head
     */
    public void adjustHead(int newLevel) {
        SkipNode newHead = new SkipNode(null, newLevel);
        for (int i = 0; i <= head.level; i++) {
            newHead.forward[i] = head.forward[i]; // Copy existing forward
                                                  // references
        }
        head = newHead; // Update the head reference
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     *
     * @param key
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        if (key == null) {
            System.out.println("Point not removed: null");
            return null;
        }

        // Array to keep track of the nodes that are just before the node to be
        // removed.
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);

        SkipNode x = head;

        // Find the position just before the node to be removed.
        for (int i = head.level; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].element().getKey()
                .compareTo(key) < 0) {
                x = x.forward[i];
            }
            update[i] = x;
        }

        x = x.forward[0];

        // Remove the node if it has the key.
        if (x != null && x.element().getKey().compareTo(key) == 0) {
            for (int i = 0; i <= head.level; i++) {
                if (update[i].forward[i] != x) {
                    break;
                }
                update[i].forward[i] = x.forward[i];
            }

            size--;
            System.out.println("Point removed: (" + x.pair.getKey() + ", "
                + x.pair.getValue().toString() + ")");
            return x.pair;
        }

        // If no point with the key is found, print the appropriate message.
        System.out.println("Point not removed: " + key);
        return null;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        System.out.println("SkipList dump:");
        // Initialize iterator
        Iterator<KVPair<K, V>> itr = new SkipListIterator();

        int headDepth = head.level + 1;
        System.out.println("Node with depth " + headDepth + ", Value (null)");

        // Iterate through the SkipList using the iterator
        while (itr.hasNext()) {
            KVPair<K, V> pair = itr.next();
            int depth = ((SkipList<K, V>.SkipListIterator)itr).getDepth();
            String value = "(" + pair.getKey() + ", " + pair.getValue()
                .toString() + ")";
            System.out.println("Node with depth " + depth + ", value " + value);
        }

        System.out.println("SkipList size is: " + size());
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     *
     * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
     *
     * @version 2024-01-27
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         *
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         *
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }


        /**
         * Returns the level of the node as an integer.
         *
         * @return level
         */
        public int getLevel() {
            return level;
        }

    }


    /**
     * This class provides an iterator for the SkipList data structure. It
     * allows
     * sequential access to the key-value pairs stored in the SkipList.
     */
    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        /**
         * Constructs a SkipListIterator and initializes it to start at the head
         * of
         * the SkipList.
         */
        public SkipListIterator() {
            current = head;
        }


        /**
         * Checks if there is a next element in the SkipList.
         *
         * @return true if there is a next element, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return current.forward[0] != null;
        }


        /**
         * Returns the next key-value pair in the SkipList and advances the
         * iterator.
         *
         * @return The next KVPair in the SkipList.
         */
        @Override
        public KVPair<K, V> next() {
            KVPair<K, V> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }


        /**
         * Retrieves the depth (level) of the current node being pointed to by
         * the
         * iterator.
         *
         * @return The depth of the current node.
         */
        public int getDepth() {
            return current.forward.length;
        }

    }

    /**
     * Provides an instance of the SkipListIterator for iterating over the
     * SkipList.
     *
     * @return An instance of SkipListIterator.
     */
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
