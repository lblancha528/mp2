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
  /**
   * BigFraction of 0 to initialize holder variables.
   */
  BigFraction zeroFrac = new BigFraction(0, 0);
  /**
   * Continuously take input from the user and prints the computed results.
   */
  public void main() {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    BigFraction firstVal = zeroFrac; // to store initial number/fraction 
    boolean foundFirst = false; // to check if first value has been found, then store in second
    BigFraction secondVal = zeroFrac; // to store second number/fraction
    String operand = null; // to store operand of expression
    String command = null; // to store command given
    String lookingAt = null; // to store the token currently being looked at, most recently found by eyes
    String register = null; // to store the register address if STORE command is found

    BFRegisterSet storage = new BFRegisterSet();

    // Repeatedly take in user input (math equations)
    // Use BFC to compute and then print the results

    // Options are
      // do math: presented an expression
      // STORE: store the last computed value in a register
      // QUIT: end program

    // Loop here on to check for next line and execute commands or math.
// SAVE STUFF
    lookingAt = eyes.next();
    // If STORE or QUIT, save to command. 
    //   If STORE, set register to next token
    //   EXIT TO DO STUFF
    // If foundFirst = F, do to firstVal, else do to secondVal
    // If value is a letter, set the firstVal to the value in register
    // Else firstVal to a new BigFraction
    //    Set foundFirst to true.
    // Else if value is an operand, set the operand
    // Else if is nothing, error and reprompt.
// DO STUFF
    // STORE
    //  storage.store(register, calculator.get();)
    // QUIT
    //  exit program
    // DO MATH
    // Create a calculator with base value firstValue
    BFCalculator calculator = new BFCalculator(firstValue);
    // Call the operand method on the calculator with secondValue as arg
    // If operand == '+' do calculator.add(secondValue); ETC
    // Print calculator.get();
//REPEAT

    eyes.close();
    return;
  } // main()
} // class InteractiveCalculator
