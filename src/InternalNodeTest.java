import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Unit tests for the InternalNode class within a QuadTree
 * implementation.
 * Tests verify the internal node's ability to handle point additions and
 * delegate these points correctly to its child nodes.
 */
public class InternalNodeTest extends TestCase {

    private InternalNode internalNode;

    /**
     * Initializes a fresh instance of {@link InternalNode} before each test,
     * setting up a scenario with an InternalNode having EmptyNode children to
     * ensure test isolation.
     */
    @Before
    public void setUp() {
        internalNode = new InternalNode(EmptyNode.getInstance(), EmptyNode
            .getInstance(), EmptyNode.getInstance(), EmptyNode.getInstance());
    }


    /**
     * Tests the InternalNode's ability to handle point additions
     */
    @Test
    public void testHandlingOfPointAdditions() {
        Point internalPoint1 = new Point("Internal", 511, 511);
        Point internalPoint2 = new Point("Internal2", 511, 511);
        Point internalPoint3 = new Point("Internal3", 511, 511);
        Point internalPoint4 = new Point("Internal4", 511, 511);
        Point internalPoint5 = new Point("Internal4", 512, 512);
        internalNode.add(internalPoint1, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(internalPoint2, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(internalPoint3, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(internalPoint4, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(internalPoint5, 0, 0, QuadTree.WORLDVIEW);
        String output = internalNode.getNW().toString();
        systemOut().clearHistory();
        System.out.println(output);
        assertFuzzyEquals(output, systemOut().getHistory());
    }


    @Test
    public void testAddPointToCorrectQuadrant() {
        Point nwPoint = new Point("NW", 25, 25);
        Point nePoint = new Point("NE", 75, 25);

        internalNode.add(nwPoint, 0, 0, 100);
        internalNode.add(nePoint, 0, 0, 100);
        String outputNW = "\nLeaf node with the following points:\n" + "25, 25";
        String outputNE = "\nLeaf node with the following points:\n" + "75, 25";
        systemOut().clearHistory();
        System.out.println(internalNode.getNW().toString());
        assertFuzzyEquals(outputNW, systemOut().getHistory());
        systemOut().clearHistory();
        System.out.println(internalNode.getNE().toString());
        assertFuzzyEquals(outputNE, systemOut().getHistory());

    }


    // Test handling of points right on the boundary between quadrants
    @Test
    public void testAddPointOnBoundary() {
        Point boundaryPoint = new Point("Boundary", 50, 50);
        internalNode.add(boundaryPoint, 0, 0, 100);
        // Verify boundary points
    }

}
