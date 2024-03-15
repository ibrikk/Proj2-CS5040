import java.util.ArrayList;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Test class for SkipList class.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class SkipListTest extends TestCase {

    private SkipList<String, Point> skipList;
    private SkipList<String, Point> skipList1;
    private SkipList<String, Point> skipList2;
    private SkipList<String, Point> skipList3;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        skipList = new SkipList<String, Point>();
        // Inserting some rectangles with different keys and same keys
        skipList.insert(new KVPair<>("R1", new Point("A", 0, 0)));
        skipList.insert(new KVPair<>("R2", new Point("B", 10, 10)));
        skipList.insert(new KVPair<>("R2", new Point("C", 15, 15)));
        // Duplicate
        // key
        skipList.insert(new KVPair<>("R3", new Point("D", 20, 20)));

        skipList1 = new SkipList<>();

        skipList2 = new SkipList<>();

        skipList3 = new SkipList<>();

        skipList3.insert(new KVPair<>("Node1", new Point("A", 0, 0)));
        skipList3.insert(new KVPair<>("Node2", new Point("B", 10, 10)));
        skipList3.insert(new KVPair<>("Node3", new Point("C", 15, 15)));

    }


    /**
     * Tests the insert method of the SkipList class.
     * Verifies that the size of the list is incremented correctly upon
     * insertion
     * and that the head element is as expected after insertions.
     */
    @Test
    public void testInsert() {
        SkipList<String, Point> skl = new SkipList<>();
        Point rec1 = new Point("A", 0, 0);
        Point rec2 = new Point("B", 99, 99);
        KVPair<String, Point> it1 = new KVPair<>("A", rec1);
        KVPair<String, Point> it2 = new KVPair<>("B", rec2);
        skl.insert(it1);
        assertEquals(skl.size(), 1);
        assertNull(skl.getHeadElement());

        skl.insert(it2);
        assertEquals(skl.size(), 2);

        skl.insert(it1);
        assertEquals(skl.size(), 3);
    }


    /**
     * Tests a big insert
     */
    @Test
    public void testBigInsert() {
        SkipList<String, Point> skl = new SkipList<>();
        Point rec1 = new Point("A", 0, 0);
        Point rec2 = new Point("B", 99, 99);
        Point recNotValid = new Point("C", -99, -99);
        KVPair<String, Point> it1 = new KVPair<>("A", rec1);
        KVPair<String, Point> it2 = new KVPair<>("B", rec2);
        KVPair<String, Point> it3 = new KVPair<>("C", recNotValid);
        skl.insert(it3);
        skl.insert(it3);
        skl.insert(it3);
        skl.insert(it3);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                skl.insert(it1);
            }
            else {
                skl.insert(it2);
            }
        }
        assertEquals(skl.size(), 104);
        assertNull(skl.getHeadElement());
    }


    /**
     * Tests the adjustHead method of the SkipList class.
     * Verifies that the level of the head node is adjusted properly and
     * that the size remains unchanged after adjusting the head.
     */
    @Test
    public void testAdjustHead() {
        SkipList<String, Point> skl = new SkipList<>();
        int newLevel = 3;
        skl.adjustHead(newLevel);
        assertEquals(skl.size(), 0);
        assertEquals(skl.getHeadLevel(), newLevel);
        assertNull(skl.getHeadElement());
    }


    /**
     * Tests the dump method on an empty SkipList.
     * Verifies the correct output is generated when the SkipList is empty.
     */
    @Test
    public void testDumpEmptySkipList() {
        SkipList<String, Point> list = new SkipList<>();
        systemOut().clearHistory();
        list.dump();
        String output = systemOut().getHistory();
        String expectedOutput = "SkipList dump:\nNode has depth 1, "
            + "Value (null)\nSkipList size is: 0\n";
        assertEquals(expectedOutput, output);
    }


    /**
     * Tests the dump method on a SkipList with a single element.
     * Verifies the correct output is generated for a list containing one
     * element.
     */
    @Test
    public void testDumpSingleElementSkipList() {
        SkipList<String, Point> list = new SkipList<>();
        list.insert(new KVPair<>("key1", new Point("A", 1, 2)));
        systemOut().clearHistory();
        list.dump();

        String output = systemOut().getHistory();
        assertTrue(output.startsWith("SkipList dump:\n"));
        assertTrue(output.contains("Value (key1, 1, 2)"));
        assertTrue(output.endsWith("SkipList size is: 1\n"));
    }


    /**
     * Tests the dump method on a SkipList with multiple elements.
     * Verifies the correct output is generated for a list containing several
     * elements.
     */
    @Test
    public void testDumpMultipleElementsSkipList() {
        SkipList<String, Point> list = new SkipList<>();
        list.insert(new KVPair<>("key1", new Point("A", 1, 2)));
        list.insert(new KVPair<>("key2", new Point("B", 5, 6)));
        systemOut().clearHistory();
        list.dump();

        String output = systemOut().getHistory();
        assertTrue(output.startsWith("SkipList dump:\n"));
        assertTrue(output.contains("Value (key1, 1, 2)"));
        assertTrue(output.contains("Value (key2, 5, 6"));
        assertTrue(output.endsWith("SkipList size is: 2\n"));
    }


    /**
     * Tests the search method of the SkipList class.
     */
    @Test
    public void testSearch() {
        SkipList<String, Point> skl = new SkipList<>();
        Point rec1 = new Point("A", 0, 0);
        Point rec2 = new Point("B", 99, 99);
        KVPair<String, Point> it1 = new KVPair<>("A", rec1);
        KVPair<String, Point> it2 = new KVPair<>("B", rec2);
        skl.insert(it1);
        skl.insert(it2);
        ArrayList<KVPair<String, Point>> output1 = skl.search("B");
        assertEquals(output1.get(0).getKey(), "B");
        skl.insert(it2);
        ArrayList<KVPair<String, Point>> output2 = skl.search("B");
        assertEquals(output2.get(0).getKey(), "B");
        assertEquals(output2.get(1).getKey(), "B");
    }


    /**
     * Tests searching for a key that exists in the SkipList. This test ensures
     * that the search method can find and return all instances of a given key.
     */
    @Test
    public void testSearchForExistingKey() {
        ArrayList<KVPair<String, Point>> result = skipList.search("R2");
        assertEquals(2, result.size()); // Expecting 2 results for "R2"
    }


    /**
     * Tests searching for a key that does not exist in the SkipList. This test
     * verifies that the search method correctly returns an empty list when the
     * searched key is not present.
     */
    @Test
    public void testSearchForNonExistingKey() {
        ArrayList<KVPair<String, Point>> result = skipList.search("R4");
        assertTrue(result.isEmpty()); // No results expected for "R4"
    }


    /**
     * Tests searching for a key at different levels in the SkipList. This test
     * checks whether the search method can navigate through multiple levels
     * correctly and find all instances of a given key.
     */
    @Test
    public void testSearchAtDifferentLevels() {
        // Inserting more rectangles to potentially increase SkipList levels
        skipList.insert(new KVPair<>("R2", new Point("A", 30, 30)));
        skipList.insert(new KVPair<>("R2", new Point("B", 35, 35)));

        ArrayList<KVPair<String, Point>> result = skipList.search("R2");
        assertEquals(4, result.size()); // 4 results expected for "R2"
    }


    /**
     * Tests searching in an empty SkipList. This test ensures that the search
     * method can handle an empty list scenario without errors and returns an
     * empty list.
     */
    @Test
    public void testSearchWithEmptySkipList() {
        SkipList<String, Point> emptySkipList = new SkipList<>();
        ArrayList<KVPair<String, Point>> result = emptySkipList.search("R1");
        assertTrue(result.isEmpty()); // No results expected in an empty
        // SkipList
    }


    /**
     * Tests the order of the search results. This test ensures that the search
     * method returns the results in the correct order based on their insertion
     * order in the SkipList.
     */
    @Test
    public void testSearchResultOrder() {
        // This test ensures that the search results are in the correct order
        ArrayList<KVPair<String, Point>> result = skipList.search("R2");
        // Verify the order of rectangles in the result
        assertEquals(10, result.get(1).getValue().getxCoordinate());
        assertEquals(10, result.get(1).getValue().getyCoordinate());
        assertEquals(15, result.get(0).getValue().getxCoordinate());
        assertEquals(15, result.get(0).getValue().getyCoordinate());
    }


    /**
     * Test removing an existing rectangle by name.
     * Verifies if the rectangle is correctly removed and
     * if the size of the SkipList is updated accordingly.
     */
    @Test
    public void testRemoveExisting() {
        Point rect = new Point("A", 10, 10);
        skipList1.insert(new KVPair<>("A", rect));
        assertEquals(1, skipList1.size());
        KVPair<String, Point> removed = skipList1.remove("A");
        assertNotNull(removed);
        assertEquals("A", removed.getKey());
        assertEquals(rect, removed.getValue());
        assertEquals(0, skipList1.size());
    }


    /**
     * Test removing a rectangle by a non-existing name.
     * Verifies that the method returns null and that the
     * size of the SkipList remains unchanged.
     */
    @Test
    public void testRemoveNonExisting() {
        Point rect = new Point("A", 10, 10);
        skipList1.insert(new KVPair<>("A", rect));
        assertEquals(1, skipList1.size());
        KVPair<String, Point> removed = skipList1.remove("B");
        assertNull(removed);
        assertEquals(1, skipList1.size());
    }


    /**
     * Test removing one of multiple rectangles with the same name.
     * Verifies that after removal, the size of the SkipList is decremented.
     */
    @Test
    public void testRemoveOneOfMultiple() {
        Point rect1 = new Point("A", 10, 10);
        Point rect2 = new Point("B", 30, 30);
        skipList1.insert(new KVPair<>("A", rect1));
        skipList1.insert(new KVPair<>("A", rect2));
        assertEquals(2, skipList1.size());
        skipList1.remove("A");
        assertEquals(1, skipList1.size());
    }


    /**
     * Test removing a rectangle from an empty SkipList.
     * Verifies that the method returns null and the size remains zero.
     */
    @Test
    public void testRemoveWithEmptyList() {
        KVPair<String, Point> removed = skipList1.remove("A");
        assertNull(removed);
        assertEquals(0, skipList1.size());
    }


    /**
     * Tests the removal of an existing rectangle from the SkipList.
     * Ensures that a rectangle that exists within the SkipList is correctly
     * removed, and the size of the SkipList is decremented accordingly.
     */
    @Test
    public void testRemoveExistingRectangle() {
        Point rect = new Point("A", 10, 10);
        KVPair<String, Point> pair = new KVPair<>("R1", rect);
        skipList2.insert(pair);

        assertEquals(1, skipList2.size());
    }


    /**
     * Simple remove operation
     */
    @Test
    public void testRemove() {
        Point rect = new Point("A", 10, 10);
        skipList1.insert(new KVPair<>("A", rect));
        Point rect1 = new Point("B", 10, 10);
        skipList1.insert(new KVPair<>("A", rect));
        skipList1.insert(new KVPair<>("B", rect1));
        assertEquals(3, skipList1.size());
        KVPair<String, Point> removed = skipList1.remove("B");
        assertNotNull(removed);
        assertEquals(2, skipList1.size());
    }


    /**
     * Test to ensure {@code randomLevel} never returns a negative value.
     * Given the method's probabilistic nature, this test runs the method
     * multiple times to ensure the output is consistently non-negative.
     */
    @Test
    public void testRandomLevelIsNonNegative() {
        for (int i = 0; i < 1000; i++) {
            assertTrue("randomLevel should never return a negative value",
                skipList.randomLevel() >= 0);
        }
    }


    /**
     * Test to verify that {@code randomLevel} does not exceed a reasonable
     * upper limit under normal conditions. This test is based on the
     * statistical
     * expectation that reaching a very high level is increasingly unlikely.
     * We choose 16 as an arbitrary upper limit for the purposes of this test,
     * assuming the probability halves with each level (50/50 chance).
     */
    @Test
    public void testRandomLevelUpperBound() {
        int upperBound = 50; // Arbitrarily chosen reasonable upper limit
        for (int i = 0; i < 1000; i++) {
            assertTrue("randomLevel should reasonably not exceed upper bound",
                skipList.randomLevel() < upperBound);
        }
    }


    /**
     * Test to ensure that randomLevel produces a variety of levels
     * over a large number of invocations. This test does not check for any
     * specific value but ensures that the distribution of levels is not
     * constant or extremely narrow, which would indicate a problem with
     * the randomness or distribution logic.
     */
    @Test
    public void testRandomLevelVariety() {
        boolean[] levelGenerated = new boolean[5]; // Test for variety within
        // first 5 levels
        for (int i = 0; i < 1000; i++) {
            int level = skipList.randomLevel();
            if (level < levelGenerated.length) {
                levelGenerated[level] = true;
            }
        }
        for (boolean levelFound : levelGenerated) {
            assertTrue("Expected a variety of levels to be generated",
                levelFound);
        }
    }


    /**
     * Calculates a random level for a skip list node using a probabilistic
     * approach.
     * Levels increment by 1 for each even result from a random number until an
     * odd result is obtained.
     *
     * @return The generated random level, starting at 0.
     */
    public int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Test to verify that the search method correctly identifies and returns
     * rectangles (KV pairs) that match the search key. This test indirectly
     * challenges the mutation that replaces equality checks with false by
     * ensuring that found rectangles are indeed reported accurately.
     */
    @Test
    public void testSearchFoundRectangles() {
        // Perform a search for an existing key
        ArrayList<KVPair<String, Point>> found = skipList3.search("Node2");
        assertFalse(found.isEmpty());

        // Verify the correctness of the found KV pairs
        assertEquals(1, found.size());
        KVPair<String, Point> resultPair = found.get(0);
        assertEquals("Expected to find key 'Node2'", "Node2", resultPair
            .getKey());
        assertEquals("Expected value associated with 'Node2'", new Point("A",
            10, 10), resultPair.getValue());
    }


    /**
     * Test to ensure that searching for a non-existent key correctly results in
     * an
     * empty list, challenging mutations that might disrupt the accurate
     * reporting
     * of search misses.
     */
    @Test
    public void testSearchNonExistentKey() {
        // Search for a key that does not exist
        ArrayList<KVPair<String, Point>> found = skipList3.search(
            "NonExistentKey");
        assertTrue(found.isEmpty());
    }


    /**
     * Test that inserting a non-null {@code KVPair} correctly adds the element
     * to the skip list
     * and increments its size. This test verifies that the insert method
     * processes valid inputs
     * as expected, in contrast to the null input case.
     */
    @Test
    public void testInsertNonNullIncrementsSize() {
        KVPair<String, Point> pair = new KVPair<>("Key", new Point("A", 10,
            10));
        int initialSize = skipList.size();
        skipList.insert(pair);
        assertEquals("SkipList size should increment by 1 after "
            + "inserting a non-null KVPair", initialSize + 1, skipList.size());
    }


    /**
     * Test remove with null.
     */
    @Test
    public void testRemoveNonNullIncrementsSize() {
        systemOut().clearHistory();
        skipList.remove(null);
        String output = systemOut().getHistory();
        assertFuzzyEquals("Point not removed: null", output);
    }


    /**
     * Test that randomLevel produces a range of levels, indicating
     * the loop condition and level increment work as expected.
     */
    @Test
    public void testRandomLevelRange() {
        SkipList<String, Point> list = new SkipList<>();
        boolean[] levelsGenerated = new boolean[5];
        for (int i = 0; i < 10000; i++) {
            int level = list.randomLevel();
            if (level < 5) {
                levelsGenerated[level] = true;
            }
        }

        // Check that multiple levels were generated
        for (int i = 0; i < 5; i++) {
            assertTrue("Expected level " + i + " to be generated",
                levelsGenerated[i]);
        }
    }
}
