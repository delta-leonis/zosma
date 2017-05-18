package org.ssh.benchmarks;

import java.util.Set;
import lombok.Value;

/**
 * The Class SimpleGroupedMeasurement.
 *
 * This class represents a simple {@link GroupedMeasurement}.
 * @author Rimon Oz
 */
@Value
public class SimpleGroupedMeasurement implements GroupedMeasurement {
  private final long timestamp = System.currentTimeMillis();
  private final String label;
  private final Set<DescriptiveMeasurement> value;
}
