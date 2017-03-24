package org.ssh.benchmarks;

import org.ssh.math.statistic.DescriptiveMeasure;
import org.ssh.math.unit.Unit;

/**
 * The Interface DescriptiveMeasurement.
 *
 * @param <M> the type parameter
 * @author Rimon Oz
 */
public interface DescriptiveMeasurement<M> {

  /**
   * Returns the type of measurement (ie. its measure).
   *
   * @return The type of measurements as a {@link DescriptiveMeasure}
   */
  DescriptiveMeasure getType();

  /**
   * Returns the label of the measurement.
   *
   * @return The label of the measurement.
   */
  String getLabel();

  /**
   * Returns the value of the measurement.
   *
   * @return The value of the measurement.
   */
  M getValue();

  /**
   * Returns the {@link Unit} of the measurement.
   *
   * @return The {@link Unit} of measurement.
   */
  Unit getUnit();
}
