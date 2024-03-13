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
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
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
        if (head == null) {
            head = newNode;
        }
        else {
            Node<T> curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
        numberOfEntries++;
    }


    /**
     * Removes a node to the beginning of the linked list. Used only for
     * testings
     */
    public void remove(int idx) {
        if (idx == 0) {
            head = head.getNext();
            numberOfEntries--;
            return;
        }

        Node<T> curr = head;

        for (int i = 1; i < idx; i++) {
            if (curr.getNext() == null) {
                // If the next node is null before reaching the index,
                // it means the index is out of bounds
                return;
            }
            curr = curr.getNext();
        }
        if (curr.getNext() != null) {
            curr.setNext(curr.getNext().getNext());
            numberOfEntries--;
            return;
        }
        else {
            // If current.getNext() is null, it means we are trying to delete
            // a node that doesn't exist (index equal to size of the list),
            // which should not happen due to the initial size check
            return;
        }

    }


    public T get(int idx) {
        Node<T> curr = head;
        T el = curr.getData();
        if (idx == 0) {
            return el;
        }
        else {
            for (int i = 0; i < idx; i++) {
                curr = curr.getNext();
            }
            el = curr.getData();
        }
        return el;

    }


    public boolean contains(T el) {
        Node<T> curr = head;
        while (curr != null) {
            if (curr.getData().equals(el)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
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
     * Setter for data
     * 
     * 
     * @param data
     */
    public void setData(T data) {
        this.data = data;
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
    public void setNext(Node<T> newNode) {
        next = newNode;
    }

}
