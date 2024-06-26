
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

import student.TestCase;

/**
 * LinkedListTest class tests the LinkedList class
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class LinkedListTest extends TestCase {
    /**
     * Setup tests
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Testing linked list for adding and removing nodes
     */
    public void testAddAndRemove() {
        LinkedList<String> list = new LinkedList<String>();
        list.add("5");
        list.add("10");
        assertEquals("5", list.getHead().getData());
        list.remove(0);
        assertEquals("10", list.getHead().getData());
        list.remove(0);
        assertNull(list.getHead());
    }

}
