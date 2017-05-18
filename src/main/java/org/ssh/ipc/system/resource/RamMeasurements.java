package org.ssh.ipc.system.resource;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lombok.Value;
import org.ssh.benchmarks.*;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.math.statistic.DescriptiveMeasure;
import oshi.hardware.GlobalMemory;

/**
 * The Class RamMeasurements.
 *
 * This class represents a {@link GroupedMeasurement collection} of RAM measurements.
 *
 * @author Rimon Oz
 */
@Value
public class RamMeasurements implements GroupedMeasurement {
  private final String label = "ram";
  private final DescriptiveMeasurement<Long> freeMemory, totalMemory, usedMemory;
  private final DescriptiveMeasurement<Long> swapUsed, swapTotal, swapFree;
  private final DescriptiveMeasurement<Long> jvmAllocated, jvmFree, jvmUsed;
  private final long timestamp = System.currentTimeMillis();

  /**
   * @param memory The {@link GlobalMemory} to extract measurements from.
   * @return A {@link RamMeasurements} containing measurements extracted from
   */
  public static RamMeasurements of(final GlobalMemory memory) {
    return new RamMeasurements(
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            memory.getAvailable(),
            "free"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            memory.getTotal(),
            "allocated"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            memory.getTotal() - memory.getAvailable(),
            "used"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            memory.getSwapUsed(),
            "swap used"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            memory.getSwapTotal(),
            "swap total"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            memory.getSwapTotal() - memory.getSwapUsed(),
            "swap free"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            Runtime.getRuntime().maxMemory(),
            "JVM allocated"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            Runtime.getRuntime().freeMemory(),
            "JVM free"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            DescriptiveMeasure.Summary.DESCRIPTION,
            Runtime.getRuntime().totalMemory(),
            "JVM used"));
  }

  @Override
  public Set<DescriptiveMeasurement> getValue() {
    return ImmutableSet.of(
        this.getFreeMemory(), this.getTotalMemory(), this.getUsedMemory(),
        this.getSwapUsed(), this.getSwapTotal(), this.getSwapFree(),
        this.getJvmAllocated(), this.getJvmFree(), this.getJvmUsed());
  }
}
