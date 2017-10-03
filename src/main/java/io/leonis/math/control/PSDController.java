package io.leonis.math.control;

import java.util.List;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Class PSDController.
 *
 * This class contains the functionality of a discrete-time Proportional-, Summation-, and
 * Difference-controller. The implementation is based on the derivation by Cagatay Basdogan (see <a
 * href="http://portal.ku.edu.tr/~cbasdogan/Courses/Robotics/projects/Discrete_PID.pdf">these
 * slides</a>).
 *
 * @author Rimon Oz
 */
public interface PSDController {

  /**
   * Computes the control vector based on the supplied previous control signal, error terms (also
   * known as residual or innovation), the supplied proportial-, summation-, and
   * difference-coefficients, and the time since last update.
   *
   * @param previousControl    The value of the control vector in the previous time-step.
   * @param residuals          A list of error terms in previous time-steps, with the latest error
   *                           term appearing first.
   * @param proportionalFactor The coefficient for the proportional controller (known as K_p in the
   *                           literature).
   * @param summationFactor    The coefficient for the summation controller (known as K_s or K_i in
   *                           the literature).
   * @param differenceFactor   The coefficient for the difference controller (known as K_d in the
   *                           literature).
   * @param deltaTime          The change in time since the previous measurement.
   * @return The new control vector, adjusted by the controller.
   */
  static INDArray apply(
      final INDArray previousControl,
      final List<INDArray> residuals,
      final double proportionalFactor,
      final double summationFactor,
      final double differenceFactor,
      final double deltaTime
  ) {
    return previousControl
        .add(residuals.get(0).mul(
            proportionalFactor
                + summationFactor * deltaTime / 2d
                + differenceFactor / deltaTime))
        .add(residuals.get(1).mul(
            -1d * proportionalFactor
                + summationFactor * deltaTime / 2d
                - 2d * differenceFactor / deltaTime))
        .add(residuals.get(2).mul(differenceFactor / deltaTime));
  }
}
