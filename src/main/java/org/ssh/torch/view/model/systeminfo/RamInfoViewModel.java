package org.ssh.torch.view.model.systeminfo;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.benchmarks.*;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.ipc.system.resource.SystemInfoState;
import org.ssh.math.statistic.DescriptiveMeasure.Summary;
import org.ssh.torch.view.model.*;

/**
 * The Class RamInfoViewModel.
 *
 * @author Jeroen de Jong
 */
@Value
public class RamInfoViewModel implements GroupedMeasurementViewModel {

  @Delegate(excludes = {DescriptiveMeasurement.class})
  private final GroupedMeasurement object;

  @Override
  public String getLabel() {
    return String.format("%2.0fMB / %2.0fMB",
        this.getFreeMemory() / 1_000_000d,
        this.getAllocatedMemory() / 1_000_000d) + " (%2.0f%%)";
  }

  public double getFreeMemory() {
    return this.find("JVM free").map(DescriptiveMeasurement<Double>::getValue).orElse(0d);
  }

  public double getAllocatedMemory() {
    return this.find("JVM allocated").map(DescriptiveMeasurement<Double>::getValue).orElse(0d);
  }

  /**
   * @return A {@link RangedMeasurement ranged RAM measurement}.
   */
  public RangedMeasurement<Long> getRangedUsedMemory() {
    return new MeasurementViewModel<>(
        0L,
        this.getAllocatedMemory(),
        this.find("JVM free").orElseGet(() -> new SystemInfoState<>(
            SystemComponent.MEMORY,
            Summary.DESCRIPTION,
            0d,
            "JVM free")));
  }
}
