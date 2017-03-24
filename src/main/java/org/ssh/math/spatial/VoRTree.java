package org.ssh.math.spatial;

import org.ssh.math.geometry.Polygon;
import org.ssh.math.geometry.VoronoiDiagram;

import java.util.Set;

/**
 * MINDIST, MINMAXDIST, http://www.postgis.org/support/nearestneighbor.pdf
 * NXNDIST: http://www.cse.unsw.edu.au/~yingz/papers/2013_dassfa_AVRtree.pdf
 */
public interface VoRTree<V, O, F extends Polygon<V>> extends RTree<V, O>, VoronoiDiagram<V, Polygon<V>> {
    F getVoronoiBoundingRectangle(O facility);

    F getVoronoiBoundingRectangle(Set<O> facility);
}
