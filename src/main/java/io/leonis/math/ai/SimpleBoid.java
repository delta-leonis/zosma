package io.leonis.math.ai;

import io.leonis.math.Spatial;
import io.leonis.math.spatial.Moving;
import java.util.Set;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Class SimpleBoid.
 *
 * This class contains the functionality for a simple boids algorithm. The implementation is based
 * on the notes and pseudocode found <a href="http://www.kfish.org/boids/pseudocode.html">here</a>.
 *
 * @author Rimon Oz
 */
public final class SimpleBoid<O extends Spatial & Moving> implements Boid<O> {

  @Override
  public INDArray respond(
      final O currentBoid,
      final Set<? extends O> otherBoids,
      final double avoidanceScale,
      final double velocityRange,
      final double centeringScale
  ) {
    return this.collisionAvoidance(currentBoid, otherBoids, avoidanceScale)
        .add(this.velocityMatching(currentBoid, otherBoids, velocityRange))
        .add(this.flockCentering(currentBoid, otherBoids, centeringScale));
  }

  /**
   * Returns a velocity vector for a specific boid, given a set of other boids and scaling factor,
   * such that the boid will avoid collisions.
   *
   * @param currentBoid   The boid to compute the velocity vector for.
   * @param otherBoids    A set of {@link Boid}s.
   * @param scalingFactor The tuning parameter for the collision avoidance rule. High values result
   *                      in large vectors.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the specific boid steers away from other boids in the flock.
   */
  private <O extends Spatial & Moving> INDArray collisionAvoidance(
      final O currentBoid,
      final Set<? extends O> otherBoids,
      final double scalingFactor
  ) {
    return otherBoids.stream()
        .filter(boid -> !boid.equals(currentBoid))
        .map(O::getPosition)
        .reduce(
            Nd4j.create(currentBoid.getPosition().shape()),
            INDArray::add)
        .div(otherBoids.size())
        .sub(currentBoid.getPosition())
        .mul(scalingFactor);
  }

  /**
   * Returns a velocity vector for a specific boid, given a set of other boids and a range, such
   * that the boid will match its velocity vector with other boids within that range.
   *
   * @param currentBoid The boid to compute the velocity vector for.
   * @param otherBoids  A set of {@link Boid}s.
   * @param range       The range within which boids will be taken into account in computing the
   *                    velocity vector.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the specific boid matches its velocity vector with other boids within its range.
   */
  private <O extends Spatial & Moving> INDArray velocityMatching(
      final O currentBoid,
      final Set<? extends O> otherBoids,
      final double range
  ) {
    return otherBoids.stream()
        .filter(boid -> !boid.equals(currentBoid))
        .map(O::getVelocity)
        .filter(boid ->
            boid.distance2(currentBoid.getPosition()) < range)
        .reduce(
            Nd4j.create(currentBoid.getPosition().shape()),
            (velocity, nextBoid)
                -> velocity.sub(
                nextBoid.sub(
                    currentBoid.getVelocity())));
  }

  /**
   * Returns a velocity vector for a specific boid, given a set of other boids and a scaling factor,
   * such that the flock will stay together.
   *
   * @param currentBoid   The boid to compute the velocity vector for.
   * @param otherBoids    A set of {@link Boid}s.
   * @param scalingFactor The tuning parameter for the centering rule. High values result in larger
   *                      spacing between boids.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the flock stays together.
   */
  private <O extends Spatial & Moving> INDArray flockCentering(
      final O currentBoid,
      final Set<? extends O> otherBoids,
      final double scalingFactor
  ) {
    return otherBoids.stream()
        .filter(boid -> !boid.equals(currentBoid))
        .map(Moving::getVelocity)
        .reduce(
            Nd4j.create(currentBoid.getPosition().shape()),
            INDArray::add)
        .div(otherBoids.size())
        .mul(scalingFactor);
  }
}
