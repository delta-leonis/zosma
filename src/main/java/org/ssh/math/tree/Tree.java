package org.ssh.math.tree;

import java.util.Collection;

/**
 * The Interface Tree.
 *
 * @author Rimon Oz
 */
public interface Tree<V, N extends Tree.Node<V>> extends Graph<V, N> {
    /**
     * Returns the root {@link Node} of the tree.
     *
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
         * Returns the node's children.
         *
         * @return The node's children.
         */
        Collection<? extends Node<V>> getChildren();
    }
}
