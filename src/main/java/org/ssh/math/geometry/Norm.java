package org.ssh.math.geometry;

/**
 * The Interface Norm.
 * <p>
 * This interface describes the functionality of a norm, ie. a measure of distance.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Norm {
    /**
     * Calculates the norm of the supplied vector.
     *
     * @param <V>   The type of vector
     * @param <N>   the type parameter
     * @param input the input
     * @return the double
     */
    <V extends Iterable<N>, N extends Number> double calculateNorm(V input);
}
