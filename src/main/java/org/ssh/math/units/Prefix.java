package org.ssh.math.units;

import java.math.BigDecimal;

/**
 * The Enum Prefix.
 * <p>
 * This enumeration lists the <a href="https://en.wikipedia.org/wiki/Metric_prefix#List_of_SI_prefixes">SI-prefixes</a>
 * as {@link BigDecimal}.
 *
 * @author Rimon Oz
 */
public enum Prefix {
    // iec
    KIBI(new BigDecimal("1024"), "Ki"),
    MEBI(new BigDecimal("1048576"), "Mi"),
    GIBI(new BigDecimal("1073741824"), "Gi"),
    TEBI(new BigDecimal("1099511627776"), "Ti"),
    PEBI(new BigDecimal("1125899906842624"), "Pi"),
    EXBI(new BigDecimal("1152921504606846976"), "Ei"),
    ZEBI(new BigDecimal("1180591620717411303424"), "Zi"),
    YOBI(new BigDecimal("1208925819614629174706176"), "Yi"),
    // units
    KILO(new BigDecimal("1000"), "k"),
    MEGA(new BigDecimal("1000000"), "M"),
    GIGA(new BigDecimal("1000000000"), "G"),
    TERA(new BigDecimal("1000000000000"), "T"),
    PETA(new BigDecimal("1000000000000000"), "P"),
    EXA(new BigDecimal("1000000000000000000"), "E"),
    ZETTA(new BigDecimal("1000000000000000000000"), "Z"),
    YOTTA(new BigDecimal("1000000000000000000000000"), "Y"),
    MILLI(new BigDecimal("0.001"), "m"),
    MICRO(new BigDecimal("0.000001"), "\\mu"),
    NANO(new BigDecimal("0.000000001"), "n"),
    PICO(new BigDecimal("0.000000000001"), "p"),
    FEMTO(new BigDecimal("0.000000000000001"), "f"),
    ATTO(new BigDecimal("0.000000000000000001"), "a"),
    ZEPTO(new BigDecimal("0.000000000000000000001"), "z"),
    YOCTO(new BigDecimal("0.000000000000000000000001"), "y");

    /**
     * The multiplier as a {@link BigDecimal}.
     */
    private final BigDecimal multiplier;

    private final String prefixString;

    /**
     * Constructs a new prefixString with the specified multiplier.
     *
     * @param multiplier The multiplier as a {@link BigDecimal}.
     */
    Prefix(BigDecimal multiplier, String prefix) {
        this.multiplier = multiplier;
        this.prefixString = prefix;
    }

    public BigDecimal getMultiplier() {
        return this.multiplier;
    }

    @Override
    public String toString() {
        return this.prefixString;
    }
}
