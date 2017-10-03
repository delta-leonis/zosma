package io.leonis.math.algebra;

/**
 * The Interface Group.
 *
 * This interface describes the functionality of a <a href="https://en.wikipedia.org/wiki/Group_(mathematics)">group</a>.
 * A group is a mathematical object which has the following properties:
 *
 * - A group is defined over a field (of elements), such as the real numbers or the complex numbers.
 * - A ring has one operator: addition (+). - When an operator is applied to a number, it will
 * result in a number from the same field (eg. addition of two reals results in a real,
 * multiplication of two complex numbers yield a complex number, etc.)
 *
 * A group is governed by the following axioms (also known as the group axioms):
 *
 * - Closure:       if a and b are elements of F, then a + b is an element of F - Associativity: if
 * a, b, and c are elements of F, then (a+b)+c = a+(b+c) - Identity:      there exists an element e
 * of F such that for all a in F: e+a = a+e = a+0
 *
 * @param <F> The type of field element (eg. real, complex, integer, etc.)
 * @author Rimon Oz
 */
public interface Group<F> {

  /**
   * Computes the difference between two field elements.
   *
   * @param firstElement  The first field number, which is used as a reference.
   * @param secondElement The second field number, which is subtracted from the first.
   * @return The difference between the first and second argument.
   */
  default F subtract(final F firstElement, final F secondElement) {
    return this.add(
        firstElement,
        this.getAdditiveInverse(secondElement));
  }

  /**
   * Computes the sum of two field elements.
   *
   * @param firstElement  The first field element to be added.
   * @param secondElement The second field element to be added.
   * @return The sum of the two arguments.
   */
  F add(final F firstElement, final F secondElement);

  /**
   * Returns the additive inverse of the argument. The additive inverse is defined as the number
   * which which when added to the original number results in zero. For example, the additive
   * inverse of 3 is -3 and the additive inverse of 3+j is -3-j.
   *
   * @param fieldElement The number to compute the additive inverse for.
   * @return The additive inverse of the argument.
   */
  F getAdditiveInverse(final F fieldElement);
}
