package org.ssh.math.filter;

/**
 * The Interface Filter.
 *
 * This interface represents a mathematical filter which operates on objects of the supplied
 * generic type.
 *
 * @param <T> The type of mathematical object on which the filter operates.
 * @author Rimon Oz
 */
public interface Filter<T> {

  /**
   * @param previousResult The previously computed value.
   * @param input          The newest input.
   * @return The value computed using the previously computed value and the newest input.
   */
  T filter(final T previousResult, final T input);
}
