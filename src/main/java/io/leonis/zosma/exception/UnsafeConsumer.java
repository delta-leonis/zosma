package io.leonis.zosma.exception;

import java.util.function.Consumer;
import lombok.Value;

/**
 * The Class UnsafeConsumer.
 *
 * This class represents a {@link ConsumerWithExceptions} which rethrows
 * {@link Exception Exceptions} as {@link RuntimeException RuntimeExceptions}.
 *
 * @param <T> The type of argument passed to the {@link ConsumerWithExceptions}.
 * @param <E> The type of exception which may be thrown by the {@link ConsumerWithExceptions}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class UnsafeConsumer<T, E extends Exception> implements Consumer<T> {

  /**
   * The {@link Consumer} which may throw an exception of type {@link E}.
   */
  private final ConsumerWithExceptions<T, E> consumer;

  /**
   * A {@link ConsumerWithExceptions} which rethrows {@link Exception Exceptions} as
   * {@link RuntimeException RuntimeExceptions}.
   *
   * @param argument The first argument to consume.
   */
  public void accept(final T argument) {
    try {
      consumer.accept(argument);
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
