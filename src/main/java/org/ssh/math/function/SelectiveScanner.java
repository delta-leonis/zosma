package org.ssh.math.function;

/**
 * The Interface SelectiveScanner.
 * <p>
 * This interface describes the functionality of a scanner which operates
 * on specific subjects which can be extracted from the input type.
 *
 * @param <V> The type of result returned by this scanner.
 * @param <I> The type of result accepted by this scanner.
 * @param <T> The type of subject which can be extracted from the input to the scanner.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface SelectiveScanner<V, I, T> {

  /**
   * Applies the scanning operation to the previously returned result, the newly
   * acquired result, and the subjects which can be extracted from the input.
   *
   * @param previousResult the previous result
   * @param input          the input
   * @param subjects       the subjects
   * @return the v
   */
  V scan(V previousResult, I input, T subjects);
}
