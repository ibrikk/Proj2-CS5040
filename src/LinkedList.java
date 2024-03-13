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
            return;
        }

    }


    /**
     * Retrieves the element at the specified index in the list.
     * <p>
     * This method navigates through the list starting from the head
     * until it reaches the element at the specified index. If the index
     * is zero, it immediately returns the data held by the head node.
     * It's important to ensure the index is within the bounds of the list
     * size to prevent {@link IndexOutOfBoundsException}.
     * </p>
     * 
     * @param idx
     *            The index of the element to retrieve, starting from 0.
     * @return The data stored in the node at the specified index.
     * @throws IndexOutOfBoundsException
     *             If the index is out of the range
     *             (index < 0 || index >= size of the list).
     */
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


    /**
     * Checks if the list contains a specific element.
     * <p>
     * This method iterates over each node in the list, starting from the head,
     * comparing the node's data with the element using the `.equals()` method.
     * The search continues until the element is found or the end of the list is
     * reached.
     * </p>
     * 
     * @param el
     *            The element to search for in the list.
     * @return true if the list contains the specified element, false otherwise.
     */
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
