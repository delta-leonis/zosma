package org.ssh.ipc.system.resource;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lombok.Value;
import org.ssh.benchmarks.*;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.math.statistic.DescriptiveMeasure;
import oshi.hardware.HWDiskStore;

/**
 * The Class DiskMeasurements.
 *
 * This class represents a {@link GroupedMeasurement collection} of disk measurements.
 *
 * @author Rimon Oz
 */
@Value
public class DiskMeasurements implements GroupedMeasurement {
  private final String label = "disk";
  private final DescriptiveMeasurement<String> name;
  private final DescriptiveMeasurement<Long> size;
  private final DescriptiveMeasurement<String> model, serial;
  private final DescriptiveMeasurement<Long> reads, readBytes, writes, writeBytes, transferTime;
  private final long timestamp = System.currentTimeMillis();

  /**
   * @param hardDisk The {@link HWDiskStore} to extract measurements from.
   * @return A {@link DiskMeasurements} containing measurements extracted from {@link HWDiskStore}.
   */
  public static DiskMeasurements of(final HWDiskStore hardDisk) {
    return new DiskMeasurements(
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getName(),
            "name"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getSize(),
            "size"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getModel(),
            "model"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getSerial(),
            "serial"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getReads(),
            "reads"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getReadBytes(),
            "bytes read"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getWrites(),
            "writes"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getWriteBytes(),
            "bytes written"),
        new SystemInfoState<>(
            SystemComponent.DISK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            hardDisk.getTransferTime(),
            "transfer time"));
  }

  @Override
  public Set<DescriptiveMeasurement> getValue() {
    return ImmutableSet.of(
        this.getName(),
        this.getSize(),
        this.getModel(),
        this.getSerial(),
        this.getReads(),
        this.getReadBytes(),
        this.getWrites(),
        this.getWriteBytes(),
        this.getTransferTime());
  }
}
