package org.ssh.math.algebra;

import lombok.experimental.UtilityClass;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.eigen.Eigen;
import org.nd4j.linalg.indexing.BooleanIndexing;
import org.nd4j.linalg.indexing.conditions.Conditions;

/**
 * The Class LinearAlgebra.
 *
 * This class contains utility functions for performing linear algebra operations
 * on {@link INDArray}.
 *
 * @author Rimon Oz
 */
@UtilityClass
public class LinearAlgebra {

  /**
   * Determines whether a matrix is positive definite.
   * A positive definite matrix, M, is a matrix which is Hermitian and for which the scalar
   * transpose(Z)*M*Z is real and positive for all non-zero column vectors Z (1 by n).
   *
   * @param subject The potential positive definite matrix.
   * @return True is the matrix is positive definite, false otherwise.
   */
  public static boolean isPositiveDefinite(final INDArray subject) {
    return LinearAlgebra.isHermitian(subject)
        && BooleanIndexing.and(
        Eigen.eigenvalues(subject),
        Conditions.greaterThan(0));
  }

  /**
   * Determines whether a matrix is Hermitian.
   * A Hermitian matrix is a square (n by n) matrix which is equal to its
   * conjugate transpose.
   *
   * @param subject The potential Hermitian matrix.
   * @return True if the matrix is Hermitian, false otherwise.
   */
  public static boolean isHermitian(final INDArray subject) {
    return BooleanIndexing.and(subject.eq(subject.transpose()), Conditions.equals(0d));
  }

  /**
   * Determines whether a matrix is positive semi-definite.
   * A semi-positive definite matrix, M, is a matrix which is Hermitian and for which the scalar
   * transpose(Z)*M*Z is real and non-negative for all non-zero column vectors Z (1 by n).
   *
   * @param subject The potential positive semi-definite matrix.
   * @return True is the matrix is positive semi-definite, false otherwise.
   */
  public static boolean isPositiveSemiDefinite(final INDArray subject) {
    return LinearAlgebra.isHermitian(subject)
        && BooleanIndexing.and(
        Eigen.eigenvalues(subject),
        Conditions.greaterThanOrEqual(0));
  }

  /**
   * Determines whether a matrix is negative definite.
   * A negative definite matrix, M, is a matrix which is Hermitian and for which the scalar
   * transpose(Z)*M*Z is real and negative for all non-zero column vectors Z (1 by n).
   *
   * @param subject The potential negative definite matrix.
   * @return True is the matrix is negative definite, false otherwise.
   */
  public static boolean isNegativeDefinite(final INDArray subject) {
    return LinearAlgebra.isHermitian(subject)
        && BooleanIndexing.and(
        Eigen.eigenvalues(subject),
        Conditions.lessThan(0));
  }

  /**
   * Determines whether a matrix is negative semi-definite.
   * A negative semi-definite matrix, M, is a matrix which is Hermitian and for which the scalar
   * transpose(Z)*M*Z is real and non-positive for all non-zero column vectors Z (1 by n).
   *
   * @param subject The potential negative semi-definite matrix.
   * @return True is the matrix is negative semi-definite, false otherwise.
   */
  public static boolean isNegativeSemiDefinite(final INDArray subject) {
    return LinearAlgebra.isHermitian(subject)
        && BooleanIndexing.and(
        Eigen.eigenvalues(subject),
        Conditions.lessThanOrEqual(0));
  }
}
