package org.ssh.math.ai;

import org.ssh.math.geometry.motion.MovingObject;

import java.util.Set;

/**
 * The Interface Boid.
 * <p>
 * This interface represents the functionality of a flocking algorithm.
 *
 * @param <T> The type of vector.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Boid<T> {
    /**
     * Returns a velocity vector for a specific boid, given a set of other
     * boids and tuning parameters.
     *
     * @param otherBoids     A set of {@link Boid}s.
     * @param currentBoid    The boid to compute the velocity vector for.
     * @param avoidanceScale The tuning parameter for the collision avoidance
     *                       rule.
     * @param velocityRange  The tuning parameter for the velocity matching
     *                       rule.
     * @param centeringScale The tuning parameter for the flock centering rule.
     * @return               A velocity vector representing the suggested
     *                       velocity for the specified boid
     *                       such that the flock exhibits flocking behavior.
     */
    T respond(
            MovingObject<T> currentBoid,
            Set<? extends MovingObject<T>> otherBoids,
            double avoidanceScale,
            double velocityRange,
            double centeringScale);
}
