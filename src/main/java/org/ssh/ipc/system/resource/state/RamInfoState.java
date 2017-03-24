package org.ssh.ipc.system.resource.state;

import lombok.Value;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.ipc.system.resource.RamInfo;
import org.ssh.math.statistic.DescriptiveMeasure;
import org.ssh.math.unit.BaseUnit;

/**
 * The Class RamInfoState.
 *
 * This class contains measurements made on RAM.
 *
 * @author Jeroen de Jong
 */
@Value
public class RamInfoState implements RamInfo {

  private final DescriptiveMeasurement<Long> freeMemory;
  private final DescriptiveMeasurement<Long> totalMemory;
  private final DescriptiveMeasurement<Long> usedMemory;

  /**
   * Of ram info state.
   *
   * @param runtime the runtime
   * @return the ram info state
   */
  public static RamInfoState of(Runtime runtime) {
    return new RamInfoState(
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            BaseUnit.BYTE,
            DescriptiveMeasure.Summary.DESCRIPTION,
            runtime.freeMemory(),
            "Total free memory"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            BaseUnit.BYTE,
            DescriptiveMeasure.Summary.DESCRIPTION,
            runtime.totalMemory(),
            "Total allocated memory"),
        new SystemInfoState<>(
            SystemComponent.MEMORY,
            BaseUnit.BYTE,
            DescriptiveMeasure.Summary.DESCRIPTION,
            runtime.totalMemory() - runtime.freeMemory(),
            "Total used memory"));
  }
}
