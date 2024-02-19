//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import student.TestCase;
//
///**
// * This class tests database methods
// *
// * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
// * @version 2024-01-27
// */
//public class DatabaseTest extends TestCase {
//
//    private Database db;
//    private String errorMessage1 = "The name must begin with a letter, "
//        + "and may contain letters, digits, " + "and underscore characters.";
//
//    /**
//     * Sets up the test fixture. Called before every test case method.
//     */
//    @Before
//    public void setUp() {
//        db = new Database();
//    }
//
//
//    /**
//     * Called after every test case method.
//     */
//    @After
//    public void cleanUp() {
//        systemOut().clearHistory();
//    }
//
//
//    /**
//     * Tests the insert method with an invalid name that starts with a digit.
//     */
//    @Test
//    public void testInsert1() {
////        KVPair<String, Rectangle> pair = new KVPair<>("5a_", new Rectangle(0, 0,
////            1024, 1024));
//        systemOut().clearHistory();
////        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, errorMessage1);
//    }
//
//
//    /**
//     * Tests the insert method with an invalid name that contains a colon.
//     */
//    @Test
//    public void testInsert2() {
////        KVPair<String, Rectangle> pair = new KVPair<>("a_:", new Rectangle(0, 0,
////            1024, 1024));
//        systemOut().clearHistory();
////        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, errorMessage1);
//    }
//
//
//    /**
//     * Tests the insert method with a rectangle that has invalid coordinates.
//     */
//    @Test
//    public void testInsert() {
////        KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(-5, 0,
////            1024, 1024));
////        systemOut().clearHistory();
////        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, "Rectangle rejected: (A, -5, 0, 1024, 1024)");
//    }
//
//
//    /**
//     * Tests the insert method with an invalid name that contains only
//     * whitespace.
//     */
//    @Test
//    public void testInsert4() {
////        KVPair<String, Rectangle> pair = new KVPair<>(" ", new Rectangle(0, 0,
////            1024, 1024));
////        systemOut().clearHistory();
////        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, "The name must begin with a letter, "
//            + "and may contain letters, digits,"
//            + " and underscore characters.");
//    }
//
//
//    /**
//     * Tests invalid insert
//     */
//    @Test
//    public void testInsertInvalid1() {
//        Rectangle rec1 = new Rectangle(0, 0, 100, 100);
//        Rectangle rec2 = new Rectangle(99, 99, 924, 924);
//        Rectangle recNotValid = new Rectangle(-99, -99, 924, 924);
//        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
//        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
//        KVPair<String, Rectangle> it3 = new KVPair<>("C", recNotValid);
//        db.insert(it3);
//        db.insert(it3);
//        db.insert(it3);
//        db.insert(it3);
//
//        for (int i = 0; i < 100; i++) {
//            if (i % 2 == 0) {
//                db.insert(it1);
//            }
//            else {
//                db.insert(it2);
//            }
//        }
//
//        assertEquals(db.size(), 100);
//    }
//
//
//    /**
//     * Tests the region search with a valid region search
//     * whitespace.
//     */
//    @Test
//    public void testRegionSearch() {
//        KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(30, 40,
//            50, 60));
//        db.insert(pair);
//        systemOut().clearHistory();
//        db.regionsearch(0, 0, 1024, 1024);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output,
//            "rectangles intersecting region 0 0 1024 1024\r\n"
//                + "a 30 40 50 60");
//    }
//
//
//    /**
//     * Tests the region search with an invalid region search
//     * whitespace.
//     */
//    @Test
//    public void testRegionSearch1() {
//        KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(30, 40,
//            50, 60));
//        db.insert(pair);
//        systemOut().clearHistory();
//        db.regionsearch(900, 5, 0, 0);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, "Rectangle rejected: (900, 5, 0, 0)");
//    }
//
//
//    /**
//     * Tests the region search with edge cases
//     * whitespace.
//     */
//    @Test
//    public void testRegionSearch2() {
//        KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(0, 0,
//            1024, 1024));
//        db.insert(pair);
//        systemOut().clearHistory();
//        db.regionsearch(0, 0, 1024, 1024);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output,
//            "Rectangles intersecting region (0, 0, 1024, 1024):\r\n"
//                + "(A, 0, 0, 1024, 1024)");
//    }
//
//
//    /**
//     * Tests the insert method with a valid name containing letters and digits.
//     */
//    @Test
//    public void testValidNameWithLettersAndDigits() {
//        KVPair<String, Rectangle> pair = new KVPair<>("Name123", new Rectangle(
//            10, 10, 100, 100));
//        systemOut().clearHistory();
//        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output,
//            "Rectangle inserted: (Name123, 10, 10, 100, 100)");
//    }
//
//
//    /**
//     * Tests the insert method with a valid name containing an underscore.
//     */
//    @Test
//    public void testValidNameWithUnderscore() {
//        KVPair<String, Rectangle> pair = new KVPair<>("Name_123", new Rectangle(
//            10, 10, 100, 100));
//        systemOut().clearHistory();
//        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output,
//            "Rectangle inserted: (Name_123, 10, 10, 100, 100)");
//    }
//
//
//    /**
//     * Tests the insert method with an invalid name that starts with a digit.
//     */
//    @Test
//    public void testNameStartingWithDigit() {
//        KVPair<String, Rectangle> pair = new KVPair<>("1Name", new Rectangle(10,
//            10, 100, 100));
//        systemOut().clearHistory();
//        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, errorMessage1);
//    }
//
//
//    /**
//     * Tests the insert method with an invalid name containing special
//     * characters.
//     */
//    @Test
//    public void testNameWithSpecialCharacters() {
//        KVPair<String, Rectangle> pair = new KVPair<>("Name#123", new Rectangle(
//            10, 10, 100, 100));
//        systemOut().clearHistory();
//        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, errorMessage1);
//    }
//
//
//    /**
//     * Tests the insert method with an empty name.
//     */
//    @Test
//    public void testEmptyName() {
//        KVPair<String, Rectangle> pair = new KVPair<>("", new Rectangle(10, 10,
//            100, 100));
//        systemOut().clearHistory();
//        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertFuzzyEquals(output, errorMessage1);
//    }
//
//
//    /**
//     * Tests the insert method with a null name.
//     */
//    @Test
//    public void testNullName() {
//        KVPair<String, Rectangle> pair = new KVPair<>(null, new Rectangle(10,
//            10, 100, 100));
//        systemOut().clearHistory();
//        db.insert(pair);
//        String output = systemOut().getHistory();
//        assertTrue(output.contains(errorMessage1));
//    }
//
//
//    /**
//     * Tests isValidAscii method with a null string.
//     */
//    @Test
//    public void testIsValidAsciiWithNull() {
//        assertFalse(db.isValidAscii(null));
//    }
//
//
//    /**
//     * Tests isValidAscii method with an empty string.
//     */
//    @Test
//    public void testIsValidAsciiWithEmptyString() {
//        assertFalse(db.isValidAscii(""));
//    }
//
//
//    /**
//     * Tests isValidAscii method with a string starting with a digit.
//     */
//    @Test
//    public void testIsValidAsciiWithDigitStart() {
//        assertFalse(db.isValidAscii("1abc"));
//    }
//
//
//    /**
//     * Tests isValidAscii method with a string starting with an underscore.
//     */
//    @Test
//    public void testIsValidAsciiWithUnderscoreStart() {
//        assertFalse(db.isValidAscii("_abc"));
//    }
//
//
//    /**
//     * Tests isValidAscii method with a string containing special characters.
//     */
//    @Test
//    public void testIsValidAsciiWithSpecialChars() {
//        assertFalse(db.isValidAscii("abc#123"));
//    }
//
//
//    /**
//     * Tests isValidAscii method with a string containing spaces.
//     */
//    @Test
//    public void testIsValidAsciiWithSpaces() {
//        assertFalse(db.isValidAscii("abc 123"));
//    }
//
//
//    /**
//     * Tests isValidAscii method with a valid string.
//     */
//    @Test
//    public void testIsValidAsciiWithValidString() {
//        assertTrue(db.isValidAscii("abc_123"));
//    }
//
//
//    /**
//     * Tests isValidAscii method with characters on the upper boundary of valid
//     * ranges.
//     */
//    @Test
//    public void testIsValidAsciiWithUpperBoundChars() {
//        assertTrue(db.isValidAscii("Z"));
//        assertTrue(db.isValidAscii("A"));
//        assertTrue(db.isValidAscii("z"));
//        assertTrue(db.isValidAscii("a"));
//        assertFalse(db.isValidAscii("0"));
//        assertFalse(db.isValidAscii("9"));
//        assertFalse(db.isValidAscii("_"));
//    }
//
//
//    /**
//     * Tests isValidAscii method with characters on the lower boundary of valid
//     * ranges.
//     */
//    @Test
//    public void testIsValidAsciiWithLowerBoundChars() {
//        assertFalse(db.isValidAscii("["));
//        assertFalse(db.isValidAscii("@"));
//        assertFalse(db.isValidAscii("`"));
//        assertFalse(db.isValidAscii("{"));
//        assertFalse(db.isValidAscii("/"));
//        assertFalse(db.isValidAscii(":"));
//    }
//
//}