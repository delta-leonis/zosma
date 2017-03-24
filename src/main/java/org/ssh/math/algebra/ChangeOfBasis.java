package org.ssh.math.algebra;

/**
 * The Interface ChangeOfBasis. <p> This interface describes the functionality of a change of basis
 * matrix which is used to transform a vector from one coordinate system to another. A change of
 * basis matrix should be expressed with respect to the standard basis (since the basis vectors are
 * locally linear) but can be dependent on the position vector.
 *
 * @param <V> The type of vector.
 * @author Rimon Oz
 */
public interface ChangeOfBasis<V> {

  /**
   * Returns the change of basis matrix for the supplied vector.
   *
   * @param input The vector to compute the change of basis matrix for.
   * @return The change of basis matrix for the supplied vector.
   */
  V asMatrix(V input);

  /**
   * Computes the vector representation of the supplied vector in the basis described by the
   * implementing class.
   *
   * @param input The input vector.
   * @return The vector representation of the supplied vector in the basis described by the
   * implementing class.
   */
  V change(V input);
}
