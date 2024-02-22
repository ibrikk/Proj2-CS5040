import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Unit tests for the {@link LeafNode} class within a QuadTree implementation.
 * These tests focus on verifying the addition of points within capacity and the
 * node's
 * behavior when exceeding its capacity (splitting into an InternalNode).
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
     * Verifies that a LeafNode can correctly add points up to its
     * capacity
     * without needing to split into an {@link InternalNode}.
     */
    @Test
    public void testAdditionWithinCapacity() {
        Point point1 = new Point("Leaf1", 100, 100);
        leafNode.add(point1, 0, 0, QuadTree.WORLDVIEW);
        assertEquals("LeafNode should contain 1 point after addition.", 1,
            leafNode.pointsList.getNumberOfEntries());
    }


    /**
     * Ensures that a LeafNode splits into an InternalNode when
     * an addition
     * of a point exceeds its capacity. This test checks the splitting and
     * redistribution
     * of points into the new structure.
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
        assertFalse(
            "LeafNode should split into an InternalNode upon exceeding capacity.",
            result instanceof LeafNode);
    }
}
