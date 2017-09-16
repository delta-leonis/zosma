package org.ssh.benchmarks;

import java.util.*;
import java.util.function.Function;
import org.ssh.math.statistic.DescriptiveMeasure;
import org.ssh.math.statistic.DescriptiveMeasure.Summary;

/**
 * The Class GroupedMeasurement
 *
 * This interface describes a group of measurements, ie. a set of measurements which have been
 * made together.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
public interface GroupedMeasurement extends DescriptiveMeasurement<Set<DescriptiveMeasurement>> {
  @Override
  default DescriptiveMeasure getType() {
    return Summary.GROUPED;
  }

  /**
   * Finds a measurement with the supplied label.
   *
   * @param label The label to match measurements with.
   * @return The first matching measurement.
   */
  default Optional<DescriptiveMeasurement> find(final String label) {
    return this.find(label, DescriptiveMeasurement::getLabel);
  }

  /**
   * Finds a measurement by applying the supplied {@link Function} and comparing the result
   * to the supplied comparison value.
   *
   * @param toCompare             The comparison value.
   * @param transformToComparable A {@link Function} which maps the measurement to a type matching
   *                              the type of the supplied comparison value.
   */
  default <O> Optional<DescriptiveMeasurement> find(
      final O toCompare,
      final Function<DescriptiveMeasurement, O> transformToComparable
  ) {
    return this.getValue().stream()
        .filter(comparable -> toCompare.equals(transformToComparable.apply(comparable)))
        .findFirst();
  }
}
