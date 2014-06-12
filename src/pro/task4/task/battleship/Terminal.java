package task.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class provides some simple methods for input/output from and to a
 * terminal.
 *
 * Never modify this class, never upload it to Praktomat. This is only for your
 * local use. If an assignment tells you to use this class for input and output
 * never use System.out or System.in in the same assignment.
 *
 * @author ITI, VeriAlg Group
 * @version 3 
 */
public final class Terminal {

    /** 
     * BufferedReader for reading from standard input line-by-line.
     */
    private static BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    
    /** 
     * Private constructor to avoid object generation. 
     */
    private Terminal() {
    }

    /**
     * Print a String to the standard output.
     *
     * The String out must not be null.
     *
     * @param out The string to be printed.
     */
    public static void printLine(String out) {
        System.out.println(out);
    }

    /**
     * Reads a line from standard input.
     *
     * Returns null at the end of the standard input.
     *
     * Use Ctrl+D to indicate the end of the standard input.
     *
     * @return The next line from the standard input or null.
     * @throws IOException If an I/O error occurs
     */
    public static String readLine() throws IOException {
        return in.readLine();
    }

}
