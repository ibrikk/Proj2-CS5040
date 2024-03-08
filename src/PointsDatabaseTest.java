import java.io.IOException;

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
 * Test class for SkipListProject class.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-01-27
 */
public class PointsDatabaseTest extends TestCase {

    /**
     * Setup tests
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Tests the main method of SkipListProject.
     * It uses different input files to simulate various scenarios and compares
     * the output of the program against the expected output.
     */
    public void testMain() {
//        String[] args = { "duplicates1.txt" };
// String[] args = { "test.txt" };
 String[] args = { "SyntaxTest2.txt" };
// String[] args = { "BadPoint.txt" };
        systemOut().clearHistory();
        PointsDatabase.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = null;
        try {
//            referenceOutput = PointsDatabase.readFile("duplicates1Out.txt");
// referenceOutput = PointsDatabase.readFile("testOut.txt");
 referenceOutput = PointsDatabase.readFile("SyntaxTest2Out.txt");
// referenceOutput = PointsDatabase.readFile("BadPointOut.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        assertFuzzyEquals(referenceOutput, output);
    }
}
