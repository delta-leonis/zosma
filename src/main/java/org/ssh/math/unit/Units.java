package org.ssh.math.unit;

/**
 * The Enum Units.
 * <p>
 * This enumeration lists commonly used {@link Unit unit}.
 *
 * @author Rimon Oz
 */
public enum Units implements Unit {
  /**
   * Meter units.
   */
  METER("m", "L", "length"),
  /**
   * Kilogram units.
   */
  KILOGRAM("kg", "M", "mass"),
  /**
   * Second units.
   */
  SECOND("s", "T", "time"),
  /**
   * Ampere units.
   */
  AMPERE("A", "I", "current"),
  /**
   * Kelvin units.
   */
  KELVIN("K", "\\Theta", "temperature"),
  /**
   * Mole units.
   */
  MOLE("mol", "N", "amount"),
  /**
   * Candela units.
   */
  CANDELA("cd", "J", "intensity"),
  /**
   * Bit units.
   */
  BIT("bit", "bit", "information"),

  /**
   * The Rpm.
   */
  RPM("rpm", "rpm", "rotations per minute"),
  /**
   * Volt units.
   */
  VOLT("V", "U", "voltage"),

  /**
   * Byte units.
   */
  BYTE("B", "byte", "information"),
  /**
   * Ratio units.
   */
  RATIO("%", "%", "percentage"),
  /**
   * Truth units.
   */
  TRUTH("", "", "truthiness"),
  /**
   * Unit units.
   */
  UNIT("unit(s)", "u", "quantity");

  private final String unitSymbol;
  private final String dimensionSymbol;
  private final String quantityName;

  /**
   * Constructs a new {@link Unit}.
   *
   * @param unitSymbol      The symbol of the unit.
   * @param dimensionSymbol The dimension of the unit.
   * @param quantityName    The name of the quantity measured by this unit.
   */
  Units(String unitSymbol, String dimensionSymbol, String quantityName) {
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
