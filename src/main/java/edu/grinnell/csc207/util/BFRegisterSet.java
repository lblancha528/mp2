package edu.grinnell.csc207.util;

/**
 * A register to hold calculated values.
 */
public class BFRegisterSet {
  // +--------+---------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * A BigFraction array.
   * Indices correspond to a letter, where a = 0, b = 1, etc.
   */
  BigFraction[] storage;
  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build an empty register.
   */
  public BFRegisterSet() {
    this.storage = new BigFraction[26];
  } // construct a BFRegisterSet

  // +---------+--------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Stores the given value in the specified register.
   *
   * @param register
   *  the corresponding letter value of the desired register to store in
   *
   * @param val
   *  the value to be stored
   */
  public void store(char register, BigFraction val) {
    storage[letter2int(register)] = val;
    return;
  } // store(char, BigFraction)

  /**
   * Retrieves the value from the given register.
   * @param register
   *  the letter address of the register to be stored in
   * @return the value stored in register
   */
  public BigFraction get(char register) {
    return storage[letter2int(register)];
  } // get(char)

  /**
   * Convert a character to an integer with 'a' = 0.
   * Copied from my MP1
   * @param letter
   *  letter to be converted
   * @return an int value 0-26 corresponding to the letter provided
   */
  private static int letter2int(char letter) {
    int base = (int) 'a';
    return (int) letter - base;
  } // letter2int(char)
} // BFRegisterSet
