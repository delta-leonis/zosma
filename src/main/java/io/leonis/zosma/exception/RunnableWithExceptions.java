package io.leonis.zosma.exception;

/**
 * The Interface RunnableWithExceptions.
 *
 * This interface represents a {@link Runnable} which may throw an {@link Exception}.
 *
 * @param <E> The type of exception which may be thrown by the {@link Runnable}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface RunnableWithExceptions<E extends Exception> {

  /**
   * A {@link Runnable} which may throw an exception.
   *
   * @throws E The type of exception which may be thrown by the {@link Runnable}.
   */
  void run() throws E;
}
