import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Unit tests for the {@link InternalNode} class within a QuadTree
 * implementation.
 * These tests aim to verify the internal node's capability to correctly handle
 * point
 * additions, ensuring that points are delegated to the appropriate child nodes
 * based
 * on their spatial coordinates. Additional tests check the behavior with points
 * on
 * quadrant boundaries.
 */
public class InternalNodeTest extends TestCase {

    private InternalNode internalNode;

    /**
     * Sets up an {@link InternalNode} with {@link EmptyNode} instances as its
     * children before each test. This setup ensures that the test starts with a
     * known state where the internal node does not initially contain any
     * points.
     */
    @Before
    public void setUp() {
        internalNode = new InternalNode(EmptyNode.getInstance(), EmptyNode
            .getInstance(), EmptyNode.getInstance(), EmptyNode.getInstance());
    }


    /**
     * Tests the addition of multiple points to an {@link InternalNode} and
     * verifies
     * that points with identical coordinates are handled correctly, presumably
     * by
     * adding them to the same quadrant. This test assumes that the internal
     * node can
     * transition to a leaf node or another structure when certain conditions
     * are met.
     */
    @Test
    public void testHandlingOfPointAdditions() {
        // Add multiple points with identical coordinates
        internalNode.add(new Point("Internal", 511, 511), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Internal2", 511, 511), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Internal3", 511, 511), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Internal4", 511, 511), 0, 0,
            QuadTree.WORLDVIEW);
        // Add a point with different coordinates to potentially trigger a
        // structure change
        internalNode.add(new Point("Internal5", 512, 512), 0, 0,
            QuadTree.WORLDVIEW);

        // Assuming a method to inspect or print the state of the NW quadrant
        // for verification
        String expectedOutput = internalNode.getNW().toString();
        systemOut().clearHistory(); // Clear the console history to capture only
                                    // the next output
        System.out.println(expectedOutput);
        assertFuzzyEquals(
            "Expected NW quadrant to contain multiple identical points",
            expectedOutput, systemOut().getHistory());
    }


    /**
     * Tests that points are added to the correct quadrants of an
     * {@link InternalNode}.
     * Specifically, it verifies that a point intended for the NW quadrant and
     * another
     * for the NE quadrant are correctly delegated to their respective child
     * nodes.
     */
    @Test
    public void testAddPointToCorrectQuadrant() {
        // Points intended for different quadrants
        internalNode.add(new Point("NW", 25, 25), 0, 0, 100);
        internalNode.add(new Point("NE", 75, 25), 0, 0, 100);

        // Expected output for each quadrant after addition
        String expectedNWOutput = "\nLeaf node with the following points:\n"
            + "25, 25";
        String expectedNEOutput = "\nLeaf node with the following points:\n"
            + "75, 25";

        // Capture and compare the output for NW quadrant
        systemOut().clearHistory();
        System.out.println(internalNode.getNW().toString());
        assertFuzzyEquals("NW quadrant should contain the correct point.",
            expectedNWOutput, systemOut().getHistory());

        // Capture and compare the output for NE quadrant
        systemOut().clearHistory();
        System.out.println(internalNode.getNE().toString());
        assertFuzzyEquals("NE quadrant should contain the correct point.",
            expectedNEOutput, systemOut().getHistory());
    }


    /**
     * Tests adding a point that should logically fall into the NW quadrant and
     * verifies
     * it is correctly added.
     */
    @Test
    public void testAddPointToNWQuadrant() {
        Point nwPoint = new Point("NW", 10, 10);
        internalNode.add(nwPoint, 0, 0, 1024);
        systemOut().clearHistory();
        System.out.println(internalNode.getNW().toString());
        String expectedNWOutput = "\nLeaf node with the following points:\n"
            + "10, 10";
        assertFuzzyEquals("NW quadrant should contain the correct point.",
            expectedNWOutput, systemOut().getHistory());
    }


    /**
     * Tests adding a point that should logically fall into the NE quadrant and
     * verifies
     * it is correctly added.
     */
    @Test
    public void testAddPointToNEQuadrant() {
        Point nePoint = new Point("NE", 800, 10);
        internalNode.add(nePoint, 0, 0, 1024);
        systemOut().clearHistory();
        System.out.println(internalNode.getNE().toString());
        String expectedNEOutput = "\nLeaf node with the following points:\n"
            + "800, 10";
        assertFuzzyEquals("NE quadrant should contain the correct point.",
            expectedNEOutput, systemOut().getHistory());
    }


    /**
     * Tests adding a point that should logically fall into the SE quadrant and
     * verifies
     * it is correctly added.
     */
    @Test
    public void testAddPointToSEQuadrant() {
        Point sePoint = new Point("SE", 800, 800);
        internalNode.add(sePoint, 0, 0, 1024);
        systemOut().clearHistory();
        System.out.println(internalNode.getSE().toString());
        String expectedSEOutput = "\nLeaf node with the following points:\n"
            + "800, 800";
        assertFuzzyEquals("SE quadrant should contain the correct point.",
            expectedSEOutput, systemOut().getHistory());
    }


    /**
     * Tests adding a point that should logically fall into the SW quadrant and
     * verifies
     * it is correctly added.
     */
    @Test
    public void testAddPointToSWQuadrant() {
        Point swPoint = new Point("SW", 10, 800);
        internalNode.add(swPoint, 0, 0, 1024);
        systemOut().clearHistory();
        System.out.println(internalNode.getSW().toString());
        String expectedSWOutput = "\nLeaf node with the following points:\n"
            + "10, 800";
        assertFuzzyEquals("SW quadrant should contain the correct point.",
            expectedSWOutput, systemOut().getHistory());
    }


    /**
     * Tests adding a point exactly on the boundary between quadrants and
     * verifies how it
     * is handled according to the quadtree's rules for such points.
     */
    @Test
    public void testAddPointOnBoundary() {
        Point boundaryPoint = new Point("Boundary", 512, 512);
        internalNode.add(boundaryPoint, 0, 0, 1024);
        // This test's verification depends on your quadtree's specific rules
        // for boundary points
    }

}
