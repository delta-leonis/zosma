package org.ssh.math.unit;

import java.math.BigDecimal;

/**
 * The Enum Prefix. <p> This enumeration lists the <a href="https://en.wikipedia.org/wiki/Metric_prefix#List_of_SI_prefixes">SI-prefixes</a>
 * as {@link BigDecimal}.
 *
 * @author Rimon Oz
 */
public enum Prefix {
  /**
   * The Kibi.
   */
// iec
  KIBI(new BigDecimal("1024"), "Ki"),
  /**
   * The Mebi.
   */
  MEBI(new BigDecimal("1048576"), "Mi"),
  /**
   * The Gibi.
   */
  GIBI(new BigDecimal("1073741824"), "Gi"),
  /**
   * The Tebi.
   */
  TEBI(new BigDecimal("1099511627776"), "Ti"),
  /**
   * The Pebi.
   */
  PEBI(new BigDecimal("1125899906842624"), "Pi"),
  /**
   * The Exbi.
   */
  EXBI(new BigDecimal("1152921504606846976"), "Ei"),
  /**
   * The Zebi.
   */
  ZEBI(new BigDecimal("1180591620717411303424"), "Zi"),
  /**
   * The Yobi.
   */
  YOBI(new BigDecimal("1208925819614629174706176"), "Yi"),
  /**
   * The Kilo.
   */
// unit
  KILO(new BigDecimal("1000"), "k"),
  /**
   * The Mega.
   */
  MEGA(new BigDecimal("1000000"), "M"),
  /**
   * The Giga.
   */
  GIGA(new BigDecimal("1000000000"), "G"),
  /**
   * The Tera.
   */
  TERA(new BigDecimal("1000000000000"), "T"),
  /**
   * The Peta.
   */
  PETA(new BigDecimal("1000000000000000"), "P"),
  /**
   * The Exa.
   */
  EXA(new BigDecimal("1000000000000000000"), "E"),
  /**
   * The Zetta.
   */
  ZETTA(new BigDecimal("1000000000000000000000"), "Z"),
  /**
   * The Yotta.
   */
  YOTTA(new BigDecimal("1000000000000000000000000"), "Y"),
  /**
   * The Milli.
   */
  MILLI(new BigDecimal("0.001"), "m"),
  /**
   * The Micro.
   */
  MICRO(new BigDecimal("0.000001"), "\\mu"),
  /**
   * The Nano.
   */
  NANO(new BigDecimal("0.000000001"), "n"),
  /**
   * The Pico.
   */
  PICO(new BigDecimal("0.000000000001"), "p"),
  /**
   * The Femto.
   */
  FEMTO(new BigDecimal("0.000000000000001"), "f"),
  /**
   * The Atto.
   */
  ATTO(new BigDecimal("0.000000000000000001"), "a"),
  /**
   * The Zepto.
   */
  ZEPTO(new BigDecimal("0.000000000000000000001"), "z"),
  /**
   * The Yocto.
   */
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

  /**
   * Gets multiplier.
   *
   * @return the multiplier
   */
  public BigDecimal getMultiplier() {
    return this.multiplier;
  }

  @Override
  public String toString() {
    return this.prefixString;
  }
}
