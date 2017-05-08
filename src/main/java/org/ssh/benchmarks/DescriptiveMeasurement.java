package org.ssh.benchmarks;

import org.ssh.math.statistic.DescriptiveMeasure;
import org.ssh.math.unit.Unit;

/**
 * The Interface DescriptiveMeasurement.
 *
 * @param <V> The type of value.
 * @author Rimon Oz
 */
public interface DescriptiveMeasurement<V> {
  /**
   * @return The value of the measurement.
   */
  V getValue();

  /**
   * @return The type of measurements as a {@link DescriptiveMeasure}
   */
  DescriptiveMeasure getType();

  /**
   * @return The label of the measurement.
   */
  String getLabel();

  /**
   * @return The {@link Unit} of measurement.
   */
  Unit getUnit();
}
