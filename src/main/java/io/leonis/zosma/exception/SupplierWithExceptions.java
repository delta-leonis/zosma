package io.leonis.zosma.exception;

import java.util.function.Supplier;

/**
 * The Interface SupplierWithExceptions.
 *
 * This interface represents a {@link Supplier} which may throw an {@link Exception}.
 *
 * @param <S> The type of object returned by the {@link Supplier}.
 * @param <E> The type of exception which may be thrown by the {@link Supplier}.
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface SupplierWithExceptions<S, E extends Exception> {

  /**
   * A {@link Supplier} which may throw an exception.
   *
   * @return The object produced by the {@link Supplier}.
   * @throws E The type of exception which may be thrown by the {@link Supplier}.
   */
  S get() throws E;
}
