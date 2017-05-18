package org.ssh.ipc.serialization.influx;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.ssh.benchmarks.*;

/**
 * The Class GroupedMeasurementWriteHandler.
 *
 * This class represents a {@link PointWriteHandler} which can handle {@link GroupedMeasurement}.
 *
 * @author Rimon Oz
 */
@Slf4j
public class GroupedMeasurementWriteHandler implements PointWriteHandler<GroupedMeasurement> {
  @Override
  public Point write(final GroupedMeasurement src) {
    return Point.measurement(src.getLabel())
        .time(src.getTimestamp(), TimeUnit.MILLISECONDS)
        .fields(this.parse(src))
        .build();
  }

  /**
   * Parses the supplied {@link GroupedMeasurement} into a mapping of labels to values.
   *
   * @param measurement The {@link GroupedMeasurement} to parse.
   * @return A mapping of labels into values.
   */
  private Map<String, Object> parse(final GroupedMeasurement measurement) {
    return measurement.getValue().stream()
        .map(this::parse)
        .flatMap(map -> map.entrySet().stream())
        .collect(Collectors.toMap(
            Entry::getKey,
            Entry::getValue));
  }

  /**
   * Parses the supplied {@link DescriptiveMeasurement} into a mapping of labels to values.
   *
   * @param measurement The {@link DescriptiveMeasurement} to parse.
   * @return A mapping of labels into values.
   */
  private Map<String, Object> parse(final DescriptiveMeasurement measurement) {
    return Collections.singletonMap(measurement.getLabel(), measurement.getValue());
  }
}
