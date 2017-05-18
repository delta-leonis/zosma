package org.ssh.torch.view.model;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.benchmarks.*;

/**
 * The Class MeasurementViewModel.
 *
 * @param <M> The type of value in the measurement.
 * @author Jeroen de Jong
 */
@Value
public class MeasurementViewModel<M>
    implements RangedMeasurement<M>, ViewModel<DescriptiveMeasurement<M>> {
  private final M min;
  private final M max;
  @Delegate
  private final DescriptiveMeasurement<M> object;
  private final long timestamp = System.currentTimeMillis();
}
