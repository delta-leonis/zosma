package io.leonis.zosma.game;

/**
 * The Interface Formation.
 *
 * This interface describes the functionality of a formation, that is a structured ordering of
 * agents (or other entities) in some space.
 *
 * @param <A> The type of entity in the formation.
 * @param <V> The type of position vector.
 * @author Rimon Oz
 */
public interface Formation<A extends Identity.Supplier, V> {

  /**
   * @param entity The entity to find a formation vector for.
   * @return The formation vector for the specified entity.
   */
  V getFormationFor(A entity);

  interface Supplier<F extends Formation> {
    F getFormation();
  }
}
