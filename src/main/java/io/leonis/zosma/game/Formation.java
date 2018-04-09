package io.leonis.zosma.game;

/**
 * The Interface Formation.
 *
 * This interface describes the functionality of a formation, that is a structured ordering of
 * agents (or other entities) in some space.
 *
 * @param <A> The identity of entity in the formation.
 * @param <V> The type of position vector.
 * @author Rimon Oz
 */
public interface Formation<A, V> {

  /**
   * @param identity The identity to find a formation vector for.
   * @return The formation vector for the specified entity.
   */
  V getFormationFor(A identity);
}
