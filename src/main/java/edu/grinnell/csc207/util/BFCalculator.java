package edu.grinnell.csc207.util;

import java.math.BigInteger;
/**
 * Calculates values relative to the last computed value.
 */
public class BFCalculator {
  // +----------+-----------------------------------------------------
  // | Constants |
  // +-----------+

  /** The value zero as a BigFraction. */
  BigFraction zeroFrac = new BigFraction(0, 1);

  /** The value 0 as a BigInteger. */
  BigInteger bizero = BigInteger.valueOf(0);  

  /** The value 1 as a BigInteger. */
  BigInteger bione = BigInteger.valueOf(1);  

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

  /**
   * Build a calculator with lastValue initialized to zeroFrac.
   * @return
   */
  public BFCalculator() {
    this.lastValue = zeroFrac;
  } // construct BFCalculator

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
    return this.lastValue;
  } // get()

  /**
   * Adds val to the last computed value.
   * @param val
   *   value to be added
   */
  public void add(BigFraction val) {
    this.lastValue = this.lastValue.add(val);
    return;
  } // add(BigFraction)

  /**
   * Subtracts val from the last computed value.
   * @param val
   *   value to be subtracted
   */
  public void subtract(BigFraction val) {
    this.lastValue = this.lastValue.subtract(val);
    return;
  } // subtract(BigFraction)

  /**
   * Multiplies the last computed value by val.
   * @param val
   *  value to be multiplied
   */
  public void multiply(BigFraction val) {
    this.lastValue = this.lastValue.multiply(val);
    return;
  } // multiply(BigFraction)

  /**
   * Divides the last computed value by val.
   * @param val
   *  value to be divided by
   */
  public void divide(BigFraction val) {
    this.lastValue = this.lastValue.divide(val);
    return;
  } // divide(BigFraction)

  /**
   * Resets the last computed value to 0.
   */
  public void clear() {
    this.lastValue = zeroFrac;
    this.lastValue.num = bizero;
    this.lastValue.denom = bione;
    return;
  } // clear()
} // class BFCalculator
