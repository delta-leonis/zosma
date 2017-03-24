package org.ssh.math.ai;

import lombok.experimental.UtilityClass;
import org.ssh.math.geometry.Norm;
import org.ssh.math.geometry.Trigonometry;
import org.ssh.math.statistic.distribution.GaussianDistribution;


/**
 * The Class RadialBasisFunctions.
 * <p>
 * This class contains methods for computing values, derivatives, and integrals of (vector-valued)
 * hyperspherical basis functions.
 *
 * @author Rimon Oz
 * @see <a href="https://en.wikipedia.org/wiki/Radial_basis_function#Types">Radial basis
 * functions</a>
 */
@UtilityClass
public class RadialBasisFunctions {

  /**
   * Computes the value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double inverseQuadratic(Norm norm,
      V input, Number epsilon) {
    return 1d / (1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double inverseMultiQuadric(Norm norm,
      V input, Number epsilon) {
    return 1d / StrictMath
        .sqrt(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param <V>   The type of vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @return The value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double thinPlateSpline(Norm norm,
      V input) {
    return RadialBasisFunctions.polyharmonicSpline(norm, input, 2);
  }

  /**
   * Computes the value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param <V>   The type of vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @param k     The degree of the spline.
   * @return The value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double polyharmonicSpline(Norm norm,
      V input, int k) {
    double exponentiatedEuclidianNorm = StrictMath.pow(norm.calculateNorm(input), k);
    if (k % 2 == 0) {
      return exponentiatedEuclidianNorm * StrictMath.log(exponentiatedEuclidianNorm);
    } else {
      return exponentiatedEuclidianNorm;
    }
  }

  /**
   * Computes the value of the derivative of the Gaussian RBF in the point to which the supplied
   * vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the Gaussian RBF in the point to which the supplied vector points.
   */
  public static <N extends Number, V extends Iterable<N>> double gaussianDerivative(Norm norm,
      V input, Number epsilon) {
    return -2d * norm.calculateNorm(input)
        * StrictMath.pow(epsilon.doubleValue(), 2)
        * RadialBasisFunctions.gaussian(norm, input, epsilon);
  }

  /**
   * Computes the value of the Gaussian RBF in the point to which the supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the Gaussian RBF in the point to which the supplied vector points.
   */
  public static <N extends Number, V extends Iterable<N>> double gaussian(Norm norm, V input,
      Number epsilon) {
    return StrictMath
        .exp(-1d * StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the derivative of the multiquadric RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the multiquadric RBF in the point to which the supplied vector points.
   */
  public static <N extends Number, V extends Iterable<N>> double multiQuadricDerivative(Norm norm,
      V input, Number epsilon) {
    return norm.calculateNorm(input) * StrictMath.pow(epsilon.doubleValue(), 2)
        / StrictMath
        .sqrt(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the derivative of the inverse quadratic RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double inverseQuadraticDerivative(
      Norm norm, V input, Number epsilon) {
    return (-2d * norm.calculateNorm(input) * StrictMath.pow(epsilon.doubleValue(), 2))
        / StrictMath
        .pow(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2), 2);
  }

  /**
   * Computes the value of the derivative of the inverse multiquadric RBF in the point to which
   * the supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double inverseMultiQuadricDerivative(
      Norm norm, V input, Number epsilon) {
    return (-1d * norm.calculateNorm(input) * StrictMath.pow(epsilon.doubleValue(), 2))
        / StrictMath
        .pow(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2),
            3d / 2d);
  }

  /**
   * Computes the value of the derivative of the thin plate spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param <V>   The type of vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @return The value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double thinPlateSplineDerivative(
      Norm norm, V input) {
    return RadialBasisFunctions.polyharmonicSplineDerivative(norm, input, 2);
  }

  /**
   * Computes the value of the derivative of the polyharmonic spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param <V>   The type of vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @param k     The degree of the spline.
   * @return The value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double polyharmonicSplineDerivative(
      Norm norm, V input, int k) {
    double exponentiatedEuclidianNorm = StrictMath.pow(norm.calculateNorm(input), k - 1);
    if (k % 2 == 0) {
      return exponentiatedEuclidianNorm * (k * StrictMath.log(norm.calculateNorm(input) + 1));
    } else {
      return k * exponentiatedEuclidianNorm;
    }
  }

  /**
   * Computes the value of the integral of the Gaussian RBF in the point to which the supplied
   * vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the Gaussian RBF in the point to which the supplied vector points.
   */
  public static <N extends Number, V extends Iterable<N>> double gaussianIntegral(Norm norm,
      V input, Number epsilon) {
    return StrictMath.sqrt(Math.PI) * (GaussianDistribution
        .erf(norm.calculateNorm(input) * epsilon.doubleValue()))
        / (2d * epsilon.doubleValue());

  }

  /**
   * Computes the value of the integral of the multiquadric RBF in the point to which the supplied
   * vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the multiquadric RBF in the point to which the supplied vector points.
   */
  public static <N extends Number, V extends Iterable<N>> double multiQuadricIntegral(Norm norm,
      V input, Number epsilon) {
    double euclidianNorm = norm.calculateNorm(input);
    return (euclidianNorm * epsilon.doubleValue() * RadialBasisFunctions
        .multiQuadric(norm, input, epsilon)
        + Trigonometry.asinh(euclidianNorm * epsilon.doubleValue()))
        / (2d * epsilon.doubleValue());
  }

  /**
   * Computes the value of the multiquadric RBF in the point to which the supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the multiquadric RBF in the point to which the supplied vector points.
   */
  public static <N extends Number, V extends Iterable<N>> double multiQuadric(Norm norm, V input,
      Number epsilon) {
    return StrictMath
        .sqrt(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the integral of the inverse quadratic RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double inverseQuadraticIntegral(
      Norm norm, V input, Number epsilon) {
    return Trigonometry.atanh(norm.calculateNorm(input) * epsilon.doubleValue())
        / epsilon.doubleValue();
  }

  /**
   * Computes the value of the integral of the inverse multiquadric RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param <V>     The type of vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double inverseMultiQuadricIntegral(
      Norm norm, V input, Number epsilon) {
    return Trigonometry.asinh(norm.calculateNorm(input) * epsilon.doubleValue())
        / epsilon.doubleValue();
  }

  /**
   * Computes the value of the integral of the thin plate spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param <V>   The type of vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @return The value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double thinPlateSplineIntegral(
      Norm norm, V input) {
    return RadialBasisFunctions.polyharmonicSplineIntegral(norm, input, 2);
  }

  /**
   * Computes the value of the integral of the polyharmonic spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param <V>   The type of vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @param k     The degree of the spline.
   * @return The value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number, V extends Iterable<N>> double polyharmonicSplineIntegral(
      Norm norm, V input, int k) {
    double exponentiatedEuclidianNorm = StrictMath.pow(norm.calculateNorm(input), k + 1);
    if (k % 2 == 0) {
      return
          (exponentiatedEuclidianNorm * ((k + 1) * StrictMath.log(norm.calculateNorm(input))
              - 1))
              / StrictMath.pow(k + 1d, 2);
    } else {
      return exponentiatedEuclidianNorm / (k + 1);
    }
  }
}
