package org.ssh.math.units;

/**
 * The Enum Units.
 * <p>
 * This enumeration lists commonly used {@link Unit units}.
 *
 * @author Rimon Oz
 */
public enum Units implements Unit {
    METER("m", "L", "length"),
    KILOGRAM("kg", "M", "mass"),
    SECOND("s", "T", "time"),
    AMPERE("A", "I", "current"),
    KELVIN("K", "\\Theta", "temperature"),
    MOLE("mol", "N", "amount"),
    CANDELA("cd", "J", "intensity"),
    BIT("bit", "bit", "information"),

    RPM("rpm", "rpm", "rotations per minute"),
    VOLT("V", "U", "voltage"),

    BYTE("B", "byte", "information"),
    RATIO("%", "%", "percentage"),
    TRUTH("", "", "truthiness"),
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
    public String getUnitSymbol() {
        return this.unitSymbol;
    }

    @Override
    public String getDimensionSymbol() {
        return this.dimensionSymbol;
    }

    @Override
    public String getQuantityName() {
        return this.quantityName;
    }
}
