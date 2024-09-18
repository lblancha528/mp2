package edu.grinnell.csc207.util;

import java.math.BigInteger;

// Parts copied from Unit Testing lab by SamR
/**
 * An implementation for fractions using BigIntegers.
 */
public class BigFraction {
  //extended version of lab Fraction
  //use BigInteger for num and denom
  //Reduce all fractions all the way
  //other constructors??
  //other operations such as subtract and divide

  // +----------+-----------------------------------------------------
  // | Constants |
  // +-----------+

  /** The value 1 as a BigInteger. */
  BigInteger one = BigInteger.valueOf(1);

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

    /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

    /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */

  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    if (str.indexOf('/') == -1) {
      this.num = new BigInteger(str);
      this.denom = one;
    } // if

    this.num = new BigInteger(str.substring(0, str.indexOf((int) '/')));
    this.denom = new BigInteger(str.substring(str.indexOf((int) '/') + 1, str.length() - 1));

  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Add another fraction to this fraction.
   * Copied and modified from SamR's Unit Testing lab provided code and my lab work.
   *
   * @param val
   *  the value to add
   *
   * @return the result of the addition
   */
  public BigFraction add(BigFraction val) {
    BigInteger newNum;
    BigInteger newDenom;

    newDenom = this.denom.multiply(val.denom);
    newNum = (this.num.multiply(val.denom)).add(val.num.multiply(this.denom));
    BigFraction newFrac = new BigFraction(newNum, newDenom);
    newFrac.simplify();
    return newFrac;
  } // add(BigFraction)

  /**
   * Subtract another fraction from this fraction.
   * Copied and modified from SamR's Unit Testing lab provided code and my lab work.
   *
   * @param val
   *  the value to subtract
   *
   * @return the result of the subtraction
   */
  public BigFraction subtract(BigFraction val) {
    BigInteger newNum;
    BigInteger newDenom;

    newDenom = this.denom.multiply(val.denom);
    newNum = (this.num.multiply(val.denom)).subtract(val.num.multiply(this.denom));
    BigFraction newFrac = new BigFraction(newNum, newDenom);
    newFrac.simplify();
    return newFrac;
  } // subtract(BigFraction)

  /**
   * Multiply another fraction with this fraction.
   *
   * @param val
   *  the value to multiply by
   *
   * @return the result of the multiplication
   */
  public BigFraction multiply(BigFraction val) {
    BigInteger newNum;
    BigInteger newDenom;

    newDenom = this.denom.multiply(val.denom);
    newNum = this.num.multiply(val.num);
    BigFraction newFrac = new BigFraction(newNum, newDenom);
    newFrac.simplify();
    return newFrac;
  } // multiply(BigFraction)

  /**
   * Divide this fraction by another fraction.
   *
   * @param val
   *  the value to divide by
   *
   * @return the result of the division
   */
  public BigFraction divide(BigFraction val) {
    BigInteger newNum;
    BigInteger newDenom;

    // Copy dot flip
    newDenom = this.denom.multiply(val.num);
    newNum = (this.num.multiply(val.denom));
    BigFraction newFrac = new BigFraction(newNum, newDenom);
    newFrac.simplify();
    return newFrac;
  } // subtract(BigFraction)

  /**
   * Simplify this fraction down into an unreduceable fraction.
   *
   * Modifies passed fraction.
   */
  public void simplify() {
    BigInteger gcd = this.num.gcd(this.denom);
    if (gcd != one) {
      this.num = this.num.divide(gcd);
      this.denom = this.denom.divide(gcd);
    } // if
    return;
  } // simplify()

  /**
   * Find the numerator of this fraction.
   * @return numerator
   */
  public BigInteger num() {
    return this.num;
  } // num()

  /**
   * Find the denominator of this fraction.
   * @return denominator
   */
  public BigInteger denom() {
    return this.denom;
  } // denom()

  /**
   * Turn this fraction into a string.
   * Copied and modified from SamR's Unit Testing lab and my lab work.
   *
   * @return this fraction as a string
   */
  public String toString() {
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero
    return this.num + "/" + this.denom;
  } // toString()
} // class InteractiveCalculator
