import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Test class for QuadTree. It tests the functionality of inserting
 * points
 * within and outside the predefined WORLDVIEW, splitting leaf nodes upon
 * reaching capacity,
 * and the behavior of adding points to empty nodes.
 */
public class QuadTreeTest extends TestCase {

    /**
     * Sets up the test environment before each test method.
     * This method is called before the execution of each test method.
     */
    @Before
    public void setUp() {
        // Setup can be used to initialize common objects if needed
    }


    /**
     * Tests inserting a point that lies within the QuadTree's WORLDVIEW.
     * Verifies that the QuadTree correctly increments its node count when a
     * valid point is inserted.
     */
    @Test
    public void testInsertWithinWorldView() {
        QuadTree quadTree = new QuadTree();
        Point pointInside = new Point("A", 500, 500);
        quadTree.insert(pointInside);
        assertEquals("QuadTree should have 1 node after insertion.", 1, quadTree
            .getNumOfNodes());
    }


    /**
     * Tests attempting to insert a point that falls outside the QuadTree's
     * WORLDVIEW.
     * Ensures that the QuadTree does not increment its node count when an
     * invalid point is inserted.
     */
    @Test
    public void testInsertOutsideWorldView() {
        QuadTree quadTree = new QuadTree();
        Point pointOutside = new Point("B", 1025, 1025);
        quadTree.insert(pointOutside);
        assertEquals(
            "QuadTree should have 0 nodes after attempting to insert a point outside the WORLDVIEW.",
            0, quadTree.getNumOfNodes());
    }


    /**
     * Tests the behavior of a LeafNode when adding points beyond its capacity.
     * Verifies that the node correctly splits into an InternalNode upon
     * reaching capacity.
     */
    @Test
    public void testLeafNodeSplit() {
        LeafNode leafNode = new LeafNode();
        for (int i = 0; i < LeafNode.CAPACITY; i++) {
            leafNode.add(new Point("Point" + i, i, i), 0, 0,
                QuadTree.WORLDVIEW);
        }
        // Inserting one more point to trigger the split
        QuadNode result = leafNode.add(new Point("SplitPoint",
            LeafNode.CAPACITY, LeafNode.CAPACITY), 0, 0, QuadTree.WORLDVIEW);
        assertFalse(
            "Expected a split resulting in an InternalNode instead of a LeafNode.",
            result instanceof LeafNode);
    }


    /**
     * Tests adding a point to an EmptyNode.
     * Ensures that adding a point results in the transformation of the
     * EmptyNode into a LeafNode.
     */
    @Test
    public void testEmptyNodeAddition() {
        EmptyNode emptyNode = EmptyNode.getInstance();
        Point newPoint = new Point("New", 100, 100);
        QuadNode result = emptyNode.add(newPoint, 0, 0, QuadTree.WORLDVIEW);
        assertTrue(
            "Adding a point to an EmptyNode should result in a LeafNode.",
            result instanceof LeafNode);
    }

}
