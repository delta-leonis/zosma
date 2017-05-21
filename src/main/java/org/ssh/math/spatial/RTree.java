package org.ssh.math.spatial;

import java.util.Set;
import org.ssh.math.tree.Tree;

/**
 * The Interface RTree.
 *
 * @param <V> The type of vector.
 * @param <O> The type of object stored in the node.
 * @author Rimon Oz
 */
public interface RTree<V, O> extends Tree<O> {

  /**
   * @return The facilities, or objects in the space.
   */
  Set<O> getFacilities();

  /**
   * @param object The object to find the nearest neighbor of.
   * @return The nearest neighbor of the supplied object.
   */
  O getNearestNeighbor(final O object);

  /**
   * @param objects The object to find the nearest neighbors of.
   * @return The nearest neighbors of the supplied object.
   */
  Set<O> getNearestNeighbors(final Set<O> objects);

  /**
   * @param facility The facility to find the maximum nearest neighbor distance of.
   * @return The maximum nearest neighbor distance of the supplied facility.
   */
  double getMaximumNearestNeighborDistance(final O facility);

  /**
   * @param facility The set of facilities to find the maximum nearest neighbor distance of.
   * @return The maximum nearest neighbor distance of the supplied facilities.
   */
  double getMaximumNearestNeighborDistance(final Set<O> facility);

  /**
   * @return The position vector of the facility.
   */
  V getPosition();

  /**
   * @return The size of the facility.
   */
  V getSize();
}
