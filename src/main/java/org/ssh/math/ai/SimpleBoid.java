package org.ssh.math.ai;

import java.util.Set;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.ssh.math.geometry.motion.MovingObject;

/**
 * The Class SimpleBoid.
 *
 * This class contains the functionality for a simple boids algorithm. The
 * implementation is based on the notes and pseudocode found
 * <a href="http://www.kfish.org/boids/pseudocode.html">here</a>.
 *
 * @author Rimon Oz
 */
public interface SimpleBoid extends Boid<INDArray> {

  @Override
  default INDArray respond(
      final MovingObject<INDArray> currentBoid,
      final Set<? extends MovingObject<INDArray>> otherBoids,
      final double avoidanceScale,
      final double velocityRange,
      final double centeringScale
  ) {
    return SimpleBoid.collisionAvoidance(currentBoid, otherBoids, avoidanceScale)
        .add(SimpleBoid.velocityMatching(currentBoid, otherBoids, velocityRange))
        .add(SimpleBoid.flockCentering(currentBoid, otherBoids, centeringScale));
  }

  /**
   * Returns a velocity vector for a specific boid, given a set of other boids
   * and scaling factor, such that the boid will avoid collisions.
   *
   * @param currentBoid   The boid to compute the velocity vector for.
   * @param otherBoids    A set of {@link Boid}s.
   * @param scalingFactor The tuning parameter for the collision avoidance rule. High values result
   *                      in large vectors.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the specific boid steers away from other boids in the flock.
   */
  static INDArray collisionAvoidance(
      final MovingObject<INDArray> currentBoid,
      final Set<? extends MovingObject<INDArray>> otherBoids,
      final double scalingFactor
  ) {
    return otherBoids.stream()
        .filter(boid -> !boid.equals(currentBoid))
        .map(MovingObject::getPosition)
        .reduce(
            Nd4j.create(currentBoid.getPosition().rows(),
                currentBoid.getPosition().columns()),
            INDArray::add)
        .div(otherBoids.size())
        .sub(currentBoid.getPosition())
        .mul(scalingFactor);
  }

  /**
   * Returns a velocity vector for a specific boid, given a set of other
   * boids and a range, such that the boid will match its velocity vector
   * with other boids within that range.
   *
   * @param currentBoid The boid to compute the velocity vector for.
   * @param otherBoids  A set of {@link Boid}s.
   * @param range       The range within which boids will be taken into account in computing the
   *                    velocity vector.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the specific boid matches its velocity vector with other boids within its range.
   */
  static INDArray velocityMatching(
      final MovingObject<INDArray> currentBoid,
      final Set<? extends MovingObject<INDArray>> otherBoids,
      final double range
  ) {
    return otherBoids.stream()
        .filter(boid -> !boid.equals(currentBoid))
        .map(MovingObject::getPosition)
        .filter(boid ->
            boid.distance2(currentBoid.getPosition()) < range)
        .reduce(
            Nd4j.create(
                currentBoid.getPosition().rows(),
                currentBoid.getPosition().columns()),
            (velocity, nextBoid)
                -> velocity.sub(
                nextBoid.sub(
                    currentBoid.getPosition())));
  }

  /**
   * Returns a velocity vector for a specific boid, given a set of other
   * boids and a scaling factor, such that the flock will stay together.
   *
   * @param currentBoid   The boid to compute the velocity vector for.
   * @param otherBoids    A set of {@link Boid}s.
   * @param scalingFactor The tuning parameter for the centering rule. High values result in larger
   *                      spacing between boids.
   * @return A velocity vector representing the suggested velocity for the specified boid such that
   * the flock stays together.
   */
  static INDArray flockCentering(
      final MovingObject<INDArray> currentBoid,
      final Set<? extends MovingObject<INDArray>> otherBoids,
      final double scalingFactor
  ) {
    return otherBoids.stream()
        .filter(boid -> !boid.equals(currentBoid))
        .map(MovingObject::getVelocity)
        .reduce(
            Nd4j.create(
                currentBoid.getPosition().rows(),
                currentBoid.getPosition().columns()),
            INDArray::add)
        .div(otherBoids.size())
        .mul(scalingFactor);
  }
}
