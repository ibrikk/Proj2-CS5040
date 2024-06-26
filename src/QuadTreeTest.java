import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * Test class for QuadTree. It tests the functionality of inserting
 * points
 * within and outside the predefined WORLDVIEW, splitting leaf nodes upon
 * reaching capacity,
 * and the behavior of adding points to empty nodes.
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class QuadTreeTest extends TestCase {
    private QuadTree tree;

    /**
     * This method is called before the execution of each test method.
     */
    @Before
    public void setUp() {
        tree = new QuadTree();
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
        assertEquals(1, quadTree.getNumOfNodes());
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
        assertEquals(0, quadTree.getNumOfNodes());
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
        QuadNode result = leafNode.add(new Point("SplitPoint",
            LeafNode.CAPACITY, LeafNode.CAPACITY), 0, 0, QuadTree.WORLDVIEW);
        assertFalse(
            "Expected a split resulting in an InternalNode "
            + "instead of a LeafNode.",
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
        assertTrue(result instanceof LeafNode);
    }


    /**
     * Tests the {@code dump()} method for an empty QuadTree, verifying that it
     * correctly
     * reports the tree as being empty.
     */
    @Test
    public void testDumpEmptyTree1() {
        systemOut().clearHistory();
        tree.dump();
        String expectedOutput =
            "QuadTree dump:\nNode at 0, 0, 1024: "
            + "Empty\n1 quadtree nodes printed";
        assertFuzzyEquals(
            "The output should correctly represent an empty QuadTree.",
            expectedOutput, systemOut().getHistory().trim());
    }


    /**
     * Tests the {@code dump()} method for a QuadTree with a single point
     * inserted,
     * ensuring that the tree structure is accurately printed.
     */
    @Test
    public void testDumpTreeWithSinglePoint1() {
        Point point = new Point("Point1", 100, 100);
        tree.insert(point);
        systemOut().clearHistory();
        tree.dump();
        String expectedOutput = "QuadTree dump:\n" + 
            "Node at 0, 0, 1024:\n"
            + "(Point1, 100, 100)\n" + "1 quadtree nodes printed";
        assertFuzzyEquals(expectedOutput, systemOut().getHistory().trim());
    }


    /**
     * Tests the dump method for an empty QuadTree, verifying that it
     * correctly identifies the tree as being empty.
     */
    @Test
    public void testDumpEmptyTree() {
        systemOut().clearHistory();
        tree.dump();
        String expectedOutput = "QuadTree dump:\nNode at 0, 0, 1024: "
            + "Empty\n1 quadtree nodes printed";
        assertEquals(
            "The output should correctly represent an empty QuadTree.",
            expectedOutput.trim(), systemOut().getHistory().trim());
    }


    /**
     * Tests the dump method for a QuadTree with a single inserted
     * point,
     * ensuring that the structure and details of the point are correctly
     * printed.
     */
    @Test
    public void testDumpTreeWithSinglePoint() {
        tree.insert(new Point("Point1", 100, 100));
        systemOut().clearHistory();
        tree.dump();

        String expectedOutputContains = "Point1, 100, 100";
        assertTrue("The dump output should include the "
            + "details of the inserted point.", systemOut().getHistory()
                .contains(expectedOutputContains));
    }


    /**
     * Tests the dump method for a QuadTree with multiple points
     * inserted,
     * verifying that the output accurately reflects the tree's structure and
     * all points.
     */
    @Test
    public void testDumpTreeWithMultiplePoints() {
        tree.insert(new Point("Point1", 100, 100));
        tree.insert(new Point("Point2", 200, 200));
        systemOut().clearHistory();
        tree.dump();

        String output = systemOut().getHistory();
        assertTrue("The dump output should include the details "
            + "of the first inserted point.", output.contains(
                "Point1, 100, 100"));
        assertTrue("The dump output should include the details "
            + "of the second inserted point.", output.contains(
                "Point2, 200, 200"));
    }


    /**
     * Tests the merge functionality of the QuadTree. Ensures that nodes are
     * merged correctly under
     * specific conditions, such as removing points that lead to a reduction in
     * the tree's complexity.
     */
    @Test
    public void testMerge() {
        tree.insert(new Point("Point1", 100, 100));
        tree.insert(new Point("Point2", 200, 200));
        tree.insert(new Point("Point3", 200, 200));
        tree.insert(new Point("Point4", 512, 512));
        tree.insert(new Point("Point5", 0, 511));
        assertTrue(((InternalNode)tree.getRoot())
            .getNW() instanceof InternalNode);
        assertTrue(((InternalNode)tree.getRoot())
            .getSE() instanceof LeafNode);

        tree.remove(200, 200);
        assertTrue(((InternalNode)tree.getRoot())
            .getSE() instanceof LeafNode);
        assertTrue(((InternalNode)tree.getRoot())
            .getNW() instanceof LeafNode);
    }


    /**
     * Verifies that inserting points into the QuadTree leads to the creation of
     * an internal node when
     * necessary. Also checks that the tree can be simplified back into a leaf
     * node upon removal of
     * points.
     */
    @Test
    public void testInsertLeadsToInternalNodeCreation() {
        // Insert points close to each other to trigger internal node creation
        Point p1 = new Point("P1", 10, 390);
        tree.insert(p1);
        tree.insert(new Point("P2", 20, 400));
        tree.insert(new Point("P3", 30, 410));
        tree.insert(new Point("P4", 40, 420));

        // Remove a point and check if the tree is simplified back to a leaf
        // node
        tree.remove(p1);
        assertTrue(((LeafNode)tree.getRoot()) instanceof LeafNode);
    }


    /**
     * Tests insertion of a point within the defined world view of the QuadTree.
     */
    @Test
    public void testInsertPointInsideWorldView() {
        Point pointInside = new Point("inside", 500, 500);
        tree.insert(pointInside);
        // Check that the tree is not empty after insertion
        assertFalse(tree.getNumOfNodes() == 0);
    }


    /**
     * Tests insertion of a point outside the defined world view, expecting the
     * QuadTree to ignore it.
     */
    @Test
    public void testInsertPointOutsideWorldView() {
        Point pointOutside = new Point("outside", 1025, 1025);
        tree.insert(pointOutside);
        assertEquals(0, tree.getNumOfNodes());
    }


    /**
     * Tests the dump functionality of a non-empty QuadTree to ensure the
     * structure is printed as expected.
     */
    @Test
    public void testDumpNonEmptyQuadTree() {
        tree.insert(new Point("test", 100, 100));
        tree.dump();
    }


    /**
     * Verifies that an existing point can be removed from the QuadTree.
     */
    @Test
    public void testRemoveExistingPoint() {
        Point point = new Point("toRemove", 100, 100);
        tree.insert(point);
        Point removedPoint = tree.remove(100, 100);
        assertNotNull("Removed point should not be null.", removedPoint);
        assertEquals("toRemove", removedPoint.getName());
    }


    /**
     * Tests the removal of a point that does not exist in the QuadTree,
     * expecting null to be returned.
     */
    @Test
    public void testRemoveNonExistingPoint() {
        Point removedPoint = tree.remove(1000, 1000);
        assertNull("Removing a non-existing point should return null.",
            removedPoint);
    }


    /**
     * Verifies that removing a point object that exists within the QuadTree
     * returns the correct point.
     */
    @Test
    public void testRemovePointObjectExisting() {
        Point point = new Point("toRemove", 200, 200);
        tree.insert(point);
        Point removedPoint = tree.remove(point);
        assertNotNull(
            "Removed point should not be null when "
            + "removing existing point object.",
            removedPoint);
    }


    /**
     * Tests removing a point object that does not exist in the QuadTree,
     * expecting null to be returned.
     */
    @Test
    public void testRemovePointObjectNonExisting() {
        Point nonExistingPoint = new Point("nonExisting", 2000, 2000);
        Point removedPoint = tree.remove(nonExistingPoint);
        assertNull("Removing a non-existing point object "
            + "should return null.",
            removedPoint);
    }

}
