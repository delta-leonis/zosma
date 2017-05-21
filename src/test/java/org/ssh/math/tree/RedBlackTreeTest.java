package org.ssh.math.tree;

import static org.testng.Assert.*;

import java.util.Set;
import java.util.stream.*;
import org.ssh.math.tree.RedBlackTree.Type;
import org.testng.annotations.Test;

/**
 * The Test RedBlackTreeTest.
 *
 * @author Rimon Oz
 */
public class RedBlackTreeTest {
  @Test
  public void testEquals() throws Exception {
    final int toTest = (int) (Math.random() * 1000);
    assertTrue(new RedBlackTree<Integer>().equals(new RedBlackTree<Integer>()));

    assertFalse(new RedBlackTree<Integer>().equals("test"));
    assertTrue(new RedBlackTree<>(toTest).equals(new RedBlackTree<>(toTest)));
    assertFalse(new RedBlackTree<>(toTest).equals(new RedBlackTree<>(-1 * toTest)));
  }

  @Test
  public void testContains() throws Exception {
    final RedBlackTree<Double> emptyTree = new RedBlackTree<>();

    assertFalse(emptyTree.contains(Math.random()));
    assertFalse(emptyTree.contains(-1 * Math.random()));
    assertFalse(RedBlackTree.contains(null, Math.random()));
    assertFalse(RedBlackTree.contains(emptyTree, null));
  }

  @Test
  public void testInsert() throws Exception {
    final RedBlackTree<Double> redBlackTree = new RedBlackTree<>();
    final double toInsert = Math.random();
    final RedBlackTree<Double> updatedTree = redBlackTree.insert(toInsert);
    final double toInsertNext = -1 * Math.random();
    final RedBlackTree<Double> newUpdatedTree = updatedTree.insert(toInsertNext);

    assertTrue(updatedTree.contains(toInsert));
    assertTrue(newUpdatedTree.contains(toInsertNext));
  }

  @Test
  public void testGetNodes() throws Exception {
    final Set<Integer> randomNumbers = IntStream.range(0, 100)
        .mapToObj(index -> (int) (1000 * Math.random()))
        .collect(Collectors.toSet());

    final RedBlackTree<Integer> redBlackTree = new RedBlackTree<>(randomNumbers);
    final RedBlackTree<Integer> emptyRedBlackTree = new RedBlackTree<>();

    assertEqualsNoOrder(randomNumbers.toArray(), redBlackTree.getNodes().toArray());
    assertTrue(emptyRedBlackTree.getNodes().isEmpty());
    assertTrue(RedBlackTree.getNodes(null).isEmpty());
  }

  @Test
  public void testIsRedBlackTree() throws Exception {
    final Set<Integer> randomNumbers = IntStream.range(0, 100)
        .mapToObj(index -> (int) (1000 * Math.random()))
        .collect(Collectors.toSet());

    final RedBlackTree<Integer> redBlackTree = new RedBlackTree<>(randomNumbers);

    final RedBlackTree<Integer> unbalancedTree = new RedBlackTree<>(
        Type.RED,
        new RedBlackTree<>(Type.BLACK, null, null, null),
        0,
        new RedBlackTree<>(Type.RED, null, null, null));

    assertTrue(redBlackTree.isBalanced());
    assertTrue(RedBlackTree.isBalanced(null));
    assertFalse(unbalancedTree.isBalanced());
  }

  @Test
  public void testIsEmpty() throws Exception {
    final RedBlackTree<String> emptyTree = new RedBlackTree<>();
    final RedBlackTree<String> singleItemTree = new RedBlackTree<>("test");

    assertTrue(emptyTree.isEmpty());
    assertFalse(singleItemTree.isEmpty());
  }
}
