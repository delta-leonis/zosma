package org.ssh.math.tree;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lombok.*;

/**
 * The Class RedBlackTree.
 *
 * This class represents an immutable and persistent implementation of a Red-Black Tree
 * which is based on the description provided at
 * http://www.seas.upenn.edu/~cis552/11fa/lectures/RedBlack.html. This implementation of the
 * Red Black tree simultaneously represents a node in the tree as well as the entire tree.
 *
 * @author Rimon Oz
 */
@Value
@EqualsAndHashCode(exclude = "type")
@AllArgsConstructor
public class RedBlackTree<V extends Comparable<V>> implements Tree<V> {
  /**
   * The {@link Type type} of the node.
   */
  private final Type type;
  /**
   * The (optional) left child of the node.
   */
  private final RedBlackTree<V> leftChild;
  /**
   * The value of the node.
   */
  private final V value;
  /**
   * The (optional) right child of the node.
   */
  private final RedBlackTree<V> rightChild;

  /**
   * Constructs a Red Black tree using the supplied array.
   *
   * @param values The array of values to insert into the tree.
   */
  public RedBlackTree(final V... values) {
    this(new ImmutableSet.Builder<V>().add(values).build());
  }

  /**
   * Constructs a Red Black tree using the supplied {@link Set} of values.
   *
   * @param values The set of values to insert into the tree.
   */
  public RedBlackTree(final Set<V> values) {
    final RedBlackTree<V> toBe = values.stream()
        .reduce(new RedBlackTree<V>(
                Type.BLACK,
                null,
                null,
                null),
            (tree, newValue) -> insert(tree, newValue), RedBlackTree::merge);

    this.type = Type.BLACK;
    this.leftChild = toBe.getLeftChild();
    this.value = toBe.getValue();
    this.rightChild = toBe.getRightChild();
  }

  /**
   * Merges two {@link RedBlackTree} into one.
   *
   * @param leftTree  The first tree to merge.
   * @param rightTree The second tree to merge.
   * @param <V>       The type of object in the tree.
   * @return The result of merging the first and second tree.
   */
  public static <V extends Comparable<V>> RedBlackTree<V> merge(
      final RedBlackTree<V> leftTree,
      final RedBlackTree<V> rightTree
  ) {
    return leftTree.getNodes().stream()
        .reduce(rightTree, (node, right) -> insert(node, right), RedBlackTree::merge);
  }

  /**
   * @param toInsertInto The {@link RedBlackTree} to insert a value into.
   * @param value        The value to insert.
   * @param <V>          The type of object in the tree.
   * @return A new {@link RedBlackTree} containing all the nodes in the supplied tree and a new node
   * with the supplied value.
   */
  public static <V extends Comparable<V>> RedBlackTree<V> insert(
      final RedBlackTree<V> toInsertInto,
      final V value
  ) {
    if (toInsertInto == null || toInsertInto.getValue() == null) {
      return new RedBlackTree<>(
          Type.RED,
          null,
          value,
          null);
    } else if (value.compareTo(toInsertInto.getValue()) < 0) {
      return balance(
          new RedBlackTree<>(
              toInsertInto.getType(),
              insert(toInsertInto.getLeftChild(), value),
              toInsertInto.getValue(),
              toInsertInto.getRightChild()));
    } else if (value.compareTo(toInsertInto.getValue()) > 0) {
      return balance(
          new RedBlackTree<>(
              toInsertInto.getType(),
              toInsertInto.getLeftChild(),
              toInsertInto.getValue(),
              insert(toInsertInto.getRightChild(), value)));
    }
    return toInsertInto;
  }

  /**
   * Flattens a {@link RedBlackTree} and returns all the values as a {@link Set}.
   *
   * @param toFlatten The {@link RedBlackTree} to flatten
   * @param <V>       The type of object in the tree.
   * @return A {@link Set} containing all the values in the tree.
   */
  public static <V extends Comparable<V>> Set<V> getNodes(final RedBlackTree<V> toFlatten) {
    final ImmutableSet.Builder<V> setBuilder = new ImmutableSet.Builder<>();
    if (toFlatten == null) {
      return setBuilder.build();
    }
    if (toFlatten.getValue() != null) {
      setBuilder.add(toFlatten.getValue());
    }
    if (toFlatten.getLeftChild() != null) {
      setBuilder.addAll(getNodes(toFlatten.getLeftChild()));
    }
    if (toFlatten.getRightChild() != null) {
      setBuilder.addAll(getNodes(toFlatten.getRightChild()));
    }
    return setBuilder.build();
  }

