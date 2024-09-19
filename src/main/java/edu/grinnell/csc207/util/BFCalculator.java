package edu.grinnell.csc207.util;
/**
 * Calculates values relative to the last computed value.
 */
public class BFCalculator {
  // +----------+-----------------------------------------------------
  // | Constants |
  // +-----------+

    /** The value zero as a BigFraction. */
  BigFraction zeroFrac = new BigFraction(0, 0);

  // +--------+---------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The last calculated value.
   */
  BigFraction lastValue;

  // +--------------+---------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a calculator with lastValue initialized to provided value.
   * @param init
   *   A big fraction to set the lastValue
   */
  public BFCalculator(BigFraction init) {
    this.lastValue = init;
  } // construct a BFCalculator

  // +---------+--------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Gets the last computed value.
   * Returns 0 if there is no such value.
   *
   * @return the last computed value
   */
  public BigFraction get() {
    return new BigFraction(0, 1);
  } // get()

  /**
   * Adds val to the last computed value.
   * @param val
   *   value to be added
   */
  public void add(BigFraction val) {
    return;
  } // add(BigFraction)

  /**
   * Subtracts val from the last computed value.
   * @param val
   *   value to be subtracted
   */
  public void subtract(BigFraction val) {
    return;
  } // subtract(BigFraction)

  /**
   * Multiplies the last computed value by val.
   * @param val
   *  value to be multiplied
   */
  public void multiply(BigFraction val) {
    return;
  } // multiply(BigFraction)

  /**
   * Divides the last computed value by val.
   * @param val
   *  value to be divided by
   */
  public void divide(BigFraction val) {
    return;
  } // divide(BigFraction)

  /**
   * Resets the last computed value to 0.
   */
  public void clear() {
    return;
  } // clear()
} // class BFCalculator
