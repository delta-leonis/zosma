package io.leonis.zosma.exception;

import lombok.Value;

/**
 * The Class UnsafeRunnable.
 *
 * This class represents a {@link RunnableWithExceptions} which rethrows
 * {@link Exception Exceptions} as {@link RuntimeException RuntimeExceptions}.
 *
 * @param <E> The type of exception which may be thrown by the {@link RunnableWithExceptions}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class UnsafeRunnable<E extends Exception> implements Runnable {

  /**
   * The {@link Runnable} which may throw an exception of type {@link E}.
   */
  private final RunnableWithExceptions<E> runnable;

  /**
   * A {@link RunnableWithExceptions} which may throw an exception.
   */
  public void run() {
    try {
      runnable.run();
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
