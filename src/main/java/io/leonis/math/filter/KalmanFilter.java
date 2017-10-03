package io.leonis.math.filter;

import io.leonis.math.statistic.*;
import io.leonis.math.statistic.distribution.GaussianDistribution;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.inverse.InvertMatrix;

/**
 * The Class KalmanFilter.
 *
 * This class contains the functionality of a Kalman filter for linear applications. The
 * implementation is based off of notes and equations which can be found <a
 * href="https://en.wikipedia.org/wiki/Kalman_filter#Details">here</a>.
 *
 * @author Rimon Oz
 */
public class KalmanFilter {

  /**
   * Computes the filtered {@link GaussianDistribution} (mean and covariance) using the supplied
   * state-, measurement-, and control-transition matrices, and the supplied control input, process
   * noise, observation noise, and current state.
   *
   * @param stateTransitionMatrix       The state-transition matrix used in projecting the {@link
   *                                    GaussianDistribution}.
   * @param measurementTransitionMatrix The measurement-transition matrix used in computing the
   *                                    measurement based on the supplied state.
   * @param controlTransitionMatrix     The control-transition matrix used in computing the effect
   *                                    of the control input on the resultant {@link
   *                                    GaussianDistribution}.
   * @param controlInputVector          The control input.
   * @param processCovariance           The process variance matrix. This matrix determines the
   *                                    impact of the state transition matrix on the filter and can
   *                                    be seen as an indication of how trustworthy the state
   *                                    transition model is. Lower values in the process covariance
   *                                    matrix result in the filtered state tending towards the
   *                                    projected state using the state transition model. Higher
   *                                    values result in the filtered state tending towards the
   *                                    measurements.
   * @param measurement                 The {@link GaussianDistribution} of the measurement, where
   *                                    the mean is equal to the measurement vector and the
   *                                    covariance is equal to the measurement variance matrix.
   * @param previousState               The {@link GaussianDistribution} of the latest state.
   * @return The filtered state.
   */
  public Distribution apply(
      final INDArray stateTransitionMatrix,
      final INDArray measurementTransitionMatrix,
      final INDArray controlTransitionMatrix,
      final INDArray controlInputVector,
      final INDArray processCovariance,
      final Distribution measurement,
      final Distribution previousState
  ) {
    final INDArray projectedState = stateTransitionMatrix.mmul(previousState.getMean())
        .add(controlTransitionMatrix.mmul(controlInputVector));

    final INDArray projectedErrorCovariance =
        stateTransitionMatrix
            .mmul(previousState.getCovariance().mmul(stateTransitionMatrix.transpose()))
            .add(processCovariance);

    final INDArray kalmanGain = projectedErrorCovariance
        .mmul(measurementTransitionMatrix.transpose()
            .mmul(InvertMatrix.invert(measurementTransitionMatrix
                .mmul(projectedErrorCovariance
                    .mmul(measurementTransitionMatrix.transpose()))
                .add(measurement.getCovariance()), false)));

    final INDArray estimatedState = projectedState
        .add(kalmanGain
            .mmul(measurement.getMean().sub(measurementTransitionMatrix.mmul(projectedState))));

    final INDArray estimatedErrorCovariance =
        (Nd4j.eye(estimatedState.rows()).sub(kalmanGain.mmul(measurementTransitionMatrix)))
            .mmul(projectedErrorCovariance);

    return new SimpleDistribution(estimatedState, estimatedErrorCovariance);
  }
}
