package org.ssh.math.spatial;

import org.ssh.math.tree.Tree;

import java.util.Map;
import java.util.Set;

/**
 * The Interface RTree.
 *
 * @author Rimon Oz
 */
public interface RTree<V, O> extends Tree<O, RTree.Node<V, O>> {
    Set<Node<V, O>> getFacilities();

    RTree.Node<V, O> getNearestNeighbor(O object);

    Map<V, Node<V, O>> getNearestNeighbors(Set<O> objects);

    double getMaximumNearestNeighborDistance(O facility);

    double getMaximumNearestNeighborDistance(Set<O> facility);

    interface Node<V, O> extends Tree.Node<O> {
        V getPosition();

        V getSize();
    }
}
