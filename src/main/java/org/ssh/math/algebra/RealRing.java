package org.ssh.math.algebra;

/**
 * The Class RealRing.
 *
 * @author Rimon Oz
 */
public class RealRing implements Ring<Double> {
    @Override
    public Double add(Double firstElement, Double secondElement) {
        return firstElement + secondElement;
    }

    @Override
    public Double getAdditiveInverse(Double fieldElement) {
        return (-1d) * fieldElement;
    }

    @Override
    public Double multiply(Double firstElement, Double secondElement) {
        return firstElement * secondElement;
    }

    @Override
    public Double getMultiplicativeInverse(Double fieldElement) throws Exception {
        if (fieldElement == 0)
            throw new ArithmeticException();
        return 1d / fieldElement;
    }
}
