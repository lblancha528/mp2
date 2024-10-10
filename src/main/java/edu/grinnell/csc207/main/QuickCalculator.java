package edu.grinnell.csc207.main;

import java.io.PrintWriter;

/**
 * Takes input from the command line and prints the computed results.
 * @author Lily Blanchard
 * for CSC207-01 with Rebelsky
 */
public class QuickCalculator {
  /**
   * Takes input from the command line and prints the computed results.
   *
   * @param args
   *   command line input to be computed
   */
  public static void main(String[] args) {
    String[][] sets = new String[args.length][];
    PrintWriter pen = new PrintWriter(System.out, true);
    for (int i = 0; i < args.length; i++) {
      sets[i] = stringToArray(args[i]);
    } // for

    // BigFraction first = new BigFraction(args[0], 1); // string is
    for (int i = 0; i < args.length; i++) {
      InteractiveCalculator.main(sets[i]);
      pen.println("Sent to IC");
    } // for
    return;
  } // main()

  /**
   * Turn a string into an array where each element is a token in the string.
   * @param str
   *   the string to be separated into an array
   * @return arr
   *   the completed array
   */
  public static String[] stringToArray(String str) {
    String[] strArray = new String[3];
    int fillAt = 0;
    int lastSpace = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ' ') {
        // if there is a space, save the next element
        strArray[fillAt] = str.substring(lastSpace, i);
        lastSpace = i + 1;
        fillAt += 1;
      } // if
    } // for
    return strArray;
  } // stringToArray(String)
} // class QuickCalculator
