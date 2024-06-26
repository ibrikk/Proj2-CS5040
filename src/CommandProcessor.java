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

/**
 * The purpose of this class is to parse a single line from the command text
 * file according to the format specified in the project specs.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-03-12
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands
     * to.
     */
    public CommandProcessor() {
        data = new Database();

    }


    /**
     * This method parses keywords in the line and calls methods in the database
     * as
     * required. Each line command will be specified by one of the keywords to
     * perform the actions. These actions are performed on specified objects and
     * include insert, remove, regionsearch, search, and dump. If the command in
     * the
     * file line is not one of these, an appropriate message will be written in
     * the
     * console. This processor method is called for each line in the file. Note
     * that
     * the methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     *
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = line.split("\\s{1,}");
        String command = arr[0]; // the command will be the first of these
                                 // elements
        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace
        if (command.equals("insert")) {
            Point point = new Point(arr[1], Integer.parseInt(arr[2]), Integer
                .parseInt(arr[3]));
            KVPair<String, Point> pair = new KVPair<String, Point>(arr[1],
                point);
            data.insert(pair);

        }
        // calls the appropriate remove method based on the
        // number of white space delimited strings in the line
        else if (command.equals("remove")) {
            // checks the number of white space delimited strings in the line
            int numParam = arr.length - 1;
            if (numParam == 1) {
                // Calls remove by name
                data.remove(arr[1], false);

            }
            else if (numParam == 2) {
                // Calls remove by coordinate, converting string
                // integers into their Integer equivalent minus whitespace
                data.remove(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            }

        }
        else if (command.equals("regionsearch")) {
            data.regionSearch(Integer.parseInt(arr[1]), Integer.parseInt(
                arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
        }
        else if (command.equals("duplicates")) {
            data.duplicates();
        }
        else if (command.equals("search")) {
            // calls the search method for a name of object
            data.search(arr[1]);

        }
        else if (command.equals("dump")) {
            data.dump();
        }
        else {
            System.out.println("Unrecognized command.");
        }
    }

}
