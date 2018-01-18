package io.leonis.zosma.exception;

import java.util.function.Function;
import lombok.Value;

/**
 * The Class UnsafeFunction.
 *
 * This class represents a {@link FunctionWithExceptions} which rethrows
 * {@link Exception Exceptions} as {@link RuntimeException RuntimeExceptions}.
 *
 * @param <I> The type of argument passed to the {@link FunctionWithExceptions}.
 * @param <O> The type of object returned by the {@link FunctionWithExceptions}.
 * @param <E> The type of exception which may be thrown by the {@link FunctionWithExceptions}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class UnsafeFunction<I, O, E extends Exception> implements Function<I, O> {
  /**
   * The {@link Function} which may throw an exception of type {@link E}.
   */
  private final FunctionWithExceptions<I, O, E> function;

  /**
   * A {@link FunctionWithExceptions} which may throw an exception.
   */
  public O apply(final I input) {
    try {
      return function.apply(input);
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