  /**
   * Balances the tree during insertion. Using the implemented {@link
   * RedBlackTree#insert(RedBlackTree, Comparable) insertion mechanism}, there can only occur one of
   * four cases which need rebalancing:
   * * A black node with a red left child and a red left-left grandchild.
   * * A black node with a red left child and a red left-right grandchild.
   * * A black node with a red right child and a red right-left grandchild.
   * * A black node with a red right child and a red right-right grandchild.
   *
   * @param toBalance The {@link RedBlackTree} to balance.
   * @param <V>       The type of object in the tree.
   * @return The rebalanced tree.
   */
  private static <V extends Comparable<V>> RedBlackTree<V> balance(
      final RedBlackTree<V> toBalance
  ) {
    // if the root note is black
    if (toBalance.getType().equals(Type.BLACK)) {
      // and if the left child of the root is red
      if (toBalance.getLeftChild() != null && Type.RED.equals(toBalance.getLeftChild().getType())) {
        if (toBalance.getLeftChild().getLeftChild() != null
            && Type.RED.equals((toBalance.getLeftChild().getLeftChild().getType()))) {
          // we must rebuild
          // the root
          return new RedBlackTree<>(
              Type.RED,
              // the left child
              new RedBlackTree<>(
                  Type.BLACK,
                  toBalance.getLeftChild().getLeftChild().getLeftChild(),
                  toBalance.getLeftChild().getLeftChild().getValue(),
                  toBalance.getLeftChild().getLeftChild().getRightChild()),
              toBalance.getLeftChild().getValue(),
              // the right child
              new RedBlackTree<>(
                  Type.BLACK,
                  // the right-left grandchild
                  toBalance.getLeftChild().getRightChild(),
                  toBalance.getValue(),
                  toBalance.getRightChild()));
        } else if (toBalance.getLeftChild().getRightChild() != null
            && Type.RED.equals((toBalance.getLeftChild().getRightChild().getType()))) {
          // we must rebuild
          // the root
          return new RedBlackTree<>(
              Type.RED,
              // the left child
              new RedBlackTree<>(
                  Type.BLACK,
                  toBalance.getLeftChild().getLeftChild(),
                  toBalance.getLeftChild().getValue(),
                  toBalance.getLeftChild().getRightChild().getLeftChild()),
              toBalance.getLeftChild().getRightChild().getValue(),
              // the right child
              new RedBlackTree<>(
                  Type.BLACK,
                  // the right-left grandchild
                  toBalance.getLeftChild().getRightChild().getRightChild(),
                  toBalance.getValue(),
                  toBalance.getRightChild()));
        }
      } else if (toBalance.getRightChild() != null
          && Type.RED.equals(toBalance.getRightChild().getType())) {
        if (toBalance.getRightChild().getLeftChild() != null
            && Type.RED.equals((toBalance.getRightChild().getLeftChild().getType()))) {
          // we must rebuild
          // the root
          return new RedBlackTree<>(
              Type.RED,
              // the left child
              new RedBlackTree<>(
                  Type.BLACK,
                  toBalance.getLeftChild(),
                  toBalance.getValue(),
                  toBalance.getRightChild().getLeftChild().getLeftChild()),
              toBalance.getRightChild().getLeftChild().getValue(),
              // the right child
              new RedBlackTree<>(
                  Type.BLACK,
                  // the right-left grandchild
                  toBalance.getRightChild().getLeftChild().getRightChild(),
                  toBalance.getRightChild().getValue(),
                  toBalance.getRightChild().getRightChild()));
        } else if (toBalance.getRightChild().getRightChild() != null
            && Type.RED.equals((toBalance.getRightChild().getRightChild().getType()))) {
          // we must rebuild
          // the root
          return new RedBlackTree<>(
              Type.RED,
              // the left child
              new RedBlackTree<>(
                  Type.BLACK,
                  toBalance.getLeftChild(),
                  toBalance.getValue(),
                  toBalance.getRightChild().getLeftChild()),
              toBalance.getRightChild().getValue(),
              // the right child
              new RedBlackTree<>(
                  Type.BLACK,
                  // the right-left grandchild
                  toBalance.getRightChild().getRightChild().getLeftChild(),
                  toBalance.getRightChild().getRightChild().getValue(),
                  toBalance.getRightChild().getRightChild().getRightChild()));
        }
      }
    }
    return toBalance;
  }

