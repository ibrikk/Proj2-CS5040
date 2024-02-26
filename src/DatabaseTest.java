import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * This class tests database methods
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-01-27
 */
public class DatabaseTest extends TestCase {

    private Database db;
    private String errorMessage1 = "The name must begin with a letter, "
        + "and may contain letters, digits, " + "and underscore characters.";

    /**
     * Sets up the test fixture. Called before every test case method.
     */
    @Before
    public void setUp() {
        db = new Database();
    }


    /**
     * Called after every test case method.
     */
    @After
    public void cleanUp() {
        systemOut().clearHistory();
    }


    /**
     * Tests the insert method with an invalid name that starts with a digit.
     */
    @Test
    public void testInsert1() {
        KVPair<String, Point> pair = new KVPair<>("5a_", new Point("5a_", 0,
            0));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    /**
     * Tests the insert method with an invalid name that contains a colon.
     */
    @Test
    public void testInsert2() {
        KVPair<String, Point> pair = new KVPair<>("a_:", new Point("a_:", 0,
            0));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    /**
     * Tests the insert method with a rectangle that has invalid coordinates.
     */
    @Test
    public void testInsert() {
        KVPair<String, Point> pair = new KVPair<>("A", new Point("A", -5, 0));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Point rejected: (A, -5, 0)");
    }


    /**
     * Tests the insert method with an invalid name that contains only
     * whitespace.
     */
    @Test
    public void testInsert4() {
        KVPair<String, Point> pair = new KVPair<>(" ", new Point(" ", 0, 0));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "The name must begin with a letter, "
            + "and may contain letters, digits,"
            + " and underscore characters.");
    }


    /**
     * Tests invalid insert
     */
    @Test
    public void testInsertInvalid1() {
        Point p1 = new Point("A", 0, 0);
        Point p2 = new Point("B", 99, 99);
        Point pNotValid = new Point("C", -99, -99);
        KVPair<String, Point> it1 = new KVPair<>("A", p1);
        KVPair<String, Point> it2 = new KVPair<>("B", p2);
        KVPair<String, Point> it3 = new KVPair<>("C", pNotValid);
        db.insert(it3);
        db.insert(it3);
        db.insert(it3);
        db.insert(it3);

        for (int i = 0; i < 2; i++) {
            if (i % 2 == 0) {
                db.insert(it1);
            }
            else {
                db.insert(it2);
            }
        }

        assertEquals(db.size(), 2);
    }

// /**
// * Tests the region search with a valid region search
// * whitespace.
// */
// @Test
// public void testRegionSearch() {
// KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(30, 40,
// 50, 60));
// db.insert(pair);
// systemOut().clearHistory();
// db.regionsearch(0, 0, 1024, 1024);
// String output = systemOut().getHistory();
// assertFuzzyEquals(output,
// "rectangles intersecting region 0 0 1024 1024\r\n"
// + "a 30 40 50 60");
// }

// /**
// * Tests the region search with an invalid region search
// * whitespace.
// */
// @Test
// public void testRegionSearch1() {
// KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(30, 40,
// 50, 60));
// db.insert(pair);
// systemOut().clearHistory();
// db.regionsearch(900, 5, 0, 0);
// String output = systemOut().getHistory();
// assertFuzzyEquals(output, "Rectangle rejected: (900, 5, 0, 0)");
// }
//
//
// /**
// * Tests the region search with edge cases
// * whitespace.
// */
// @Test
// public void testRegionSearch2() {
// KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(0, 0,
// 1024, 1024));
// db.insert(pair);
// systemOut().clearHistory();
// db.regionsearch(0, 0, 1024, 1024);
// String output = systemOut().getHistory();
// assertFuzzyEquals(output,
// "Rectangles intersecting region (0, 0, 1024, 1024):\r\n"
// + "(A, 0, 0, 1024, 1024)");
// }


    /**
     * Tests the insert method with a valid name containing letters and digits.
     */
    @Test
    public void testValidNameWithLettersAndDigits() {
        KVPair<String, Point> pair = new KVPair<>("Name123", new Point(
            "Name123", 10, 10));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Point inserted: (Name123, 10, 10)");
    }


    /**
     * Tests the insert method with a valid name containing an underscore.
     */
    @Test
    public void testValidNameWithUnderscore() {
        KVPair<String, Point> pair = new KVPair<>("Name_123", new Point(
            "Name_123", 10, 10));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Point inserted: (Name_123, 10, 10)");
    }


    /**
     * Tests the insert method with an invalid name that starts with a digit.
     */
    @Test
    public void testNameStartingWithDigit() {
        KVPair<String, Point> pair = new KVPair<>("1Name", new Point("1Name",
            10, 10));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    /**
     * Tests the insert method with an invalid name containing special
     * characters.
     */
    @Test
    public void testNameWithSpecialCharacters() {
        KVPair<String, Point> pair = new KVPair<>("Name#123", new Point(
            "Name#123", 10, 10));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    /**
     * Tests the insert method with an empty name.
     */
    @Test
    public void testEmptyName() {
        KVPair<String, Point> pair = new KVPair<>("", new Point("", 10, 10));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    /**
     * Tests the insert method with a null name.
     */
    @Test
    public void testNullName() {
        KVPair<String, Point> pair = new KVPair<>(null, new Point(null, 10,
            10));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertTrue(output.contains(errorMessage1));
    }


    /**
     * Tests isValidAscii method with a null string.
     */
    @Test
    public void testIsValidAsciiWithNull() {
        assertFalse(db.isValidAscii(null));
    }


    /**
     * Tests isValidAscii method with an empty string.
     */
    @Test
    public void testIsValidAsciiWithEmptyString() {
        assertFalse(db.isValidAscii(""));
    }


    /**
     * Tests isValidAscii method with a string starting with a digit.
     */
    @Test
    public void testIsValidAsciiWithDigitStart() {
        assertFalse(db.isValidAscii("1abc"));
    }


    /**
     * Tests isValidAscii method with a string starting with an underscore.
     */
    @Test
    public void testIsValidAsciiWithUnderscoreStart() {
        assertFalse(db.isValidAscii("_abc"));
    }


    /**
     * Tests isValidAscii method with a string containing special characters.
     */
    @Test
    public void testIsValidAsciiWithSpecialChars() {
        assertFalse(db.isValidAscii("abc#123"));
    }


    /**
     * Tests isValidAscii method with a string containing spaces.
     */
    @Test
    public void testIsValidAsciiWithSpaces() {
        assertFalse(db.isValidAscii("abc 123"));
    }


    /**
     * Tests isValidAscii method with a valid string.
     */
    @Test
    public void testIsValidAsciiWithValidString() {
        assertTrue(db.isValidAscii("abc_123"));
    }


    /**
     * Tests isValidAscii method with characters on the upper boundary of valid
     * ranges.
     */
    @Test
    public void testIsValidAsciiWithUpperBoundChars() {
        assertTrue(db.isValidAscii("Z"));
        assertTrue(db.isValidAscii("A"));
        assertTrue(db.isValidAscii("z"));
        assertTrue(db.isValidAscii("a"));
        assertFalse(db.isValidAscii("0"));
        assertFalse(db.isValidAscii("9"));
        assertFalse(db.isValidAscii("_"));
    }


    /**
     * Tests isValidAscii method with characters on the lower boundary of valid
     * ranges.
     */
    @Test
    public void testIsValidAsciiWithLowerBoundChars() {
        assertFalse(db.isValidAscii("["));
        assertFalse(db.isValidAscii("@"));
        assertFalse(db.isValidAscii("`"));
        assertFalse(db.isValidAscii("{"));
        assertFalse(db.isValidAscii("/"));
        assertFalse(db.isValidAscii(":"));
    }

}
