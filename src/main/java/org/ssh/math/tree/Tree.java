package org.ssh.math.tree;

import java.util.Collection;

/**
 * The Interface Tree.
 *
 * @param <V> The type of object stored inside the {@link Tree.Node}.
 * @param <N> The type of {@link Tree.Node}.
 * @author Rimon Oz
 */
public interface Tree<V, N extends Tree.Node<V>> extends Graph<V, N> {

  /**
   * @return The root {@link Node} of the tree.
   */
  N getRoot();

  /**
   * The Interface Node.
   * <p>
   * This interface describes the functionality of a {@link Tree} node.
   *
   * @param <V> The type of object held by the node.
   */
  interface Node<V> extends Graph.Node<V> {

    /**
     * @return The node's children.
     */
    Collection<? extends Node<V>> getChildren();
  }
}
