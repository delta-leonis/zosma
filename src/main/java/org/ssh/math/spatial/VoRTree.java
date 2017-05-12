package org.ssh.math.spatial;

import java.util.Set;
import org.ssh.math.geometry.*;

/**
 * The Interface VoRTree.
 *
 * MINDIST, MINMAXDIST, http://www.postgis.org/support/nearestneighbor.pdf
 * NXNDIST: http://www.cse.unsw.edu.au/~yingz/papers/2013_dassfa_AVRtree.pdf
 *
 * @param <V> The type of vector.
 * @param <O> The type of object stored in the {@link VoRTree.Node}.
 * @param <F> The type of Voronoi-polygon.
 */
public interface VoRTree<V, O, F extends Polygon<V>>
    extends RTree<V, O>, VoronoiDiagram<V, Polygon<V>> {

  /**
   * @param facility The facility to find the bounding polygon of.
   * @return The bounding polygon of the facility.
   */
  F getVoronoiBound(final O facility);

  /**
   * @param facility The facilities to find the bounding polygon of.
   * @return The bounding polygon of the facilities.
   */
  F getVoronoiBound(final Set<O> facility);
}
