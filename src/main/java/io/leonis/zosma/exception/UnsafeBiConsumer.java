package io.leonis.zosma.exception;

import java.util.function.BiConsumer;
import lombok.Value;

/**
 * The Class UnsafeBiConsumer.
 *
 * This class represents a {@link BiConsumerWithExceptions} which rethrows
 * {@link Exception Exceptions} as {@link RuntimeException RuntimeExceptions}.
 *
 * @param <T> The type of the first argument passed to the {@link BiConsumerWithExceptions}.
 * @param <U> The type of the second argument passed to the {@link BiConsumerWithExceptions}.
 * @param <E> The type of exception which may be thrown by the {@link BiConsumerWithExceptions}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class UnsafeBiConsumer<T, U, E extends Exception> implements BiConsumer<T, U> {

  /**
   * The {@link BiConsumer} which may throw an exception of type {@link E}.
   */
  private final BiConsumerWithExceptions<T, U, E> biConsumer;

  /**
   * A {@link BiConsumerWithExceptions} which may throw an exception.
   *
   * @param firstArgument  The first argument to consume.
   * @param secondArgument The second argument to consume.
   */
  public void accept(final T firstArgument, final U secondArgument) {
    try {
      biConsumer.accept(firstArgument, secondArgument);
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
