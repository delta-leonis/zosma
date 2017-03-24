package org.ssh.math.units;

/**
 * The Enum Description.
 *
 * @author Rimon Oz
 */
public enum Description implements Unit {
    TEXT("", "", "description"),
    REPORT("", "", "report");


    private final String unitSymbol;
    private final String dimensionSymbol;
    private final String quantityName;

    Description(String unitSymbol, String dimensionSymbol, String quantityName) {
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
