package org.ssh.math.function;

/**
 * The Interface Scanner. <p> This interface describes the functionality of a scanner, that is a
 * {@link java.util.function.BiFunction} which takes two arguments and returns a single object of
 * the same type as the first argument. The result value of the scanning operation gets used as the
 * first argument to the scanning operation with the next call.
 *
 * @param <V> The type of object which gets returned by the scanner.
 * @param <I> The type of object which gets used by the scanner as an input.
 * @author Rimon Oz
 */
public interface Scanner<V, I> {

  /**
   * @param previousResult The previously computed value.
   * @param input          The newest input.
   * @return The value computed using the previously computed value and the newest input.
   */
  V scan(final V previousResult, final I input);
}
