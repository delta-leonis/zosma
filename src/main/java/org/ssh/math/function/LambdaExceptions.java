package org.ssh.math.function;

import java.util.function.*;

/**
 * Utility class to handle checked exceptions in lambdas.
 *
 * From <a href="http://stackoverflow.com/questions/27644361/how-can-i-throw-checked-exceptions-from-inside-java-8-streams">
 * How can I throw CHECKED exceptions from inside Java 8 streams?</a>. This class helps to handle
 * checked exceptions with lambdas.
 *
 * @author http ://stackoverflow.com/users/3411681/marcg
 * @author Rimon Oz
 */
public final class LambdaExceptions {

  /**
   * Rethrows exceptions caught in a {@link RunnableWithExceptions} as unchecked exceptions.
   *
   * @param <E>      The type of exception thrown by the consumer
   * @param runnable The consumer to run.
   * @return A new consumer which rethrows exceptions as unchecked exceptions.
   */
  public static <E extends Exception> Runnable rethrowRunnable(
      final RunnableWithExceptions<E> runnable
  ) {
    return () -> {
      try {
        runnable.run();
      } catch (final Exception exception) {
        throwAsUnchecked(exception);
      }
    };
  }

  /**
   * Rethrows an exception.
   *
   * @param exception The exception to throw.
   * @param <E>       The type of exception which needs to be thrown.
   * @throws E The exception cast to the specified type.
   */
  @SuppressWarnings("unchecked")
  private static <E extends Throwable> void throwAsUnchecked(
      final Exception exception
  ) throws E {
    throw (E) exception;
  }

  /**
   * Rethrows exceptions caught in a {@link ConsumerWithExceptions} as unchecked exceptions.
   *
   * @param <T>      The type of object accepted by the consumer.
   * @param <E>      The type of exception thrown by the consumer
   * @param consumer The consumer to run.
   * @return A new consumer which rethrows exceptions as unchecked exceptions.
   */
  public static <T, E extends Exception> Consumer<T> rethrowConsumer(
      final ConsumerWithExceptions<T, E> consumer
  ) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (final Exception exception) {
        throwAsUnchecked(exception);
      }
    };
  }

  /**
   * Rethrows exceptions caught in a {@link BiConsumerWithExceptions} as unchecked exceptions.
   *
   * @param <T>        The type of object accepted by the bi-consumer in the first argument.
   * @param <U>        The type of object accepted by the bi-consumer in the second argument.
   * @param <E>        The type of exception thrown by the bi-consumer.
   * @param biConsumer The bi-consumer to run.
   * @return A new bi-consumer which rethrows exceptions as unchecked exceptions.
   */
  public static <T, U, E extends Exception> BiConsumer<T, U> rethrowBiConsumer(
      final BiConsumerWithExceptions<T, U, E> biConsumer
  ) {
    return (t, u) -> {
      try {
        biConsumer.accept(t, u);
      } catch (final Exception exception) {
        throwAsUnchecked(exception);
      }
    };
  }

  /**
   * Rethrows exceptions caught in a {@link FunctionWithExceptions} as unchecked exceptions.
   *
   * @param <T>      The type of object accepted by the function.
   * @param <R>      The type of object returned by the function.
   * @param <E>      The type of exception thrown by the function.
   * @param function The function to run.
   * @return A new function which rethrows exceptions as unchecked exceptions.
   */
  public static <T, R, E extends Exception> Function<T, R> rethrowFunction(
      final FunctionWithExceptions<T, R, E> function
  ) {
    return t -> {
      try {
        return function.apply(t);
      } catch (final Exception exception) {
        throwAsUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * Rethrows exceptions caught in a {@link SupplierWithExceptions} as unchecked exceptions.
   *
   * @param <T>      The type of object returned by the supplier.
   * @param <E>      The type of exception thrown by the supplier.
   * @param function The supplier to run.
   * @return A new supplier which rethrows exceptions as unchecked exceptions.
   */
  public static <T, E extends Exception> Supplier<T> rethrowSupplier(
      final SupplierWithExceptions<T, E> function
  ) {
    return () -> {
      try {
        return function.get();
      } catch (final Exception exception) {
        throwAsUnchecked(exception);
        return null;
      }
    };
  }

  /**
   * Rethrows exceptions caught in a {@link Runnable} as unchecked exceptions.
   *
   * @param target The runnable to run.
   */
  public static void uncheck(final RunnableWithExceptions target) {
    try {
      target.run();
    } catch (final Exception exception) {
      throwAsUnchecked(exception);
    }
  }

  /**
   * Rethrows exceptions caught in a {@link SupplierWithExceptions} as unchecked exceptions.
   *
   * @param <R>      The type of object accepted by the supplier.
   * @param <E>      The type of exception thrown by the supplier.
   * @param supplier The supplier to run.
   * @return A new supplier which rethrows exceptions as unchecked exceptions.
   */
  public static <R, E extends Exception> R uncheck(
      final SupplierWithExceptions<R, E> supplier
  ) {
    try {
      return supplier.get();
    } catch (final Exception exception) {
      throwAsUnchecked(exception);
      return null;
    }
  }

  /**
   * Rethrows exceptions caught in a {@link FunctionWithExceptions} as unchecked exceptions.
   *
   * @param <T>      The type of object accepted by the function.
   * @param <R>      The type of object accepted by the function.
   * @param <E>      The type of exception thrown by the function.
   * @param function The function to run.
   * @param target   The argument to the function.
   * @return A new function which rethrows exceptions as unchecked exceptions.
   */
  public static <T, R, E extends Exception> R uncheck(
      final FunctionWithExceptions<T, R, E> function,
      final T target
  ) {
    try {
      return function.apply(target);
    } catch (final Exception exception) {
      throwAsUnchecked(exception);
      return null;
    }
  }

  /**
   * See {@link Consumer}.
   *
   * @param <T> The type of object accepted by this consumer.
   * @param <E> The type of exception thrown by this consumer.
   */
  public interface ConsumerWithExceptions<T, E extends Exception> {

    /**
     * Accept.
     *
     * @param t the t
     * @throws E the e
     */
    void accept(T t) throws E;
  }

  /**
   * See {@link BiConsumer}.
   *
   * @param <T> The type of object accepted by this consumer in the first argument.
   * @param <U> The type of object accepted by this consumer in the second argument.
   * @param <E> The type of exception thrown by this consumer.
   */
  public interface BiConsumerWithExceptions<T, U, E extends Exception> {

    /**
     * Accept.
     *
     * @param t the t
     * @param u the u
     * @throws E the e
     */
    void accept(T t, U u) throws E;
  }

  /**
   * See {@link Function}.
   *
   * @param <T> The type of object accepted by this function.
   * @param <R> The type of object return by this function.
   * @param <E> The type of exception thrown by this function.
   */
  public interface FunctionWithExceptions<T, R, E extends Exception> {

    /**
     * Apply r.
     *
     * @param t the t
     * @return the r
     * @throws E the e
     */
    R apply(T t) throws E;
  }

  /**
   * See {@link Supplier}.
   *
   * @param <T> The type of object returned by this supplier.
   * @param <E> The type of exception thrown by this supplier.
   */
  public interface SupplierWithExceptions<T, E extends Exception> {

    /**
     * Get t.
     *
     * @return the t
     * @throws E the e
     */
    T get() throws E;
  }

  /**
   * See {@link Runnable}.
   *
   * @param <E> The type of exception thrown by this runnable.
   */
  public interface RunnableWithExceptions<E extends Exception> {

    /**
     * Run.
     *
     * @throws E the e
     */
    void run() throws E;
  }
}
