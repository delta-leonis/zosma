package org.ssh.math.tree;

import java.util.Set;

/**
 * The Interface Graph.
 *
 * This interface describes the functionality of a graph.
 *
 * @param <V> The type of object which is store in the graph.
 * @param <N> The type of node which is used to wrap objects stored in the graph.
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Graph<V, N extends Graph.Node<V>> {

  /**
   * @return The root {@link Node} of the tree.
   */
  Set<N> getNodes();

  /**
   * The Interface Node.
   * <p>
   * This interface describes the functionality of a {@link Graph} node.
   *
   * @param <V> The type of object held by the node.
   */
  @FunctionalInterface
  interface Node<V> {

    /**
     * @return The value embedded in the node.
     */
    V getValue();
  }
}
