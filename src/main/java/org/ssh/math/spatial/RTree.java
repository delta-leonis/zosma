package org.ssh.math.spatial;

import java.util.Map;
import java.util.Set;
import org.ssh.math.tree.Tree;

/**
 * The Interface RTree.
 *
 * @param <V> the type parameter
 * @param <O> the type parameter
 * @author Rimon Oz
 */
public interface RTree<V, O> extends Tree<O, RTree.Node<V, O>> {

  /**
   * Gets facilities.
   *
   * @return the facilities
   */
  Set<Node<V, O>> getFacilities();

  /**
   * Gets nearest neighbor.
   *
   * @param object the object
   * @return the nearest neighbor
   */
  RTree.Node<V, O> getNearestNeighbor(O object);

  /**
   * Gets nearest neighbors.
   *
   * @param objects the objects
   * @return the nearest neighbors
   */
  Map<V, Node<V, O>> getNearestNeighbors(Set<O> objects);

  /**
   * Gets maximum nearest neighbor distance.
   *
   * @param facility the facility
   * @return the maximum nearest neighbor distance
   */
  double getMaximumNearestNeighborDistance(O facility);

  /**
   * Gets maximum nearest neighbor distance.
   *
   * @param facility the facility
   * @return the maximum nearest neighbor distance
   */
  double getMaximumNearestNeighborDistance(Set<O> facility);

  /**
   * The interface Node.
   *
   * @param <V> the type parameter
   * @param <O> the type parameter
   */
  interface Node<V, O> extends Tree.Node<O> {

    /**
     * Gets position.
     *
     * @return the position
     */
    V getPosition();

    /**
     * Gets size.
     *
     * @return the size
     */
    V getSize();
  }
}
