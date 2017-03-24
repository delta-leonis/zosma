package org.ssh.math.dsp.filters;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.inverse.InvertMatrix;
import org.ssh.math.statistics.Distribution;
import org.ssh.math.statistics.SimpleDistribution;
import org.ssh.math.statistics.distributions.GaussianDistribution;

/**
 * The Class KalmanFilter.
 * <p>
 * This class contains the functionality of a Kalman filter for linear applications. The
 * implementation is based off of notes and equations which can be found
 * <a href="https://en.wikipedia.org/wiki/Kalman_filter#Details">here</a>.
 *
 * @author Rimon Oz
 */
public interface KalmanFilter extends Filter<INDArray> {
    /**
     * Computes the filtered {@link GaussianDistribution} (mean and covariance) using the supplied state-,
     * measurement-, and control-transition matrices, and the supplied control input, process noise,
     * observation noise, and current state.
     *
     * @param stateTransitionMatrix       The state-transition matrix used in projecting the
     *                                    {@link GaussianDistribution}.
     * @param measurementTransitionMatrix The measurement-transition matrix used in computing the
     *                                    measurement based on the supplied state.
     * @param controlTransitionMatrix     The control-transition matrix used in computing the effect
     *                                    of the control input on the resultant {@link GaussianDistribution}.
     * @param controlInputVector          The control input.
     * @param processCovariance           The process variance matrix. This matrix determines the impact of the state
     *                                    transition matrix on the filter and can be seen as an indication of how
     *                                    trustworthy the state transition model is. Lower values in the process
     *                                    covariance matrix result in the filtered state tending towards the projected
     *                                    state using the state transition model. Higher values result in the filtered
     *                                    state tending towards the measurements.
     * @param measurement                 The {@link GaussianDistribution} of the measurement, where the mean is
     *                                    equal to the measurement vector and the covariance is equal to the measurement
     *                                    variance matrix.
     * @param previousState               The {@link GaussianDistribution} of the latest state.
     * @return The filtered state.
     */
    static Distribution<INDArray> apply(
            INDArray stateTransitionMatrix,
            INDArray measurementTransitionMatrix,
            INDArray controlTransitionMatrix,
            INDArray controlInputVector,
            INDArray processCovariance,
            Distribution<INDArray> measurement,
            Distribution<INDArray> previousState
    ) {
        INDArray projectedState = stateTransitionMatrix.mmul(previousState.getMean())
                .add(controlTransitionMatrix.mmul(controlInputVector));

        INDArray projectedErrorCovariance =
                stateTransitionMatrix.mmul(previousState.getCovariance().mmul(stateTransitionMatrix.transpose()))
                        .add(processCovariance);

        INDArray kalmanGain = projectedErrorCovariance.mmul(measurementTransitionMatrix.transpose()
                .mmul(InvertMatrix.invert(measurementTransitionMatrix
                        .mmul(projectedErrorCovariance
                                .mmul(measurementTransitionMatrix.transpose()))
                        .add(measurement.getCovariance()), false)));

        INDArray estimatedState = projectedState
                .add(kalmanGain.mmul(measurement.getMean().sub(measurementTransitionMatrix.mmul(projectedState))));

        INDArray estimatedErrorCovariance =
                (Nd4j.eye(estimatedState.rows()).sub(kalmanGain.mmul(measurementTransitionMatrix)))
                        .mmul(projectedErrorCovariance);

        return new SimpleDistribution(estimatedState, estimatedErrorCovariance);
    }
}
