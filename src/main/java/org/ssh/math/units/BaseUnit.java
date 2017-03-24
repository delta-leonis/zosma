package org.ssh.math.units;

/**
 * @author Rimon Oz
 */
public enum BaseUnit implements Unit {
    METER("m", "L", "length"),
    KILOGRAM("kg", "M", "mass"),
    SECOND("s", "T", "time"),
    AMPERE("A", "I", "current"),
    KELVIN("K", "\\Theta", "temperature"),
    MOLE("mol", "N", "amount"),
    CANDELA("cd", "J", "intensity"),
    BIT("bit", "bit", "information"),

    BYTE("B", "byte", "information"),
    RATIO("%", "%", "quotient"),
    TRUTH("", "", "truth"),
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
