package edu.grinnell.csc207;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;

/**
 * Continuously takes input from the user and prints the computed results.
 */
public class InteractiveCalculator {
  // +--------+---------------------------------------------------------
  // | Fields |
  // +--------+

  /** A BigFraction with value zero to initialize BF variables. */
  static BigFraction zeroFrac = new BigFraction(0, 0);

  /** To store first number/fraction. */
  static BigFraction firstVal = zeroFrac;

  /** To check if first value has been found. */
  static boolean foundFirst = false;

  /** To store second number/fraction. */
  static BigFraction secondVal = zeroFrac;

  /** To store operand of expression. */
  static String operand = null;

  /** To store command given. */
  static String command = null;

  /** To store the refister address if STORE command is found. */
  static char register = '\0';

  /** The register where values are stored. */
  static BFRegisterSet storage = new BFRegisterSet();

  /**
   * Continuously take input from the user and prints the computed results.
   * @param args
   *   command line input
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    String lookingAt = null; // to store the token currently being looked at
    BigFraction result = zeroFrac;

    while (command.equals("QUIT")) {
      // SAVE STUFF
      lookingAt = eyes.next();
      while (eyes.hasNext()) {
        char action = sortArg(lookingAt);
        if (action == 'c') {
          lookingAt = eyes.next();
          register = lookingAt.charAt(0);
          break;
        } // if action was command, next argument is register.
        lookingAt = eyes.next();
      } // while there are more tokens

      BFCalculator calculator = new BFCalculator(firstVal); // set up the calculator

      if (command.equals("STORE")) {
        storage.store(register, result);
        pen.println("STORED\n");
      } // if command is STORE, store most recently computed value
      else if (operand.equals("+")) {
        calculator.add(secondVal);
        result = calculator.get();
      } // if adding
      else if (operand.equals("-")) {
        calculator.subtract(secondVal);
        result = calculator.get();
      } // if subtracting
      else if (operand.equals("*")) {
        calculator.multiply(secondVal);
        result = calculator.get();
      } // if multiplying
      else if (operand.equals("/")) {
        calculator.divide(secondVal);
        result = calculator.get();
      } // if dividing
      // CLOSING ELSE??

      pen.println(result.toString());
    } // while command is not QUIT, continue prompting for input

    eyes.close();
    return;
  } // main()

  /**
   * Save the provided argument in the appropriate variable.
   * @param arg
   *   a token of command line input to be saved
   * @return char
   *   character corresponding to which value was saved.
   *   c: command, v: numeric value, o: operand, r: register name, e: error
   * Alters static fields.
   */
  public static char sortArg(String arg) {
    // If STORE or QUIT, save to command
    if (arg.equals("STORE") || arg.equals("QUIT")) {
      command = arg;
      return 'c';
    } // if found command
    // main will then save the register, nothing to do here
    else if (arg.length() == 1) {
      char ch = arg.charAt(0);
      if (!foundFirst) {
        firstVal = storage.get(ch);
        return 'r';
      } // if first value has not been found, save there
      else {
        secondVal = storage.get(ch);
        return 'r';
      } // else save as second value
    } // else if arg is a register name, save value  
    else if (arg.equals("*") || arg.equals("+") || arg.equals("/") || arg.equals("-")) {
      operand = arg;
      return 'o';
    } // else if is operand, save as operand
    else if (isFracStr(arg)) {
      if (!foundFirst) {
        firstVal = new BigFraction(arg);
        return 'v';
      } // if first value has not been found, save there
      else {
        secondVal = new BigFraction(arg);
        return 'v';
      } // else save as second value
    } // else, must be a numeric value
    else {
      // If we got this far, something went wrong.
      // Print error and return error key.
      System.err.print("Error: invalid argument provided. \n");
      return 'e';
    } // else must be an error
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
        if (!foundSlash) {
          return false;
        } // if multiple slashes have been found, return invalid
        else {
          foundSlash = true;
        } // else, first slash has been found
      } // if char is a slash
      else if (!Character.isDigit(str.charAt(i))) {
        return false;
      } // if char is not a digit
    } // for each character in suspect string
    return true;
  } // isFracStr(String)
} // class InteractiveCalculator
