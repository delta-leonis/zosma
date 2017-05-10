package org.ssh.ipc.serialization;

/**
 * The Interface Writer.
 *
 * This interface describes the functionality of a writer which translates from one type of object
 * to another.
 *
 * @author Jeroen de Jong
 */
@FunctionalInterface
public interface Writer<O> {

  /**
   * Write a given object to a type of {@code <O>}.
   *
   * @param input The object to be serialized.
   * @param <I>   The type of object used as an input.
   * @return The output of the write method.
   */
  <I> O write(I input);
}
