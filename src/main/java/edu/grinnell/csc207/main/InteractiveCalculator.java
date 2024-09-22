package edu.grinnell.csc207.main;

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

  /** To check if second value has been found. */
  static boolean foundSecond = false;

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

  // +---------+--------------------------------------------------------
  // | Methods |
  // +---------+
  
  /**
   * Continuously take input from the user and prints the computed results.
   * @param args
   *   command line input
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    String lookingAt; // to store the token currently being looked at
    BigFraction result = zeroFrac;

    while (!command.equals("QUIT")) {
      
      // SAVE STUFF
      while (!foundCommand && !foundSecond) {
        lookingAt = eyes.next();
        char action = sortArg(lookingAt);
        if (action == 's') {
          // if action was STORE, next argument is register.
          lookingAt = eyes.next();
          register = lookingAt.charAt(0);
          break;
        } else if (action == 'q') {
          // if action was QUIT, quit ASAP
          eyes.close();
          return;
        }
      } // while a command or second variable have not beed found

      BFCalculator calculator = new BFCalculator(firstVal); // set up the calculator

      if (command.equals("STORE")) {
        // if command is STORE, store most recently computed value
        storage.store(register, result);
        pen.println("STORED");
      } else if (operand.equals("+")) {
        // if adding
        calculator.add(secondVal);
        result = calculator.get();
        pen.println(result.toString());
      } else if (operand.equals("-")) {
        // if subtracting
        calculator.subtract(secondVal);
        result = calculator.get();
        pen.println(result.toString());
      } else if (operand.equals("*")) {
        // if multiplying
        calculator.multiply(secondVal);
        result = calculator.get();
        pen.println(result.toString());
      } else if (operand.equals("/")) {
        // if dividing
        calculator.divide(secondVal);
        result = calculator.get();
        pen.println(result.toString());
      } // else
      // CLOSING ELSE??

      // Reset values
      firstVal = zeroFrac;
      secondVal = zeroFrac;
      foundFirst = false;
      foundSecond = false;
      command = "RUN";
      operand = " ";
      foundCommand = false;
      register = '\n';
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
    } else if (isFracStr(arg) == true) {
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
        return 'r';
      } else {
        // else save as second value
        secondVal = storage.get(ch);
        foundSecond = true;
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
} // class InteractiveCalculator
