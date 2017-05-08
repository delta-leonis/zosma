package org.ssh.benchmarks;

import java.util.List;

/**
 * The Interface MeasurementContext
 * <p>
 * This interface describes the context of a measurement, that is the context in which a set
 * of measurements are made.
 *
 * @author Jeroen de Jong
 */
public interface MeasurementContext {

  /**
   * @return The list of recorded measurements.
   */
  List<DescriptiveMeasurement<?>> getMeasurements();
}
