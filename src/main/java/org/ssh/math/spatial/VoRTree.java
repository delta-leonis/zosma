package org.ssh.math.spatial;

import java.util.Set;
import org.ssh.math.geometry.Polygon;
import org.ssh.math.geometry.VoronoiDiagram;

/**
 * MINDIST, MINMAXDIST, http://www.postgis.org/support/nearestneighbor.pdf
 * NXNDIST: http://www.cse.unsw.edu.au/~yingz/papers/2013_dassfa_AVRtree.pdf
 *
 * @param <V> the type parameter
 * @param <O> the type parameter
 * @param <F> the type parameter
 */
public interface VoRTree<V, O, F extends Polygon<V>>
    extends RTree<V, O>, VoronoiDiagram<V, Polygon<V>> {

  /**
   * Gets voronoi bounding rectangle.
   *
   * @param facility the facility
   * @return the voronoi bounding rectangle
   */
  F getVoronoiBoundingRectangle(O facility);

  /**
   * Gets voronoi bounding rectangle.
   *
   * @param facility the facility
   * @return the voronoi bounding rectangle
   */
  F getVoronoiBoundingRectangle(Set<O> facility);
}
