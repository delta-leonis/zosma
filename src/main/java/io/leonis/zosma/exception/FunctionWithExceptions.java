package io.leonis.zosma.exception;

import java.util.function.Function;

/**
 * The Interface FunctionWithExceptions.
 *
 * This interface represents a {@link Function} which may throw an {@link Exception}.
 *
 * @param <I> The type of argument passed to the {@link Function}.
 * @param <O> The type of object returned by the {@link Function}.
 * @param <E> The type of exception which may be thrown by the {@link Function}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface FunctionWithExceptions<I, O, E extends Exception> {

  /**
   * A {@link Function} which may throw an exception.
   *
   * @param input The input to the {@link Function}.
   * @return The result of applying this {@link Function} to the supplied input.
   * @throws E The type of exception which may be thrown by the {@link Function}.
   */
  O apply(final I input) throws E;
}
