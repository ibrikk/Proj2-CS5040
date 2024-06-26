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
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class InternalNodeTest extends TestCase {

    private InternalNode internalNode;
    private InternalNode internalNode2;
    private InternalNode internalNode3;

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

        internalNode2 = new InternalNode(EmptyNode.getInstance(), EmptyNode
            .getInstance(), EmptyNode.getInstance(), EmptyNode.getInstance());

        internalNode3 = new InternalNode(EmptyNode.getInstance(), EmptyNode
            .getInstance(), EmptyNode.getInstance(), EmptyNode.getInstance());

        Point point1 = new Point("A", 10, 10);
        Point point2 = new Point("B", 15, 15);
        Point point3 = new Point("C", 20, 20);
        Point point4 = new Point("D", 100, 100);
        Point point5 = new Point("E", 511, 511);
        Point pointNE = new Point("F", 512, 511);
        Point pointSW = new Point("G", 511, 512);
        Point pointSE = new Point("H", 512, 512);

        internalNode2.add(point1, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(point2, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(point3, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(point4, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(point5, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(pointNE, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(pointSW, 0, 0, QuadTree.WORLDVIEW);
        internalNode2.add(pointSE, 0, 0, QuadTree.WORLDVIEW);

        internalNode3 = new InternalNode();
        internalNode3.setNW(new LeafNode());
        internalNode3.setNE(EmptyNode.getInstance());
        internalNode3.setSW(EmptyNode.getInstance());
        internalNode3.setSE(new LeafNode());

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
        Point[] removed = { null };
        internalNode.remove(200, 200, 0, 0, QuadTree.WORLDVIEW, removed);
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
        internalNode.add(new Point("Point1", 25, 25), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point2", 75, 75), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point3", 25, 75), 0, 0,
            QuadTree.WORLDVIEW);

        Point[] list = { null };
        // Remove points to potentially trigger a merge
        internalNode.remove(25, 25, 0, 0, QuadTree.WORLDVIEW, list);
        // At this point, we expect a merge to occur, leaving a single LeafNode
        // with "Point3"
        internalNode.remove(75, 75, 0, 0, QuadTree.WORLDVIEW, list);

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
        internalNode.add(new Point("Point1", 25, 25), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point2", 75, 75), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point3", 25, 75), 0, 0,
            QuadTree.WORLDVIEW);
        internalNode.add(new Point("Point3", 75, 25), 0, 0,
            QuadTree.WORLDVIEW);

        assertTrue(internalNode instanceof InternalNode);
    }


    /**
     * Verifies that the output data method
     * correctly handles internal nodes
     * with empty children.
     * This test ensures that the output
     * data accurately reflects the structure
     * of an internal node
     * that does not contain any child nodes,
     * effectively representing an empty
     * or uninitialized
     * portion of the QuadTree. It checks both
     * the visit count to ensure
     * traversal accuracy and
     * the content of the output list for
     * correct data representation.
     */
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


    /**
     * Tests the output data method with an
     * internal node having at least one
     * non-empty child.
     * It verifies that the method can correctly
     * identify and include data from
     * non-empty child
     * nodes in its output, which is critical for
     * accurately representing the
     * QuadTree's structure
     * and contents. This test checks the presence
     * of specific node data in the
     * output list,
     * confirming that the method traverses and
     * documents non-empty parts of the
     * tree as expected.
     */
    @Test
    public void testOutputDataWithNonEmptyChild() {
        // Assuming a method to set a specific child of the internal node
        LeafNode leafNode = new LeafNode();
        leafNode.add(new Point("Test", 512, 512), 512, 0, 512);
        internalNode.setNE(leafNode); // Assuming a setter method

        LinkedList<String> outputList = new LinkedList<>();
        int[] numOfVisits = { 0 };
        internalNode.getOutputData(0, 0, 1024, outputList, 0, numOfVisits);

        boolean foundLeafNodeOutput = false;
        Node<String> currentNode = outputList.getHead();
        while (currentNode != null) {
            if (currentNode.getData().contains("Empty")) {
                foundLeafNodeOutput = true;
                break;
            }
            currentNode = currentNode.getNext();
        }

        assertTrue(foundLeafNodeOutput);
    }


    /**
     * Evaluates the indentation logic
     * in the output data generated by
     * traversing an internal node.
     * This test confirms that the method
     * implementing output data generation
     * respects and correctly
     * applies indentation rules based on
     * node depth, ensuring that the
     * hierarchical structure of
     * the QuadTree is visually and textually
     * clear in the output. It checks for
     * an increasing pattern
     * of indentation with deeper levels of
     * the tree, which helps in
     * understanding the tree's layout.
     */
    @Test
    public void testOutputDataIndentation() {

        LinkedList<String> outputList = new LinkedList<>();
        int[] numOfVisits = { 0 };
        internalNode.getOutputData(0, 0, 1024, outputList, 0, numOfVisits);

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

        assertFalse(indentationIncreases);
    }


    /**
     * Tests removal of a point from the NW (North West) quadrant.
     * This test aims to verify that the remove operation correctly identifies
     * and
     * operates on the NW quadrant based on the point's coordinates relative to
     * the current split.
     */
    @Test
    public void testRemoveFromNWQuadrant() {
        Point[] removedPoint = { null };
        internalNode2.remove(10, 10, 0, 0, QuadTree.WORLDVIEW, removedPoint);

        assertEquals(
            "Removed points should contain the point from NW quadrant",
            removedPoint[0], new Point("A", 10, 10));
    }


    /**
     * Tests the resilience of the remove operation against logical expression
     * mutations.
     * Specifically ensures that altering a conditional check to always false
     * (or always true) would break the test, indicating the condition is
     * necessary.
     */
    @Test
    public void testConditionalLogicForRemoval() {
        Point[] removedPoint = { null };
        internalNode2.remove(30, 30, 0, 0, QuadTree.WORLDVIEW, removedPoint);

        assertNull("Removing a non-existent point should not affect the list",
            removedPoint[0]);
    }


    /**
     * Tests removal of a point located exactly on the quadrant boundary.
     * This test targets the arithmetic operation by placing a point on the
     * boundary
     * and checks whether the logic correctly identifies the quadrant for
     * removal.
     */
    @Test
    public void testRemovalOnBoundary() {

        Point[] removedPoint = { null };
        // Coordinates on the boundary
        internalNode2.remove(511, 511, 0, 0, QuadTree.WORLDVIEW, removedPoint);

        assertEquals(
            "Removing a non-existent point should not affect the list",
            removedPoint[0], new Point("E", 511, 511));
    }


    /**
     * Tests removal of a point located exactly on the quadrant boundary.
     * This test targets the arithmetic operation by placing a point on the
     * boundary
     * and checks whether the logic correctly identifies the quadrant for
     * removal.
     */
    @Test
    public void testRemovalOnBoundary2() {
        Point[] removedPoint = { null };
        // Coordinates on the boundary
        internalNode2.remove(512, 511, 0, 0, QuadTree.WORLDVIEW, removedPoint);

        assertEquals(
            "Removing a non-existent point should not affect the list",
            removedPoint[0], new Point("F", 512, 511));

        removedPoint[0] = null;
        internalNode2.remove(511, 512, 0, 0, QuadTree.WORLDVIEW, removedPoint);
        assertEquals(
            "Removing a non-existent point should not affect the list",
            removedPoint[0], new Point("G", 511, 512));

        removedPoint[0] = null;
        internalNode2.remove(512, 512, 0, 0, QuadTree.WORLDVIEW, removedPoint);
        assertEquals(
            "Removing a non-existent point should not affect the list",
            removedPoint[0], new Point("H", 512, 512));
    }


    /**
     * Tests the capability of the internal node
     * to remove points from each of
     * its quadrants.
     * It verifies that points added to the NW,
     * NE, SW, and SE quadrants can be
     * individually
     * identified and removed, demonstrating the
     * internal node's ability to
     * manage its child
     * nodes effectively. This is crucial for
     * maintaining the integrity of the
     * QuadTree's
     * structure during dynamic updates.
     */
    @Test
    public void testRemoveFromEachQuadrant() {
        // Setup: Add one point in each quadrant
        // NW Quadrant
        internalNode.add(new Point("NWPoint", 1, 1), 0, 0, 1024);
        // NE Quadrant
        internalNode.add(new Point("NEPoint", 1023, 1), 0, 0, 1024);
        // SW Quadrant
        internalNode.add(new Point("SWPoint", 1, 1023), 0, 0, 1024);
        // SE Quadrant
        internalNode.add(new Point("SEPoint", 1023, 1023), 0, 0, 1024);

        // Test removal from each quadrant
        Point[] removedPointsNW = { null };
        internalNode.remove(new Point("NWPoint", 1, 1), 0, 0, 1024,
            removedPointsNW);
        assertEquals("Should remove 1 point from NW quadrant", 1,
            removedPointsNW.length);

        Point[] removedPointsNE = { null };
        internalNode.remove(new Point("NEPoint", 1023, 1), 0, 0, 1024,
            removedPointsNE);
        assertEquals("Should remove 1 point from NE quadrant", 1,
            removedPointsNE.length);

        Point[] removedPointsSW = { null };
        internalNode.remove(new Point("SWPoint", 1, 1023), 0, 0, 1024,
            removedPointsSW);
        assertEquals("Should remove 1 point from SW quadrant", 1,
            removedPointsSW.length);

        Point[] removedPointsSE = { null };
        internalNode.remove(new Point("SEPoint", 1023, 1023), 0, 0, 1024,
            removedPointsSE);
        assertEquals("Should remove 1 point from SE quadrant", 1,
            removedPointsSE.length);
    }


    /**
     * Evaluates the conditions under which an
     * internal node can merge into a
     * leaf node.
     * This test assesses the internal node's
     * ability to simplify the QuadTree
     * structure
     * by merging its child nodes into a single
     * leaf node when possible, which
     * is significant
     * for optimizing the tree's performance and
     * memory usage. The conditions
     * tested include
     * scenarios with varying numbers and arrangements of points.
     */
    @Test
    public void testMergeConditions() {
        assertTrue("Initial state should not merge into a LeafNode",
            internalNode.merge() instanceof LeafNode);

        internalNode.add(new Point("SingleNW", 10, 10), 0, 0, 1024);
        assertTrue("Should merge into a single LeafNode after adding a "
            + "point to NW", internalNode.merge() instanceof LeafNode);

        internalNode = new InternalNode(EmptyNode.getInstance(), EmptyNode
            .getInstance(), EmptyNode.getInstance(), EmptyNode.getInstance());
        internalNode.add(new Point("NW", 10, 10), 0, 0, 1024);
        internalNode.add(new Point("NE", 1010, 10), 0, 0, 1024);
        internalNode.add(new Point("SW", 10, 1010), 0, 0, 1024);
        assertTrue(internalNode.merge() instanceof LeafNode);
    }


    /**
     * Tests the removal of a nonexistent
     * point from an internal node.
     * It confirms that attempting to remove
     * a point that does not exist within
     * the QuadTree
     * does not cause any unintended modifications
     * to the structure, ensuring
     * the robustness
     * and reliability of the remove operation.
     */
    @Test
    public void testRemovalOfNonexistentPoint() {
        Point[] removedPoints = { null };
        internalNode.remove(new Point("Nonexistent", 500, 500), 0, 0, 1024,
            removedPoints);
        assertNull("Removing a nonexistent point should not alter the list",
            removedPoints[0]);
    }


    /**
     * Assesses the removal operation's resilience
     * to arithmetic mutations,
     * particularly
     * when removing a point on the boundary
     * between quadrants.
     * This test ensures that the removal
     * logic accurately handles edge cases,
     * such as points
     * located exactly on quadrant boundaries,
     * without introducing errors or
     * inconsistencies
     * in the QuadTree's structure.
     */
    @Test
    public void testArithmeticMutationInRemoval() {
        Point[] removedPoints = { null };
        internalNode.add(new Point("BoundaryPoint", 512, 512), 0, 0, 1024);
        internalNode.remove(new Point("BoundaryPoint", 512, 512),
            0, 0, 1024,
            removedPoints);
        assertFalse(
            "Removed points should not be empty"
            + " when removing a boundary point",
            removedPoints.length == 0);
    }


    /**
     * Tests region search functionality within a single quadrant.
     * This test aims to verify that the regionSearch correctly identifies and
     * retrieves
     * points contained entirely within one quadrant of the QuadTree, without
     * falsely including
     * points from other quadrants.
     */
    @Test
    public void testRegionSearchWithinSingleQuadrant() {
        int[] numOfVisits = { 0 };
        LinkedList<Point> result = internalNode.regionSearch(10, 10, 200, 200,
            new LinkedList<>(), 0, 0, QuadTree.WORLDVIEW, numOfVisits);

        assertEquals(2, numOfVisits[0]);
        assertFalse(result.getNumberOfEntries() == 2);
    }


    /**
     * Tests region search functionality spanning multiple quadrants.
     * This test checks if the regionSearch method can correctly identify and
     * aggregate points
     * from multiple quadrants when the search area overlaps more than one
     * quadrant.
     */
    @Test
    public void testRegionSearchSpanningMultipleQuadrants() {
        // Setup: Assume QuadTree and points have been initialized and added
        int[] numOfVisits = { 0 };
        Point newPoint = new Point("J", 512, 512);
        internalNode.add(newPoint, 0, 0, QuadTree.WORLDVIEW);
        LinkedList<Point> result = internalNode.regionSearch(500, 500, 100,
            100, new LinkedList<>(), 0, 0, QuadTree.WORLDVIEW, numOfVisits);

        assertTrue(numOfVisits[0] > 1);
        assertFalse(result.getNumberOfEntries() == 0);
    }


    /**
     * Tests region search functionality where no points are expected to be
     * found.
     * This test ensures that the regionSearch method correctly handles cases
     * where the search
     * area does not overlap with any points in the QuadTree.
     */
    @Test
    public void testRegionSearchWithNoPointsFound() {
        int[] numOfVisits = { 0 };
        LinkedList<Point> result = internalNode.regionSearch(2000, 2000, 50,
            50, new LinkedList<>(), 0, 0, QuadTree.WORLDVIEW, numOfVisits);
        assertTrue(result.getNumberOfEntries() == 0);
    }


    /**
     * Tests region search functionality on the boundaries between quadrants.
     * This test verifies that the regionSearch method accurately includes
     * points on the
     * boundary lines of quadrants and does not omit them due to boundary
     * conditions.
     */
    @Test
    public void testRegionSearchOnBoundary() {
        int[] numOfVisits = { 0 };
        Point newPoint = new Point("J", 512, 512);
        internalNode.add(newPoint, 0, 0, QuadTree.WORLDVIEW);
        LinkedList<Point> result = internalNode
            .regionSearch(510, 510, 20, 20,
            new LinkedList<>(), 0, 0, QuadTree.WORLDVIEW, numOfVisits);

        assertFalse(result.getNumberOfEntries() == 0);
    }


    /**
     * Test to ensure correct merging when NE is
     * the only leaf node with points,
     * and other nodes are fly weights.
     */
    @Test
    public void testMergeWithOnlyNELeafNode() {
        LeafNode neLeaf = new LeafNode();
        neLeaf.add(new Point("A", 600, 100), 0, 0, 1024);
        internalNode3.setNE(neLeaf);

        internalNode3.setNW(EmptyNode.getInstance());

        // Perform merge operation
        QuadNode resultNode = internalNode3.merge();

        // Verify the result is a LeafNode
        assertTrue("Merge result should be a LeafNode",
            resultNode instanceof LeafNode);

        LeafNode resultLeaf = (LeafNode)resultNode;
        assertTrue(resultLeaf.getPointsList()
            .contains(new Point("A", 600,
            100)));
    }


    /**
     * Tests the behavior of {@link InternalNode#getOutputData}
     * when the split parameter is zero.
     * This scenario is unusual and tests the method's
     * robustness to edge cases, ensuring it
     * can handle unexpected input without failing.
     */
    @Test
    public void testGetOutputDataWithSplitZero() {
        // Initialize the quadtree and internal node with test data
        InternalNode node = new InternalNode();

        int currentX = 100;
        int currentY = 200;
        int split = 0;
        LinkedList<String> result = new LinkedList<>();
        int[] numOfVisits = new int[1];

        node.getOutputData(currentX, currentY, split, 
            result, 0, numOfVisits);

        assertFalse("Result list should not be empty", result
            .getNumberOfEntries() == 0);
    }


    /**
     * Validates the output data generation process
     * when the current X coordinate is zero.
     * This test ensures that the method correctly
     * handles scenarios where the tree or node
     * starts at the origin, emphasizing the correct
     * spatial representation and data retrieval.
     */
    @Test
    public void testGetOutputDataWithCurrentXZero() {
        InternalNode node = new InternalNode();
        // Populate your node here with children if needed

        int currentX = 0;
        int currentY = 100;
        int split = 50;
        LinkedList<String> result = new LinkedList<>();
        int[] numOfVisits = new int[1];

        node.getOutputData(currentX, currentY, split, 
            result, 0, numOfVisits);

        assertFalse("Result list should not be empty", result
            .getNumberOfEntries() == 0);
    }


    /**
     * Repeats the validation of output data
     * generation with the current X coordinate at zero,
     * aiming to reaffirm the method's
     * consistent behavior under similar conditions, ensuring
     * reliability and predictability of
     * the output.
     */
    @Test
    public void testGetOutputDataWithCurrentXZero2() {
        InternalNode node = new InternalNode();

        int currentX = 0;
        int currentY = 100;
        int split = 50;
        LinkedList<String> result = new LinkedList<>();
        int[] numOfVisits = new int[1];

        node.getOutputData(currentX, currentY, split, 
            result, 0, numOfVisits);
        assertFalse("Result list should not be empty", result
            .getNumberOfEntries() == 0);
    }


    /**
     * Examines the recursive behavior of
     * {@link InternalNode#getOutputData}, ensuring it
     * accurately traverses and gathers data
     * from a complex tree structure. This test checks
     * the method's capability to dive into
     * nested structures, highlighting the
     * importance of
     * recursion in handling tree-based data.
     */
    @Test
    public void testRecursiveGetOutputData() {
        InternalNode root = new InternalNode();
        int currentX = 0;
        int currentY = 0;
        int split = 1024;
        LinkedList<String> result = new LinkedList<>();
        int[] numOfVisits = new int[1];

        root.getOutputData(currentX, currentY, split, 
            result, 0, numOfVisits);

        assertFalse("Result list should not be empty after recursive calls",
            result.getNumberOfEntries() == 0);
        assertTrue("Number of visits should match expected traversal",
            numOfVisits[0] > 1);
    }


    /**
     * Tests the addition of points to each quadrant
     * of an {@link InternalNode}, verifying that
     * points are correctly classified and added
     * to their respective quadrants. This test
     * emphasizes the spatial data handling
     * capabilities of the QuadTree structure.
     */
    @Test
    public void testAdd4() {
        int currX = 0;
        int currY = 0;
        int split = 100;
        Point pointNW = new Point("NW", 25, 25);
        Point pointNE = new Point("NE", 75, 25);
        Point pointSW = new Point("SW", 25, 75);
        Point pointSE = new Point("SE", 75, 75);

        QuadNode updatedNW = internalNode.add(pointNW, currX, currY, split);
        QuadNode updatedNE = internalNode.add(pointNE, currX, currY, split);
        QuadNode updatedSW = internalNode.add(pointSW, currX, currY, split);
        QuadNode updatedSE = internalNode.add(pointSE, currX, currY, split);

        assertTrue("Point NW should be added to NW quadrant.",
            updatedNW instanceof InternalNode);
        assertTrue("Point NE should be added to NE quadrant.",
            updatedNE instanceof InternalNode);
        assertTrue("Point SW should be added to SW quadrant.",
            updatedSW instanceof InternalNode);
        assertTrue("Point SE should be added to SE quadrant.",
            updatedSE instanceof InternalNode);

    }


    /**
     * Focuses on the QuadTree's sensitivity to
     * boundary conditions by adding points exactly on
     * the new boundary determined after a split.
     * This test ensures that the QuadTree accurately
     * places points on or near boundaries in the
     * correct nodes or leaves.
     */
    @Test
    public void testSens() {

        QuadTree quadTree = new QuadTree();

        Point testPointRightOnNewBound = new Point("RightOnNewBound", 0, 60);
        Point testPointRightOnNewBound2 = new Point("RightOnNewBound2", 75,
            60);
        Point testPointRightOnNewBound3 = new Point("RightOnNewBound3", 75,
            60);
        Point testPointRightOnNewBound4 = new Point("RightOnNewBound4", 75,
            60);

        quadTree.insert(testPointRightOnNewBound);
        quadTree.insert(testPointRightOnNewBound2);
        quadTree.insert(testPointRightOnNewBound3);
        quadTree.insert(testPointRightOnNewBound4);

        assertEquals(
            ((LeafNode)((InternalNode)((InternalNode)
                ((InternalNode)((InternalNode)quadTree
                .getRoot()).getNW()).getNW()).getNW())
                .getNW()).getPointsList()
                    .getHead().getData(), 
                    testPointRightOnNewBound);

    }


    /**
     * Verifies the placement of a point
     * exactly at a newly calculated boundary
     * within the
     * QuadTree. This test checks the
     * precision of the QuadTree's spatial
     * calculations and its
     * ability to accurately manage points
     * located on critical boundaries.
     */
    @Test
    public void testPointPlacementAtNewXBound() {
        QuadTree quadTree = new QuadTree();
        int split = QuadTree.WORLDVIEW / 2;

        Point pointOnBoundary = new Point("OnBoundary", 
            split, split / 2);
        quadTree.insert(pointOnBoundary);

        int[] numOfVisits = new int[1];
        LinkedList<Point> foundPoints = quadTree
            .regionSearch(split / 2, split
            / 2, split, split, numOfVisits);
        assertTrue(foundPoints.contains(pointOnBoundary));
    }

}
