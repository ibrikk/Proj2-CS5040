// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
// - I have not discussed coding details about this project with
// anyone other than the my partner, instructor, ACM/UPE tutors
// or the TAs assigned to this course.
// I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * A JUnit test class for the CommandProcessor class. This class tests the
 * processing of commands by the CommandProcessor, ensuring that it correctly
 * handles various inputs.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class CommandProcessorTest extends TestCase {

    private CommandProcessor cmdp;

    /**
     * Sets up the test fixture. Called before every test case method.
     */
    @Before
    public void setUp() {
        cmdp = new CommandProcessor();
    }


    /**
     * Tests the processor method in the CommandProcessor class with an
     * unrecognized command. Ensures that the processor method correctly
     * identifies and responds to an unrecognized command.
     */
    @Test
    public void testProcessor1() {
        String unrecognizedCommand = "delete r1 0 0 1024 1024";
        systemOut().clearHistory();
        cmdp.processor(unrecognizedCommand);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Unrecognized command.");
    }


    /**
     * Test remove by name
     */
    @Test
    public void testRemoveByName() {
        String cmd1 = "insert a 10 10";
        String cmd2 = "insert b 11 11";
        String cmd3 = "insert c 0 0";
        String cmd4 = "insert d 0 0";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("remove d");
        assertFuzzyEquals(systemOut().getHistory(),
            "Point removed: (d, 0, 0)");
    }


    /**
     * Test remove by values
     */
    @Test
    public void testRemoveByValues() {
        String cmd1 = "insert a 10 10";
        String cmd2 = "insert b 11 11";
        String cmd3 = "insert c 0 0";
        String cmd4 = "insert d 0 0";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("remove 10 10");
        assertFuzzyEquals(systemOut().getHistory(),
            "Point removed: (a, 10, 10)");
    }


    /**
     * Test remove by not valid
     */
    @Test
    public void testRemoveByValuesNotValidRectangle() {
        String cmd1 = "insert a 10 10";
        String cmd2 = "insert b 11 11";
        String cmd3 = "insert c 0 0";
        String cmd4 = "insert d 0 0";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("remove 1 1");
        assertFuzzyEquals(systemOut().getHistory(), "Point not found: (1 1)");
    }


    /**
     * Test remove by valid
     */
    @Test
    public void testRemoveByCoordinatesSuccess() {
        CommandProcessor processor = new CommandProcessor();
        processor.processor("insert TestPoint 100 100");
        systemOut().clearHistory();
        processor.processor("remove 100 100");
        assertFuzzyEquals(systemOut().getHistory(),
            "Point removed: (TestPoint, 100, 100)");

    }


    /**
     * Test remove by non existent
     */
    @Test
    public void testRemoveByCoordinatesNonExistent() {
        CommandProcessor processor = new CommandProcessor();
        systemOut().clearHistory();
        processor.processor("remove 999 999");
        assertFuzzyEquals(systemOut().getHistory(),
            "Point not found: (999, 999)");

    }


    /**
     * Test remove by boundary
     */
    @Test
    public void testRemoveByBoundaryCoordinates() {
        CommandProcessor processor = new CommandProcessor();
        processor.processor("insert BoundaryPoint 0 0");
        systemOut().clearHistory();
        processor.processor("remove 0 0");
        assertFuzzyEquals(systemOut().getHistory(),
            "Point removed: (BoundaryPoint, 0, 0)");
    }

}
