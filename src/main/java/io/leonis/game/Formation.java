package io.leonis.game;

/**
 * The Interface Formation.
 *
 * This interface describes the functionality of a formation, that is a structured ordering of
 * {@link Agent} in a coordinate system.
 *
 * @param <V> The type of position vector.
 * @param <A> The type of {@link Agent} in the formation.
 * @author Rimon Oz
 */
public interface Formation<V, A extends Agent> {

  /**
   * @param agent The {@link Agent} to find a formation vector for.
   * @return The formation vector for the specified {@link Agent}.
   */
  V getFormationFor(A agent);

  interface Supplier<F extends Formation> {
    F getFormation();
  }
}
