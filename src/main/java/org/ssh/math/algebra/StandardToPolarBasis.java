package org.ssh.math.algebra;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Class StandardToPolarBasis.
 * <p>
 * This class describes the functionality of a change of basis matrix.
 *
 * @author Rimon Oz
 */
public class StandardToPolarBasis implements ChangeOfBasis<INDArray> {

    public StandardToPolarBasis(int dimension) {
    }

    @Override
    public INDArray asMatrix(INDArray input) {
        // return an input.rows * input.rows matrix
        return null;
    }

    @Override
    public INDArray change(INDArray input) {
        return this.asMatrix(input).mmul(input);
    }
}
