import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Unit tests for the {@link LeafNode} class within a QuadTree implementation.
 * These tests focus on verifying the addition of points within capacity and the
 * node's behavior when exceeding its capacity (splitting into an InternalNode).
 * 
 * @author Ibrahim Khalilov
 * @author Francisca Wood
 * @version 2024-03-12
 */
public class LeafNodeTest extends TestCase {

    private LeafNode leafNode;

    /**
     * Initializes a fresh instance of {@link LeafNode} before each test to
     * ensure test isolation.
     */
    @Before
    public void setUp() {
        leafNode = new LeafNode();
    }


    /**
     * Verifies that a LeafNode can correctly add points up to its capacity
     * without needing to split into an {@link InternalNode}.
     */
    @Test
    public void testAdditionWithinCapacity() {
        Point point1 = new Point("Leaf1", 100, 100);
        leafNode.add(point1, 0, 0, QuadTree.WORLDVIEW);
        assertEquals("LeafNode should contain 1 point after addition.", 1,
            leafNode.getPointsList().getNumberOfEntries());
    }


    /**
     * Ensures that a LeafNode splits into an InternalNode when an addition
     * of a point exceeds its capacity. This test checks the splitting and
     * redistribution of points into the new structure.
     */
    @Test
    public void testSplitUponExceedingCapacity() {
        for (int i = 0; i < LeafNode.CAPACITY; i++) {
            leafNode.add(new Point("Point" + i, i, i), 0, 0,
                QuadTree.WORLDVIEW);
        }
        // Adding one more point to exceed capacity and trigger split
        QuadNode result = leafNode.add(new Point("SplitPoint",
            LeafNode.CAPACITY, LeafNode.CAPACITY), 0, 0, QuadTree.WORLDVIEW);
        assertFalse(result instanceof LeafNode);
    }


    /**
     * Tests the addition of a point to a LeafNode that is below its capacity.
     */
    @Test
    public void testAddPointBelowCapacity() {
        Point point1 = new Point("A", 10, 10);
        leafNode.add(point1, 0, 0, 100);
        assertEquals("LeafNode should have 1 point after addition.", 1,
            leafNode.getPointsList().getNumberOfEntries());
    }


    /**
     * Tests the behavior of a LeafNode when adding a duplicate point.
     */
    @Test
    public void testHandleDuplicatePoint() {
        Point point1 = new Point("B", 20, 20);
        leafNode.add(point1, 0, 0, 100);
        leafNode.add(point1, 0, 0, 100);
        assertEquals(1, leafNode.getPointsList().getNumberOfEntries());
    }


    /**
     * Tests the transition of a LeafNode to an InternalNode.
     */
    @Test
    public void testTransitionToInternalNode() {
        leafNode.add(new Point("C", 30, 30), 0, 0, 100);
        leafNode.add(new Point("D", 40, 40), 0, 0, 100);
        leafNode.add(new Point("E", 50, 50), 0, 0, 100);
        assertTrue(leafNode instanceof LeafNode);
    }
}
