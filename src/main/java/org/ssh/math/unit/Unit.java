package org.ssh.math.unit;

/**
 * The Interface Unit
 *
 * @author Rimon Oz
 */
public interface Unit {

  /**
   * Gets dimension symbol.
   *
   * @return the dimension symbol
   */
  String getDimensionSymbol();

  /**
   * Gets quantity name.
   *
   * @return the quantity name
   */
  String getQuantityName();

  /**
   * Gets unit symbol.
   *
   * @return the unit symbol
   */
  String getUnitSymbol();
}
