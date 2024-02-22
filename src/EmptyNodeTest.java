import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Unit tests for the {@link EmptyNode} class in a QuadTree implementation.
 * Tests verify the behavior of adding points to an EmptyNode and its transformation into a LeafNode.
 */
public class EmptyNodeTest extends TestCase {

    private EmptyNode emptyNode;

    /**
     * Initializes a fresh instance of {@link EmptyNode} before each test to ensure test isolation.
     */
    @Before
    public void setUp() {
        emptyNode = EmptyNode.getInstance();
    }

    /**
     * Tests that adding a point to an {@link EmptyNode} results in the creation
     * of a {@link LeafNode} containing that point.
     */
    @Test
    public void testAdditionCreatesLeafNode() {
        Point newPoint = new Point("Empty", 100, 100);
        QuadNode result = emptyNode.add(newPoint, 0, 0, QuadTree.WORLDVIEW);
        assertTrue("Adding a point to an EmptyNode should result in a LeafNode.", result instanceof LeafNode);
    }
}
