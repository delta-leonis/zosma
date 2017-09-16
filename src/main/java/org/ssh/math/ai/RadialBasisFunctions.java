package org.ssh.math.ai;

import lombok.experimental.UtilityClass;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.ssh.math.geometry.*;
import org.ssh.math.statistic.distribution.GaussianDistribution;


/**
 * The Class RadialBasisFunctions.
 *
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
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double inverseQuadratic(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return 1d / (1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double inverseMultiQuadric(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return 1d / StrictMath
        .sqrt(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @return The value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double thinPlateSpline(
      final Norm norm,
      final INDArray input
  ) {
    return RadialBasisFunctions.polyharmonicSpline(norm, input, 2);
  }

  /**
   * Computes the value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @param k     The degree of the spline.
   * @return The value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double polyharmonicSpline(
      final Norm norm,
      final INDArray input,
      final int k
  ) {
    final double exponentiatedEuclidianNorm = StrictMath.pow(norm.calculateNorm(input), k);
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
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the Gaussian RBF in the point to which the supplied vector points.
   */
  public static <N extends Number> double gaussianDerivative(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return -2d * norm.calculateNorm(input)
        * StrictMath.pow(epsilon.doubleValue(), 2)
        * RadialBasisFunctions.gaussian(norm, input, epsilon);
  }

  /**
   * Computes the value of the Gaussian RBF in the point to which the supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the Gaussian RBF in the point to which the supplied vector points.
   */
  public static <N extends Number> double gaussian(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return StrictMath
        .exp(-1d * StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the derivative of the multiquadric RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the multiquadric RBF in the point to which the supplied vector points.
   */
  public static <N extends Number> double multiQuadricDerivative(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return norm.calculateNorm(input) * StrictMath.pow(epsilon.doubleValue(), 2)
        / StrictMath
        .sqrt(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the derivative of the inverse quadratic RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double inverseQuadraticDerivative(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return (-2d * norm.calculateNorm(input) * StrictMath.pow(epsilon.doubleValue(), 2))
        / StrictMath
        .pow(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2), 2);
  }

  /**
   * Computes the value of the derivative of the inverse multiquadric RBF in the point to which
   * the supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double inverseMultiQuadricDerivative(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
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
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @return The value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double thinPlateSplineDerivative(
      final Norm norm,
      final INDArray input
  ) {
    return RadialBasisFunctions.polyharmonicSplineDerivative(norm, input, 2);
  }

  /**
   * Computes the value of the derivative of the polyharmonic spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @param k     The degree of the spline.
   * @return The value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double polyharmonicSplineDerivative(
      final Norm norm,
      final INDArray input,
      final int k
  ) {
    final double exponentiatedEuclidianNorm = StrictMath.pow(norm.calculateNorm(input), k - 1);
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
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the Gaussian RBF in the point to which the supplied vector points.
   */
  public static <N extends Number> double gaussianIntegral(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return StrictMath.sqrt(Math.PI) * (GaussianDistribution
        .erf(norm.calculateNorm(input) * epsilon.doubleValue()))
        / (2d * epsilon.doubleValue());

  }

  /**
   * Computes the value of the integral of the multiquadric RBF in the point to which the supplied
   * vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the multiquadric RBF in the point to which the supplied vector points.
   */
  public static <N extends Number> double multiQuadricIntegral(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    final double euclidianNorm = norm.calculateNorm(input);
    return (euclidianNorm * epsilon.doubleValue() * RadialBasisFunctions
        .multiQuadric(norm, input, epsilon)
        + Trigonometry.asinh(euclidianNorm * epsilon.doubleValue()))
        / (2d * epsilon.doubleValue());
  }

  /**
   * Computes the value of the multiquadric RBF in the point to which the supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the multiquadric RBF in the point to which the supplied vector points.
   */
  public static <N extends Number> double multiQuadric(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return StrictMath
        .sqrt(1d + StrictMath.pow(epsilon.doubleValue() * norm.calculateNorm(input), 2));
  }

  /**
   * Computes the value of the integral of the inverse quadratic RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse quadratic RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double inverseQuadraticIntegral(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return Trigonometry.atanh(norm.calculateNorm(input) * epsilon.doubleValue())
        / epsilon.doubleValue();
  }

  /**
   * Computes the value of the integral of the inverse multiquadric RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>     The type of {@link Number} stored inside the vector.
   * @param norm    the norm
   * @param input   The input vector which points to the point whose value needs to be calculated.
   * @param epsilon The scaling factor.
   * @return The value of the inverse multiquadric RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double inverseMultiQuadricIntegral(
      final Norm norm,
      final INDArray input,
      final N epsilon
  ) {
    return Trigonometry.asinh(norm.calculateNorm(input) * epsilon.doubleValue())
        / epsilon.doubleValue();
  }

  /**
   * Computes the value of the integral of the thin plate spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @return The value of the thin plate spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double thinPlateSplineIntegral(
      final Norm norm,
      final INDArray input
  ) {
    return RadialBasisFunctions.polyharmonicSplineIntegral(norm, input, 2);
  }

  /**
   * Computes the value of the integral of the polyharmonic spline RBF in the point to which the
   * supplied vector points.
   *
   * @param <N>   The type of {@link Number} stored inside the vector.
   * @param norm  the norm
   * @param input The input vector which points to the point whose value needs to be calculated.
   * @param k     The degree of the spline.
   * @return The value of the polyharmonic spline RBF in the point to which the supplied vector
   * points.
   */
  public static <N extends Number> double polyharmonicSplineIntegral(
      final Norm norm,
      final INDArray input,
      final int k
  ) {
    final double exponentiatedEuclidianNorm = StrictMath.pow(norm.calculateNorm(input), k + 1);
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
