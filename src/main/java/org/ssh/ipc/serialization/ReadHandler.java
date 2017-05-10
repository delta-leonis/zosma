package org.ssh.ipc.serialization;

/**
 * The Interface ReadHandler
 *
 * This interface describes the functionality of an object which andles the reading
 * of the {@link Reader}.
 *
 * @param <I> Type to deserialize.
 * @param <O> Target of deserialization.
 * @author Jeroen de Jong
 */
@FunctionalInterface
public interface ReadHandler<I, O> {
  /**
   * Read a supplied source into a specific type.
   *
   * @param input The input to read from.
   * @return The output.
   */
  O read(I input);
}
