package org.ssh.benchmarks;

/**
 * The Interface RangedMeasurement.
 *
 * This interface describes the functionality of a ranged measurement, that is a measurement
 * restricted by a range.
 *
 * @param <M> The type of measured value.
 * @author Jeroen de Jong
 */
public interface RangedMeasurement<M> extends DescriptiveMeasurement<M> {

  /**
   * Returns the maximum value which can be measured.
   *
   * @return The maximum value which can be measured.
   */
  M getMax();

  /**
   * Returns the minimum value which can be measured.
   *
   * @return The minimum value which can be measured.
   */
  M getMin();
}