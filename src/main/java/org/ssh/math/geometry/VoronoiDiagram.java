package org.ssh.math.geometry;

/**
 * The Interface VoronoiDiagram.
 *
 * This interface describes the functionality of a Voronoi diagram.
 *
 * @param <V> The type of vector.
 * @param <O> The type of Voronoi-cell.
 * @author Rimon Oz
 */
public interface VoronoiDiagram<V, O extends Polygon<V>> {

  /**
   * @param vector The vector to find a matching Voronoi-cell for.
   * @return The Voronoi-cell to which the endpoint of the supplied vector belongs.
   */
  O getCellFor(final V vector);
}
