package org.ssh.math.filter;

import java.util.function.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.inverse.InvertMatrix;
import org.ssh.math.statistic.*;

/**
 * The Class ExtendedKalmanFilter. <p> This class contains the functionality for an extended Kalman
 * filter (EKF) for nonlinear applications. The implementation is based off of notes and equations
 * which can be found <a href="https://en.wikipedia.org/wiki/Extended_Kalman_filter#Discrete-time_predict_and_update_equations">
 * here</a>.
 *
 * @author Rimon Oz
 */
public interface ExtendedKalmanFilter extends Filter<INDArray> {

  /**
   * Computes the filtered {@link Distribution} (mean and covariance) using the supplied state-,
   * measurement-, and control-transition matrices, and the supplied control input, process noise,
   * observation noise, and current state.
   *
   * @param stateTransitionMatrix       The state-transition matrix used in projecting the {@link
   *                                    Distribution}.
   * @param measurementTransitionMatrix The measurement-transition matrix used in computing the
   *                                    measurement based on the supplied state.
   * @param controlTransitionMatrix     The control-transition matrix used in computing the effect
   *                                    of the control input on the resultant {@link Distribution}.
   * @param controlInput                The control input.
   * @param processCovariance           the process covariance
   * @param observationNoise            The {@link Distribution} of observation noise
   * @param state                       The {@link Distribution} of the latest state.
   * @return The filtered state.
   */
  static Distribution<INDArray> apply(
      final INDArray stateTransitionMatrix,
      final INDArray measurementTransitionMatrix,
      final INDArray controlTransitionMatrix,
      final INDArray controlInput,
      final INDArray processCovariance,
      final Distribution<INDArray> observationNoise,
      final Distribution<INDArray> state
  ) {
    return apply((stateMean, controlVector) ->
            stateTransitionMatrix
                .mmul(stateMean)
                .add(controlTransitionMatrix
                    .mmul(controlTransitionMatrix.mmul(controlVector)))
                .add(processCovariance),
        measurementTransitionMatrix::mul,
        controlInput,
        Nd4j.eye(stateTransitionMatrix.rows()),
        Nd4j.eye(measurementTransitionMatrix.rows()),
        processCovariance,
        observationNoise,
        state);
  }

  /**
   * Computes the filtered {@link Distribution} (mean and covariance) using the supplied state-
   * and measurement-transition functions, their respective Jacobians and the supplied control
   * input, process noise, observation noise, and current state.
   *
   * @param stateTransition               A {@link BiFunction} which takes as its first argument the
   *                                      previous state, and the control input as its second
   *                                      argument. The result is the next state.
   * @param measurementTransition         A {@link Function} which takes as its first argument the
   *                                      current state and returns the measurement.
   * @param stateTransitionJacobian       The Jacobian representing the state-transition.
   * @param measurementTransitionJacobian The Jacobian representing the measurement-transition.
   * @param controlInput                  The control input.
   * @param processCovariance             the process covariance
   * @param observationNoise              The {@link Distribution} of observation noise
   * @param state                         The {@link Distribution} of the latest state.
   * @return The filtered state.
   */
  static Distribution<INDArray> apply(
      final BiFunction<INDArray, INDArray, INDArray> stateTransition,
      final Function<INDArray, INDArray> measurementTransition,
      final INDArray stateTransitionJacobian,
      final INDArray measurementTransitionJacobian,
      final INDArray controlInput,
      final INDArray processCovariance,
      final Distribution<INDArray> observationNoise,
      final Distribution<INDArray> state
  ) {
    final INDArray projectedState = stateTransition.apply(state.getMean(), controlInput);

    final INDArray projectedErrorCovariance = stateTransitionJacobian
        .mmul(state.getMean()
            .mmul(stateTransitionJacobian.transpose()))
        .add(processCovariance);

    final INDArray kalmanGain = projectedErrorCovariance
        .mmul(measurementTransitionJacobian.transpose()
            .mmul(InvertMatrix.invert(measurementTransitionJacobian
                .mmul(projectedErrorCovariance
                    .mmul(measurementTransitionJacobian.transpose()))
                .add(observationNoise.getCovariance()), false)));

    final INDArray estimatedState = projectedState
        .add(kalmanGain
            .mmul(measurementTransition.apply(state.getMean())
                .sub(measurementTransition.apply(projectedState))));

    final INDArray estimatedErrorCovariance = Nd4j.eye(estimatedState.rows())
        .sub(kalmanGain.
            mul(measurementTransitionJacobian))
        .mmul(projectedErrorCovariance);

    return new SimpleDistribution(estimatedState, estimatedErrorCovariance);
  }
}
