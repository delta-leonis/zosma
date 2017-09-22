package org.ssh.math.ai;

import java.util.Set;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.ssh.math.Spatial;
import org.ssh.math.spatial.Moving;

/**
 * The Interface Boid.
 *
 * This interface represents the functionality of a flocking algorithm.
 *
 * @author Rimon Oz
 */
public interface Boid<O extends Spatial & Moving> {

  /**
   * Returns a velocity vector for a specific boid, given a set of other
   * boids and tuning parameters.
   *
   * @param currentBoid    The boid to compute the velocity vector for.
   * @param otherBoids     A set of {@link Boid}s.
   * @param avoidanceScale The tuning parameter for the collision avoidance rule.
   * @param velocityRange  The tuning parameter for the velocity matching rule.
   * @param centeringScale The tuning parameter for the flock centering rule.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the flock exhibits flocking behavior.
   */
  INDArray respond(
      final O currentBoid,
      final Set<? extends O> otherBoids,
      final double avoidanceScale,
      final double velocityRange,
      final double centeringScale);
}
