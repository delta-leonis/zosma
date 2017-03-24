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
@FunctionalInterface
public interface Scanner<V, I> {

  /**
   * Scan v.
   *
   * @param previousResult the previous result
   * @param input          the input
   * @return the v
   */
  V scan(V previousResult, I input);
}
