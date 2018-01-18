package io.leonis.zosma.exception;

import java.util.function.Supplier;
import lombok.Value;

/**
 * The Class UnsafeSupplier.
 *
 * This class represents a {@link SupplierWithExceptions} which rethrows
 * {@link Exception Exceptions} as {@link RuntimeException RuntimeExceptions}.
 *
 * @param <S> The type of object returned by the {@link SupplierWithExceptions}.
 * @param <E> The type of exception which may be thrown by the {@link SupplierWithExceptions}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class UnsafeSupplier<S, E extends Exception> implements Supplier<S> {

  /**
   * The {@link Supplier} which may throw an exception of type {@link E}.
   */
  private final SupplierWithExceptions<S, E> supplier;

  /**
   * A {@link SupplierWithExceptions} which may throw an exception.
   *
   * @return The object produced by the {@link SupplierWithExceptions}.
   */
  public S get() {
    try {
      return supplier.get();
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
