package org.ssh.math.geometry;

/**
 * The Interface VoronoiDiagram.
 * <p>
 * This interface describes the functionality of a Voronoi diagram.
 *
 * @param <V> The type of vector.
 * @param <O> The type of Voronoi-cell.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface VoronoiDiagram<V, O extends Polygon<V>> {
    /**
     * Returns the Voronoi-cell to which the endpoint of the supplied vector belongs.
     *
     * @param vector The vector to find a matching Voronoi-cell for.
     * @return       The Voronoi-cell belonging to the vector.
     */
    O getCellFor(V vector);
}
