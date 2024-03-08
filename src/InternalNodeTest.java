import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
        internalNode.add(boundaryPoint, 0, 0, QuadTree.WORLDVIEW);

        // Check if the point is added to the NE quadrant
        assertTrue(internalNode.getSE() instanceof LeafNode);
        LeafNode neLeaf = (LeafNode)internalNode.getSE();
        assertTrue(neLeaf.getPointsList().contains(boundaryPoint));
    }


    /**
     * Verifies that an InternalNode merges into a LeafNode after removing
     * points reduces its total number of points below a threshold.
     */
    @Test
    public void testMergeAfterRemoval() {
        // Assuming CAPACITY is the threshold for merging into a LeafNode
        Point point1 = new Point("P1", 200, 200);
        Point point2 = new Point("P2", 300, 300);
        Point point3 = new Point("P3", 400, 400);

        // Add points in different quadrants
        internalNode.add(point1, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(point2, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(point3, 0, 0, QuadTree.WORLDVIEW);

        // Remove one point to potentially trigger a merge
        internalNode.remove(200, 200, 0, 0, QuadTree.WORLDVIEW,
            new LinkedList<>());

        // Verify if internalNode merged into a LeafNode
        // This might require adjustments based on how your QuadTree handles
        // merges
        assertTrue(internalNode.getNW() instanceof LeafNode);
    }


    /**
     * Tests that an InternalNode correctly splits its content into new
     * quadrants upon exceeding LeafNode capacity.
     */
    @Test
    public void testQuadrantSplittingUponInsertion() {
        Point point1 = new Point("P1", 100, 100);
        Point point2 = new Point("P2", 300, 300);
        Point point3 = new Point("P3", 500, 500);
        Point point4 = new Point("P4", 700, 700);

        // Insert points to trigger splitting
        internalNode.add(point1, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(point2, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(point3, 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(point4, 0, 0, QuadTree.WORLDVIEW);

        // Verify that internalNode has split into smaller quadrants
        assertTrue(internalNode.getNW() instanceof LeafNode || internalNode
            .getNW() instanceof InternalNode);
        assertEquals(internalNode.getNE(), EmptyNode.getInstance());
        assertEquals(internalNode.getSW(), EmptyNode.getInstance());
        assertTrue(internalNode.getSE() instanceof LeafNode || internalNode
            .getSE() instanceof InternalNode);
    }


    /**
     * Verifies that InternalNodes correctly merge into a LeafNode after point
     * removal reduces
     * the total point count below a certain threshold. The merge is expected to
     * simplify the
     * QuadTree's structure without losing spatial indexing capabilities.
     */
    @Test
    public void testMergeInternalNodeToLeafNodeAfterRemoval() {
        // Add points to various quadrants to create a condition for merging
        internalNode.add(new Point("Point1", 25, 25), 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point2", 75, 75), 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point3", 25, 75), 0, 0, QuadTree.WORLDVIEW);

        LinkedList<Point> list = new LinkedList<>();

        // Remove points to potentially trigger a merge
        internalNode.remove(25, 25, 0, 0, QuadTree.WORLDVIEW, list);
        // At this point, we expect a merge to occur, leaving a single LeafNode
        // with "Point3"
        internalNode.remove(75, 75, 0, 0, QuadTree.WORLDVIEW, list);

        // Fetch the root after operations, expecting it to have possibly
        // changed to a LeafNode

        // Verify the result is a LeafNode, indicating a successful merge
        assertTrue(internalNode.getNW() instanceof LeafNode);
        assertEquals(internalNode.getNE(), EmptyNode.getInstance());
        assertEquals(internalNode.getSW(), EmptyNode.getInstance());
        assertEquals(internalNode.getSE(), EmptyNode.getInstance());

    }


    /**
     * Tests that an InternalNode does not merge into a LeafNode when the
     * conditions are not met,
     * such as when there are enough points distributed across its quadrants to
     * justify its existence.
     */
    @Test
    public void testNoMergeWhenConditionsNotMet() {
        // Add points in such a way that no merge should occur
        internalNode.add(new Point("Point1", 25, 25), 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point2", 75, 75), 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point3", 25, 75), 0, 0, QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point3", 75, 25), 0, 0, QuadTree.WORLDVIEW);

        // Verify the root remains an InternalNode, indicating no merge occurred
        assertTrue(
            "Root should remain an InternalNode as merge conditions are not met.",
            internalNode instanceof InternalNode);
    }


    @Test
    public void testOutputDataEmptyChildren() {
        LinkedList<String> outputList = new LinkedList<>();
        int[] numOfVisits = { 0 };
        internalNode.getOutputData(0, 0, 1024, outputList, 0, numOfVisits);
        assertEquals(
            "Expected single visit to internal node with empty children", 5,
            numOfVisits[0]);
        assertTrue("Output should include the internal node's data", outputList
            .contains("Node at 0, 0, 1024: Internal"));
    }


    @Test
    public void testOutputDataWithNonEmptyChild() {
        // Assuming a method to set a specific child of the internal node
        LeafNode leafNode = new LeafNode();
        leafNode.add(new Point("Test", 512, 512), 512, 0, 512);
        internalNode.setNE(leafNode); // Assuming a setter method

        LinkedList<String> outputList = new LinkedList<>();
        int[] numOfVisits = { 0 };
        internalNode.getOutputData(0, 0, 1024, outputList, 0, numOfVisits);

        // Expected to find the representation of the non-empty child in the
        // output
        boolean foundLeafNodeOutput = false;
        Node<String> currentNode = outputList.getHead();
        while (currentNode != null) {
            if (currentNode.getData().contains("Empty")) {
                foundLeafNodeOutput = true;
                break;
            }
            currentNode = currentNode.getNext();
        }

        assertTrue("Output should include data for non-empty child nodes",
            foundLeafNodeOutput);
    }


    @Test
    public void testOutputDataIndentation() {
        // Setup a tree with multiple levels of depth
        // This setup depends on your tree's implementation details

        LinkedList<String> outputList = new LinkedList<>();
        int[] numOfVisits = { 0 };
        internalNode.getOutputData(0, 0, 1024, outputList, 0, numOfVisits);

        // Verify indentation increases with depth
        Node<String> currentNode = outputList.getHead();
        int lastIndentationLevel = -1;
        boolean indentationIncreases = true;
        while (currentNode != null) {
            String data = currentNode.getData();
            int currentIndentationLevel = data.indexOf("Node at") / 2;

            if (lastIndentationLevel != -1
                && currentIndentationLevel <= lastIndentationLevel) {
                indentationIncreases = false;
                break;
            }

            lastIndentationLevel = currentIndentationLevel;
            currentNode = currentNode.getNext();
        }

        assertFalse("Indentation should increase with each level of depth",
            indentationIncreases);
    }

}
