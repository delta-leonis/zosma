package org.ssh.math.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility class to handle checked exceptions in lambdas.
 * <p>
 * From
 * <a href="http://stackoverflow.com/questions/27644361/how-can-i-throw-checked-exceptions-from-inside-java-8-streams">
 * How can I throw CHECKED exceptions from inside Java 8 streams?</a>.
 * This class helps to handle checked exceptions with lambdas.
 *
 * @author http://stackoverflow.com/users/3411681/marcg
 * @author Rimon Oz
 */
public final class LambdaExceptions {
    /**
     * Rethrows exceptions caught in a {@link RunnableWithExceptions} as
     * unchecked exceptions.
     *
     * @param runnable The consumer to run.
     * @param <E>      The type of exception thrown by the consumer
     * @return A new consumer which rethrows exceptions as unchecked
     * exceptions.
     */
    public static <E extends Exception> Runnable rethrowRunnable(
            RunnableWithExceptions<E> runnable
    ) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception exception) {
                throwAsUnchecked(exception);
            }
        };
    }

    /**
     * Rethrows exceptions caught in a {@link ConsumerWithExceptions} as
     * unchecked exceptions.
     *
     * @param consumer The consumer to run.
     * @param <T>      The type of object accepted by the consumer.
     * @param <E>      The type of exception thrown by the consumer
     * @return A new consumer which rethrows exceptions as unchecked
     * exceptions.
     */
    public static <T, E extends Exception> Consumer<T> rethrowConsumer(
            ConsumerWithExceptions<T, E> consumer
    ) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
            }
        };
    }

    /**
     * Rethrows exceptions caught in a {@link BiConsumerWithExceptions} as
     * unchecked exceptions.
     *
     * @param biConsumer The bi-consumer to run.
     * @param <T>        The type of object accepted by the bi-consumer in the
     *                   first argument.
     * @param <U>        The type of object accepted by the bi-consumer in the
     *                   second argument.
     * @param <E>        The type of exception thrown by the bi-consumer.
     * @return A new bi-consumer which rethrows exceptions as
     * unchecked exceptions.
     */
    public static <T, U, E extends Exception> BiConsumer<T, U> rethrowBiConsumer(
            BiConsumerWithExceptions<T, U, E> biConsumer
    ) {
        return (t, u) -> {
            try {
                biConsumer.accept(t, u);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
            }
        };
    }

    /**
     * Rethrows exceptions caught in a {@link FunctionWithExceptions} as
     * unchecked exceptions.
     *
     * @param function The function to run.
     * @param <T>      The type of object accepted by the function.
     * @param <R>      The type of object returned by the function.
     * @param <E>      The type of exception thrown by the function.
     * @return A new function which rethrows exceptions as unchecked
     * exceptions.
     */
    public static <T, R, E extends Exception> Function<T, R> rethrowFunction(
            FunctionWithExceptions<T, R, E> function
    ) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
                return null;
            }
        };
    }

    /**
     * Rethrows exceptions caught in a {@link SupplierWithExceptions} as
     * unchecked exceptions.
     *
     * @param function The supplier to run.
     * @param <T>      The type of object returned by the supplier.
     * @param <E>      The type of exception thrown by the supplier.
     * @return A new supplier which rethrows exceptions as unchecked
     * exceptions.
     */
    public static <T, E extends Exception> Supplier<T> rethrowSupplier(
            SupplierWithExceptions<T, E> function
    ) {
        return () -> {
            try {
                return function.get();
            } catch (Exception exception) {
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
    public static void uncheck(RunnableWithExceptions target) {
        try {
            target.run();
        } catch (Exception exception) {
            throwAsUnchecked(exception);
        }
    }

    /**
     * Rethrows exceptions caught in a {@link SupplierWithExceptions} as
     * unchecked exceptions.
     *
     * @param supplier The supplier to run.
     * @param <R>      The type of object accepted by the supplier.
     * @param <E>      The type of exception thrown by the supplier.
     * @return A new supplier which rethrows exceptions as unchecked
     * exceptions.
     */
    public static <R, E extends Exception> R uncheck(
            SupplierWithExceptions<R, E> supplier
    ) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            throwAsUnchecked(exception);
            return null;
        }
    }

    /**
     * Rethrows exceptions caught in a {@link FunctionWithExceptions} as
     * unchecked exceptions.
     *
     * @param function The function to run.
     * @param target   The argument to the function.
     * @param <T>      The type of object accepted by the function.
     * @param <R>      The type of object accepted by the function.
     * @param <E>      The type of exception thrown by the function.
     * @return A new function which rethrows exceptions as unchecked
     * exceptions.
     */
    public static <T, R, E extends Exception> R uncheck(
            FunctionWithExceptions<T, R, E> function,
            T target
    ) {
        try {
            return function.apply(target);
        } catch (Exception exception) {
            throwAsUnchecked(exception);
            return null;
        }
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
            Exception exception
    ) throws E {
        throw (E) exception;
    }

    /**
     * See {@link Consumer}.
     *
     * @param <T> The type of object accepted by this consumer.
     * @param <E> The type of exception thrown by this consumer.
     */
    @FunctionalInterface
    public interface ConsumerWithExceptions<T, E extends Exception> {
        void accept(T t) throws E;
    }

    /**
     * See {@link BiConsumer}.
     *
     * @param <T> The type of object accepted by this consumer in the first
     *            argument.
     * @param <U> The type of object accepted by this consumer in the second
     *            argument.
     * @param <E> The type of exception thrown by this
     *            consumer.
     */
    @FunctionalInterface
    public interface BiConsumerWithExceptions<T, U, E extends Exception> {
        void accept(T t, U u) throws E;
    }

    /**
     * See {@link Function}.
     *
     * @param <T> The type of object accepted by this function.
     * @param <R> The type of object return by this function.
     * @param <E> The type of exception thrown by this function.
     */
    @FunctionalInterface
    public interface FunctionWithExceptions<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    /**
     * See {@link Supplier}.
     *
     * @param <T> The type of object returned by this supplier.
     * @param <E> The type of exception thrown by this supplier.
     */
    @FunctionalInterface
    public interface SupplierWithExceptions<T, E extends Exception> {
        T get() throws E;
    }

    /**
     * See {@link Runnable}.
     *
     * @param <E> The type of exception thrown by this runnable.
     */
    @FunctionalInterface
    public interface RunnableWithExceptions<E extends Exception> {
        void run() throws E;
    }
}
