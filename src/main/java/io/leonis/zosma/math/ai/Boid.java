package io.leonis.zosma.math.ai;

import io.leonis.zosma.math.Spatial;
import io.leonis.zosma.math.spatial.Moving;
import java.util.Set;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface Boid.
 *
 * This interface represents the functionality of a flocking algorithm.
 *
 * @author Rimon Oz
 */
public interface Boid<O extends Spatial & Moving> {

  /**
   * Returns a velocity vector for a specific boid, given a set of other boids and tuning
   * parameters.
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
