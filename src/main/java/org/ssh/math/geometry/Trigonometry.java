package org.ssh.math.geometry;

import lombok.experimental.UtilityClass;

/**
 * The Class Trigonometry.
 *
 * @author Rimon Oz
 */
@UtilityClass
public class Trigonometry {

  /**
   * Calculates the inverse hyperbolic sine of the supplied value.
   *
   * @param value The value to calculate the inverse hyperbolic sine of.
   * @return The inverse hyperbolic sine of the supplied value.
   */
  public static double asinh(double value) {
    return StrictMath.log(value + StrictMath.sqrt(value * value + 1d));
  }

  /**
   * Calculates the inverse hyperbolic cosine of the supplied value.
   *
   * @param value The value to calculate the inverse hyperbolic cosine of.
   * @return The inverse hyperbolic cosine of the supplied value.
   */
  public static double acosh(double value) {
    return StrictMath.log(value + StrictMath.sqrt(value * value - 1d));
  }

  /**
   * Calculates the inverse hyperbolic tangent of the supplied value.
   *
   * @param value The value to calculate the inverse hyperbolic tangent of.
   * @return The inverse hyperbolic tangent of the supplied value.
   */
  public static double atanh(double value) {
    return 0.5d * StrictMath.log((value + 1d) / (value - 1d));
  }
}
