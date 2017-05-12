package org.ssh.math.algebra;

/**
 * The Interface Lattice.
 * <p>
 * This interface describes the functionality of a
 * <a href="https://en.wikipedia.org/wiki/Lattice_(order)">lattice</a>.
 * A lattice is a mathematical object which has the following properties:
 * <p>
 * - A lattice is defined over a field (of elements), such as the real numbers,
 * or the complex numbers.
 * - A lattice has a greatest lower bound (also known as meet or infimum) and
 * a least upper bound (also known as a join or a supremum).
 * - A lattice has two operations ("and" and "or")
 * <p>
 * A lattice is governed by the following axioms (also known as the lattice axioms):
 * <p>
 * - Commutativity: if a and b are elements of F, then a "and" b = b "and" a,
 * and a "or"  b = b "or"  a.
 * - Associativity: if a, b, and c are elements of F, then
 * a "and" (b "and" c) = (a "and" b) "and" c, and
 * a "or"  (b "or"  c) = (a "or"  b) "or"  c.
 * - Absorption:    if a and b are elements of F, then
 * a "or"  (a "and" b) = a, and
 * a "and" (a "or"  b) = a.
 * - Idempotence:   if a is an element of F, then
 * a "or" a = a, and
 * a "and" a = a
 *
 * @param <F> the type parameter
 * @author Rimon Oz
 */
public interface Lattice<F> {

  /**
   * @param leftElement   The first argument to the and-operation.
   * @param secondElement The second argument to the and-operation.
   * @return The result of applying the and-operation to the two arguments.
   */
  F and(final F leftElement, final F secondElement);

  /**
   * @param leftElement   The first argument to the or-operation.
   * @param secondElement The second argument to the or-operation.
   * @return The result of applying the or-operation to the two arguments.
   */
  F or(final F leftElement, final F secondElement);
}
