package org.ssh.math.algebra;

/**
 * The Interface Ring.
 * <p>
 * This interface describes the functionality of a
 * <a href="https://en.wikipedia.org/wiki/Ring_(mathematics)">ring</a>.
 * A ring is a mathematical object which has the following properties:
 * <p>
 * - A ring is defined over a field (of elements), such as the real numbers
 * or the complex numbers.
 * - A ring has two operators: addition (+) and multiplication (*)
 * - When an operator is applied to a number, it will result in a number
 * from the same field (eg. addition of two reals results in a real,
 * multiplication of two complex numbers yield a complex number, etc.)
 * <p>
 * A ring is an Abelian {@link Group}, or a commutative group, with a second
 * operator which is associative by itself, and distributes over the addition
 * operator.
 * A ring is governed by the following axioms (also known as the ring axioms):
 * <p>
 * - Closure:        if a and b are elements of F, then a + b is an element
 * of F
 * - Associativity:  if a, b, and c are elements of F, then (a+b)+c = a+(b+c)
 * if a, b, and c are elements of F, then (a*b)*c = a*(b*c)
 * - Identity:       there exists an element e of F such that for all a in F:
 * e+a = a+e = a+0
 * - Inverse:        for every element, a, in F there exists another element,
 * b, such that a*b=1
 * - Distributivity: if a, b, and c are elements of F, then
 * a*(b+c) = a*b + a*c
 *
 * @param <F> The type of field element (eg. real, complex, integer, etc.)
 * @author Rimon Oz
 */
public interface Ring<F> extends Group<F> {
    /**
     * Computes the product of two field elements.
     *
     * @param firstElement  The first field element to be multiplied.
     * @param secondElement The second field element to be multiplied.
     * @return The product of the two arguments.
     */
    F multiply(F firstElement, F secondElement);

    /**
     * Returns the multiplicative inverse of the argument. The multiplicative
     * inverse is defined as the number which when multiplied with the original
     * number results in zero.
     * For example, the additive inverse of 3 is 1/3 and the additive inverse of
     * 3+j is 1/(3+j).
     *
     * @param fieldElement The number to compute the multiplicative inverse
     *                     for.
     * @return The multiplicative inverse of the argument.
     */
    <E extends Exception> F getMultiplicativeInverse(F fieldElement) throws E;

    /**
     * Computes the quotient of two field elements.
     *
     * @param firstElement  The first field number, which functions as the
     *                      numerator.
     * @param secondElement The second field number, which functions as the
     *                      denominator.
     * @return The quotient of the two arguments.
     */
    default <E extends Exception>F divide(F firstElement, F secondElement) throws E {
        return this.multiply(
                firstElement,
                this.getMultiplicativeInverse(secondElement));
    }
}
