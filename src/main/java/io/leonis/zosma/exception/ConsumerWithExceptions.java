package io.leonis.zosma.exception;

import java.util.function.Consumer;

/**
 * The Interface ConsumerWithExceptions.
 *
 * This class represents a {@link Consumer} which may throw an {@link Exception}.
 *
 * @param <T> The type of argument passed to the {@link Consumer}.
 * @param <E> The type of exception which may be thrown by the {@link Consumer}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface ConsumerWithExceptions<T, E extends Exception> {

  /**
   * A {@link Consumer} which may throw an exception.
   *
   * @param argument The first argument to consume.
   * @throws E The type of exception which may be thrown by the {@link Consumer}.
   */
  void accept(final T argument) throws E;
}
