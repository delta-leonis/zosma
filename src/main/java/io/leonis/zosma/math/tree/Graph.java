package io.leonis.zosma.math.tree;

import java.util.Set;

/**
 * The Interface Graph.
 *
 * This interface describes the functionality of a graph.
 *
 * @param <V> The type of object which is stored in the graph.
 * @author Rimon Oz
 */
public interface Graph<V> {

  /**
   * @return The nodes in the tree as a set.
   */
  Set<V> getNodes();
}
