package org.ssh.math.geometry;

/**
 * The Interface Norm.
 * <p>
 * This interface describes the functionality of a norm, ie. a measure of distance.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Norm {

  /**
   * @param <V>   The type of vector.
   * @param <N>   The type of number in the vector.
   * @param input The vector to compute the norm of.
   * @return The norm of the vector.
   */
  <V extends Iterable<N>, N extends Number> double calculateNorm(final V input);
}
