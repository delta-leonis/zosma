package org.ssh.ipc.serialization;

/**
 * The Interface Reader
 *
 * Combination of several {@link ReadHandler ReadHandlers}.
 *
 * @param <I> input type
 * @author Jeroen de Jong
 */
public interface Reader<I> {

  /**
   * Read a supplied source into a specific type
   *
   * @param source Source to read from
   * @param <O>    Output type
   * @return output after reading todo determine whether this method needs a Class<I> target type
   */
  <O> O read(I source);
}
