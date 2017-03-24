package org.ssh.torch.view.model.systeminfo;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.benchmarks.RangedMeasurement;
import org.ssh.ipc.system.resource.RamInfo;
import org.ssh.torch.view.model.MeasurementContextViewModel;
import org.ssh.torch.view.model.MeasurementViewModel;

/**
 * The Class RamInfoViewModel.
 *
 * @author Jeroen de Jong
 */
@Value
public class RamInfoViewModel implements MeasurementContextViewModel, RamInfo {

  @Delegate
  private final RamInfo object;

  public String getLabel() {
    return String.format("%2.0fMB / %2.0fMB",
        getFreeMemory().getValue().doubleValue() / 1_000_000d,
        getTotalMemory().getValue().doubleValue() / 1_000_000d) + " (%2.0f%%)";
  }

  /**
   * Gets ranged used memory.
   *
   * @return the ranged used memory
   */
  public RangedMeasurement<Long> getRangedUsedMemory() {
    return new MeasurementViewModel<>(
        0L,
        this.getTotalMemory().getValue(),
        this.getFreeMemory()
    );
  }
}
