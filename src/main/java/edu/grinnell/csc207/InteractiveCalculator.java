package edu.grinnell.csc207;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Continuously takes input from the user and prints the computed results.
 */
public class InteractiveCalculator {
  /**
   * Continuously take input from the user and prints the computed results.
   */
  public void main() {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    BigFraction lastComputed;
    BigFraction newNum;
    String operand;

    // Repeatedly take in user input (math equations)
    // Use BFC to compute and then print the results

    // Options are
      // do math: presented an expression
      // STORE: store the last computed value in a register
      // QUIT: end program

    lastComputed = new BigFraction(eyes.next());
    operand = eyes.next();
    newNum = new BigFraction(eyes.next());

    eyes.close();
    return;
  } // main()
} // class InteractiveCalculator
