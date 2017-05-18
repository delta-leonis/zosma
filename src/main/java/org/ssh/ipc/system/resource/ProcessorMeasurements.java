package org.ssh.ipc.system.resource;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.stream.*;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.ssh.benchmarks.*;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.math.statistic.DescriptiveMeasure;
import oshi.hardware.CentralProcessor;

/**
 * The Class ProcessorMeasurements.
 *
 * This class represents a {@link GroupedMeasurement collection} of CPU measurements.
 *
 * @author Jeroen de Jong
 */
@Value
@NonFinal
public class ProcessorMeasurements implements GroupedMeasurement {
  private final String label = "cpu";

  private final DescriptiveMeasurement<String> family, identifier, model, name, stepping, serialNumber, vendor;
  private final DescriptiveMeasurement<Integer> logicalProcessors, physicalProcessors;
  private final DescriptiveMeasurement<Boolean> cpu64bit;
  private final DescriptiveMeasurement<Double> averageSystemLoad, systemLoad;
  private final DescriptiveMeasurement<Long> frequency;
  private final GroupedMeasurement loadPerCore;
  private final long timestamp = System.currentTimeMillis();

  /**
   * Creates a {@link ProcessorMeasurements} from a supplied {@link CentralProcessor}.
   *
   * @param processor The {@link CentralProcessor}.
   * @return A {@link ProcessorMeasurements}.
   */
  public static ProcessorMeasurements of(final CentralProcessor processor) {
    return new ProcessorMeasurements(
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getFamily(),
            "Family"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getIdentifier(),
            "Identifier"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getModel(),
            "Model"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getName(),
            "Name"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getStepping(),
            "Stepping"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getSystemSerialNumber(),
            "System serial number"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getVendor(),
            "Vendor"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Count.TOTAL,
            processor.getLogicalProcessorCount(),
            "Number of logical processors"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Count.TOTAL,
            processor.getPhysicalProcessorCount(),
            "Number of physical processors"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Summary.VALIDITY,
            processor.isCpu64bit(),
            "Is 64-bit?"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Continuous.Center.MEAN,
            processor.getSystemLoadAverage(),
            "System load average"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Count.TOTAL,
            processor.getSystemCpuLoad(),
            "System CPU load"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            DescriptiveMeasure.Count.TOTAL,
            processor.getVendorFreq(),
            "Vendor frequency"),
        new SimpleGroupedMeasurement(
            "Core load between ticks",
            IntStream.range(0, processor.getProcessorCpuLoadBetweenTicks().length)
                .mapToObj(number -> new SystemInfoState<>(
                    SystemComponent.CPU,
                    DescriptiveMeasure.Count.DISPERSION,
                    processor.getProcessorCpuLoadBetweenTicks()[number],
                    "core " + number))
                .collect(Collectors.toSet())));
  }

  @Override
  public Set<DescriptiveMeasurement> getValue() {
    return ImmutableSet.<DescriptiveMeasurement>builder()
        .add(
            this.getFamily(),
            this.getIdentifier(),
            this.getModel(),
            this.getName(),
            this.getStepping(),
            this.getSerialNumber(),
            this.getVendor(),
            this.getLogicalProcessors(),
            this.getPhysicalProcessors(),
            this.getCpu64bit(),
            this.getAverageSystemLoad(),
            this.getSystemLoad(),
            this.getFrequency())
        .addAll(this.getLoadPerCore().getValue())
        .build();
  }
}
