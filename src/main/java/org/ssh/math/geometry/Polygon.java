package org.ssh.math.geometry;

import java.util.Set;

/**
 * The Interface Polygon.
 * <p>
 * This interface describes the functionality of a polygon, that is a plane figure with at least
 * three sides and angles.
 *
 * @param <V> The type of vector.
 * @author Rimon Oz
 */
public interface Polygon<V> {

  /**
   * Returns the set of vertices of the polygon.
   *
   * @return The set of vertices of the polygon.
   */
  Set<V> getVertices();
}
