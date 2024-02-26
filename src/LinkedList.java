/**
 * On my honor:
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or
 * unmodified.
 * 
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 *
 * - I have not discussed coding details about this project with
 * anyone other than my partner (in the case of a joint
 * submission), instructor, ACM/UPE tutors or the TAs assigned
 * to this course. I understand that I may discuss the concepts
 * of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes
 * anything during the discussion or modifies any computer file
 * during the discussion. I have violated neither the spirit nor
 * letter of this restriction.
 */

/**
 * LinkedList class creates the Linked List data structure and has methods to
 * manipulate the structure.
 * 
 * @author Ibrahim Khalilov ibrahimk
 * @version 2023-10-15
 */
public class LinkedList<T> {

    private Node<T> head;
    private int numberOfEntries;

    /**
     * Constructor for the LinkedList class
     */
    public LinkedList() {
        head = null;
        numberOfEntries = 0;
    }


    /**
     * Adds a node to the beginning of the linked list
     * 
     * @param <T>
     * 
     * @param newEntry
     *            - the data string to add into the linked list
     */
    public void add(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);
        newNode.setNext(head); // Make new node reference rest of chain
        head = newNode; // New node is at beginning of chain
        numberOfEntries++;
    }


    /**
     * Removes a node to the beginning of the linked list. Used only for
     * testings
     */
    public void remove() {
        Node<T> temp = head.getNext();
        head = temp;
        numberOfEntries--;
    }


    /**
     * Getter for head
     * 
     * @return head
     */
    public Node<T> getHead() {
        return head;
    }


    /**
     * Getter for number of entries
     * 
     * @return numberOfEntries
     */
    public int getNumberOfEntries() {
        return numberOfEntries;
    }


    /**
     * Reversing a linked list
     * 
     * @return reversedList
     */
    public LinkedList<T> reverse() {
        LinkedList<T> reversedList = new LinkedList<T>();

        Node<T> current = head;
        while (current != null) {
            reversedList.add(current.getData());
            current = current.getNext();
        }

        return reversedList;
    }

}




/**
 * Node class creates the Node structure
 * 
 * @author Ibrahim Khalilov ibrahimk
 * @version 2023-10-15
 * @param <T>
 */
class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * @param <T>
     * @param newEntry
     *            - string data
     * @param next
     *            - pointer to next node
     */
    public Node(T newEntry, Node<T> next) {
        this.data = newEntry;
        this.next = next;
    }


    /**
     * @param <T>
     * @param newEntry
     *            adding data and making next null
     */
    public Node(T newEntry) {
        this(newEntry, null);
    }


    /**
     * Getter for data
     * 
     * @return
     * 
     * @return data
     */
    public T getData() {
        return data;
    }


    /**
     * Getter for next. Mainly used in testing
     * 
     * @return next
     */
    public Node<T> getNext() {
        return next;
    }


    /**
     * Setter for next.
     * 
     * @param head
     *            - new node to set next to
     */
    public void setNext(Node<T> head) {
        next = head;
    }
}
