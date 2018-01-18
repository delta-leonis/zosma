package io.leonis.zosma.exception;

import java.util.function.BiConsumer;

/**
 * The Interface BiConsumerWithExceptions.
 *
 * This interface represents a {@link BiConsumer} which may throw an {@link Exception}.
 *
 * @param <T> The type of the first argument passed to the {@link BiConsumer}.
 * @param <U> The type of the second argument passed to the {@link BiConsumer}.
 * @param <E> The type of exception which may be thrown by the {@link BiConsumer}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface BiConsumerWithExceptions<T, U, E extends Exception> {
  /**
   * A {@link BiConsumer} which may throw an exception.
   *
   * @param firstArgument  The first argument to consume.
   * @param secondArgument The second argument to consume.
   * @throws E The type of exception which may be thrown by the {@link BiConsumer}.
   */
  void accept(final T firstArgument, final U secondArgument) throws E;
}
