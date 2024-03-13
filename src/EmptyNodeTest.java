import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Unit tests for the EmptyNode class in a QuadTree implementation.
 * Tests verify the behavior of adding points to an EmptyNode and its
 * transformation into a LeafNode.
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 * 
 */
public class EmptyNodeTest extends TestCase {

    private EmptyNode emptyNode;
    private LinkedList list;
    private int[] numOfVisits;

    /**
     * Initializes a fresh instance of EmptyNode before each test to ensure test
     * isolation.
     */
    @Before
    public void setUp() {
        emptyNode = EmptyNode.getInstance();

        list = new LinkedList<>();
        numOfVisits = new int[1];
    }


    /**
     * Tests that adding a point to an EmptyNode results in the creation
     * of a LeafNode containing that point.
     */
    @Test
    public void testAdditionCreatesLeafNode() {
        Point newPoint = new Point("Empty", 100, 100);
        QuadNode result = emptyNode.add(newPoint, 0, 0, QuadTree.WORLDVIEW);
        assertTrue(
            "Adding a point to an EmptyNode should result in a LeafNode.",
            result instanceof LeafNode);
    }

    /**
     * Tests that adding a point to an EmptyNode results in the creation
     * of a LeafNode containing that point.
     */
    @Test
    public void testGetOutputData() {
        emptyNode = new EmptyNode();
        int currentX = 100;
        int currentY = 200;
        int split = 50;
        int initialNumOfVisits = numOfVisits[0];

        @SuppressWarnings("unchecked")
        LinkedList<String> result = emptyNode.getOutputData(currentX, currentY,
            split, list, 2, numOfVisits);

        // Check if numOfVisits incremented
        assertEquals("Number of visits should increment by 1",
            initialNumOfVisits + 1, numOfVisits[0]);

        // Check if the output list is updated correctly
        assertFalse("Output list should not be empty", result
            .getNumberOfEntries() == 0);

        // Construct expected string
        String expectedOutput = "    Node at " + currentX + ", " + currentY
            + ", " + split + ": Empty";
        assertEquals("Output list should contain the correct node description",
            expectedOutput, result.get(result.getNumberOfEntries() - 1));
    }

}
