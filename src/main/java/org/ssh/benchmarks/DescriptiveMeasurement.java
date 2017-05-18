package org.ssh.benchmarks;

import java.io.Serializable;
import org.ssh.math.Temporal;
import org.ssh.math.statistic.DescriptiveMeasure;

/**
 * The Interface DescriptiveMeasurement.
 *
 * @param <V> The type of value.
 * @author Rimon Oz
 */
public interface DescriptiveMeasurement<V> extends Serializable, Temporal {
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
}
