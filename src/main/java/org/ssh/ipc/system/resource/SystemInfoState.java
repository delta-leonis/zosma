package org.ssh.ipc.system.resource;

import lombok.Value;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.math.statistic.DescriptiveMeasure;

/**
 * The Class SystemInfoState.
 *
 * This class represents the state of a {@link SystemComponent} obtained
 * through a {@link DescriptiveMeasurement measurement}.
 *
 * @param <T> The type of value in this measurement.
 * @author Rimon Oz
 */
@Value
public class SystemInfoState<T> implements DescriptiveMeasurement<T> {

  /**
   * The type of {@link SystemComponent}.
   **/
  private final SystemComponent systemComponent;
  private final DescriptiveMeasure type;
  private final T value;
  private final String label;
  private final long timestamp = System.currentTimeMillis();
}
