package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Takes input from the command line and prints the computed results.
 * @author Lily Blanchard
 * for CSC207-01 with Rebelsky
 */
public class QuickCalculator {
  // +--------+---------------------------------------------------------
  // | Fields |
  // +--------+

  /** A BigFraction with value zero to initialize BF variables. */
  static BigFraction zeroFrac = new BigFraction(0, 0);

  /** To store first number/fraction. */
  static BigFraction firstVal = zeroFrac;

  /** To check if first value has been found. */
  static boolean foundFirst = false;

  /** To store a register, if firstVal is from a register. */
  static char firstReg = '\0';

  /** To store second number/fraction. */
  static BigFraction secondVal = zeroFrac;

  /** To check if second value has been found. */
  static boolean foundSecond = false;

    /** To store a register, if secondVal is from a register. */
    static char secondReg = '\0';

  /** To store operand of expression. */
  static String operand = " ";

  /** To store command given. */
  static String command = "RUN";

  /** To check if a command has been found. */
  static boolean foundCommand = false;

  /** To store the refister address if STORE command is found. */
  static char register = '\0';

  /** The register where values are stored. */
  static BFRegisterSet storage = new BFRegisterSet();

  /**
   * Takes input from the command line and prints the computed results.
   *
   * @param args
   *   command line input to be computed
   *   a series of strings, each with a single instruction
   *   either compute, store or quit
   */
  public static void main(String[] args) {
    // each set of instructions is an element in args
    PrintWriter pen = new PrintWriter(System.out, true);
    BigFraction result = zeroFrac;

    // for each in args, run "IC"
    for (String inst : args) {
      String[] pieces = inst.split(" ");
      for (int i = 0; i < pieces.length; i++) {
        char action = sortArg(pieces[i]);
        if (action == 's') {
          // if action was STORE, next argument is register.
          i++;
          register = pieces[i].charAt(0);
          break;
        } else if (action == 'q') {
          // if action was QUIT, quit ASAP
          return;
        } // else
      } // for each piece

      BFCalculator calculator = new BFCalculator(firstVal); // set up the calculator

      if (command.equals("STORE")) {
        // if command is STORE, store most recently computed value
        storage.store(register, result);
        pen.printf("STORE " + register + " -> STORED\n");
      } else if (operand.equals("+")) {
        // if adding
        calculator.add(secondVal);
        result = calculator.get();
        if (firstReg != '\0') {
          pen.printf(firstReg + " ");
        } else {
          pen.printf(firstVal.toString() + " ");
        } // if firstVal is from register, print as such
        pen.printf(operand + " ");
        if (secondReg != '\0') {
          pen.printf(secondReg + " -> ");
        } else {
          pen.printf(secondVal.toString() + " -> ");
        } // if secondVal is from register, print as such
        pen.printf(result.toString() + '\n');
      } else if (operand.equals("-")) {
        // if subtracting
        calculator.subtract(secondVal);
        result = calculator.get();
        if (firstReg != '\0') {
          pen.printf(firstReg + " ");
        } else {
          pen.printf(firstVal.toString() + " ");
        } // if firstVal is from register, print as such
        pen.printf(operand + " ");
        if (secondReg != '\0') {
          pen.printf(secondReg + " -> ");
        } else {
          pen.printf(secondVal.toString() + " -> ");
        } // if secondVal is from register, print as such
        pen.printf(result.toString() + '\n');
      } else if (operand.equals("*")) {
        // if multiplying
        calculator.multiply(secondVal);
        result = calculator.get();
        if (firstReg != '\0') {
          pen.printf(firstReg + " ");
        } else {
          pen.printf(firstVal.toString() + " ");
        } // if firstVal is from register, print as such
        pen.printf(operand + " ");
        if (secondReg != '\0') {
          pen.printf(secondReg + " -> ");
        } else {
          pen.printf(secondVal.toString() + " -> ");
        } // if secondVal is from register, print as such
        pen.printf(result.toString() + '\n');
      } else if (operand.equals("/")) {
        // if dividing
        calculator.divide(secondVal);
        result = calculator.get();
        if (firstReg != '\0') {
          pen.printf(firstReg + " ");
        } else {
          pen.printf(firstVal.toString() + " ");
        } // if firstVal is from register, print as such
        pen.printf(operand + " ");
        if (secondReg != '\0') {
          pen.printf(secondReg + " -> ");
        } else {
          pen.printf(secondVal.toString() + " -> ");
        } // if secondVal is from register, print as such
        pen.printf(result.toString() + '\n');
      } // else

      // Reset values
      firstVal = zeroFrac;
      secondVal = zeroFrac;
      foundFirst = false;
      foundSecond = false;
      firstReg = '\0';
      secondReg = '\0';
      command = "RUN";
      operand = " ";
      foundCommand = false;
      register = '\n';
    } // for each instruction
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

  /**
   * Save the provided argument in the appropriate variable.
   * @param arg
   *   a token of command line input to be saved
   * @return char
   *   character corresponding to which value was saved.
   *   s: store, v: numeric value, o: operand, r: register name, e: error
   * Alters static fields.
   */
  public static char sortArg(String arg) {
    // If STORE or QUIT, save to command
    if (arg.equals("STORE")) {
      // if STORE
      command = arg;
      foundCommand = true;
      return 's';
      // main will then save the register, nothing to do here
    } else if (arg.equals("QUIT")) {
      // if QUIT
      command = arg;
      foundCommand = true;
      return 'q';
    } else if (arg.equals("*") || arg.equals("+") || arg.equals("/") || arg.equals("-")) {
      // else if is operand, save as operand
      operand = arg;
      return 'o';
    } else if (isFracStr(arg)) {
      // else, must be a numeric value
      if (!foundFirst) {
        // if first value has not been found, save there
        firstVal = new BigFraction(arg);
        foundFirst = true;
        return 'v';
      } else {
        // else save as second value
        secondVal = new BigFraction(arg);
        foundSecond = true;
        return 'v';
      } // else
    } else if (arg.length() == 1) {
      // else if arg is a register name, save value
      char ch = arg.charAt(0);
      if (!foundFirst) {
        // if first value has not been found, save there
        firstVal = storage.get(ch);
        foundFirst = true;
        firstReg = ch;
        return 'r';
      } else {
        // else save as second value
        secondVal = storage.get(ch);
        foundSecond = true;
        secondReg = ch;
        return 'r';
      } // else
    } else {
      // else must be an error
      // If we got this far, something went wrong.
      // Print error and return error key.
      System.err.print("Error: invalid argument provided. \n");
      return 'e';
    } // else
  } // sortArg(String)

  /**
   * Confirm that a given string is a string representation of a fraction or whole number.
   *   i.e. consists of only digits and has one or fewer '/'
   * @param str
   *   string to be parsed for validity
   * @return boolean
   *   whether or not the string is a valid fraction
   */
  static boolean isFracStr(String str) {
    boolean foundSlash = false;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '/') {
        // if char is a slash
        if (foundSlash) {
          // if multiple slashes have been found, return invalid
          return false;
        } else {
          // else, first slash has been found
          foundSlash = true;
        } // else
      } else if (!Character.isDigit(str.charAt(i))) {
        // if char is not a digit
        return false;
      } // else
    } // for each character in suspect string
    return true;
  } // isFracStr(String)
} // class QuickCalculator