  /**
   * Computes the black height, or the uniform number of black nodes in all paths from root to the
   * leaves.
   *
   * @param toVerify The {@link RedBlackTree} to compute the black height of.
   * @param <V>      The type of object in the {@link RedBlackTree}.
   * @return The black height, or -1 when the tree is not a {@link RedBlackTree}.
   */
  public static <V extends Comparable<V>> int computeBlackHeight(final RedBlackTree<V> toVerify) {
    // no tree means zero height
    if (toVerify == null) {
      return 0;
    }

    final int leftHeight = computeBlackHeight(toVerify.getLeftChild());
    final int rightHeight = computeBlackHeight(toVerify.getRightChild());

    // increase
    final int add = Type.BLACK.equals(toVerify.getType()) ? 1 : 0;

    if (leftHeight == -1 || rightHeight == -1 || leftHeight != rightHeight) {
      return -1;
    } else {
      return leftHeight + add;
    }
  }

  /**
   * @param toSearch The {@link RedBlackTree} to search.
   * @param value    The value to verify whether it is contained inside the supplied {@link
   *                 RedBlackTree}.
   * @param <V>      The type of object in the tree.
   * @return True if value is in the supplied tree, false otherwise.
   */
  public static <V extends Comparable<V>> boolean contains(
      final RedBlackTree<V> toSearch,
      final V value
  ) {
    if (toSearch == null || toSearch.getValue() == null) {
      return false;
    } else if (value.compareTo(toSearch.getValue()) < 0) {
      return contains(toSearch.getLeftChild(), value);
    } else if (value.compareTo(toSearch.getValue()) > 0) {
      return contains(toSearch.getRightChild(), value);
    }
    return true;
  }

  /**
   * @param toMakeBlack The {@link RedBlackTree} to change the type to {@link Type} of to BLACK.
   * @param <V>         The type of object in the tree.
   * @return The supplied {@link RedBlackTree} with a {@link Type black type}.
   */
  private static <V extends Comparable<V>> RedBlackTree<V> makeBlack(
      final RedBlackTree<V> toMakeBlack
  ) {
    return new RedBlackTree<>(
        Type.BLACK,
        toMakeBlack.getLeftChild(),
        toMakeBlack.getValue(),
        toMakeBlack.getRightChild());
  }

  /**
   * @return True if this tree is a balanced {@link RedBlackTree}, false otherwise.
   */
  public static <V extends Comparable<V>> boolean isBalanced(RedBlackTree<V> toVerify) {
    return computeBlackHeight(toVerify) != -1;
  }

  /**
   * @param value The value to add to this {@link RedBlackTree}
   * @return A new {@link RedBlackTree} containing all the nodes inside this tree and the supplied
   * value.
   */
  public RedBlackTree<V> insert(final V value) {
    return makeBlack(insert(this, value));
  }

  @Override
  public Set<V> getNodes() {
    return getNodes(this);
  }

  /**
   * @param value The value to verify whether it is contained inside this {@link RedBlackTree}.
   * @return True if value is in this tree, false otherwise.
   */
  public boolean contains(final V value) {
    return contains(this, value);
  }

  /**
   * @return True if this tree is a balanced {@link RedBlackTree}, false otherwise.
   */
  public boolean isBalanced() {
    return computeBlackHeight(this) != -1;
  }

  /**
   * @return The black height of the tree, or the uniform number of black nodes in all paths from
   * root to the leaves.
   */
  public int computeBlackHeight() {
    return computeBlackHeight(this);
  }

  /**
   * @return True if empty, false otherwise.
   */
  public boolean isEmpty() {
    return this.value == null;
  }

  /**
   * The Enum Type.
   */
  enum Type {
    RED,
    BLACK
  }
}
