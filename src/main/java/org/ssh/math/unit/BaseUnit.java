package org.ssh.math.unit;

/**
 * The enum Base unit.
 *
 * @author Rimon Oz
 */
public enum BaseUnit implements Unit {
  /**
   * Meter base unit.
   */
  METER("m", "L", "length"),
  /**
   * Kilogram base unit.
   */
  KILOGRAM("kg", "M", "mass"),
  /**
   * Second base unit.
   */
  SECOND("s", "T", "time"),
  /**
   * Ampere base unit.
   */
  AMPERE("A", "I", "current"),
  /**
   * Kelvin base unit.
   */
  KELVIN("K", "\\Theta", "temperature"),
  /**
   * Mole base unit.
   */
  MOLE("mol", "N", "amount"),
  /**
   * Candela base unit.
   */
  CANDELA("cd", "J", "intensity"),
  /**
   * Bit base unit.
   */
  BIT("bit", "bit", "information"),

  /**
   * Byte base unit.
   */
  BYTE("B", "byte", "information"),
  /**
   * Ratio base unit.
   */
  RATIO("%", "%", "quotient"),
  /**
   * Truth base unit.
   */
  TRUTH("", "", "truth"),
  /**
   * Unit base unit.
   */
  UNIT("unit(s)", "u", "quantity");

  private final String unitSymbol;
  private final String dimensionSymbol;
  private final String quantityName;

  BaseUnit(String unitSymbol, String dimensionSymbol, String quantityName) {
    this.unitSymbol = unitSymbol;
    this.dimensionSymbol = dimensionSymbol;
    this.quantityName = quantityName;
  }

  @Override
  public String getDimensionSymbol() {
    return this.dimensionSymbol;
  }

  @Override
  public String getQuantityName() {
    return this.quantityName;
  }

  @Override
  public String getUnitSymbol() {
    return this.unitSymbol;
  }
}
