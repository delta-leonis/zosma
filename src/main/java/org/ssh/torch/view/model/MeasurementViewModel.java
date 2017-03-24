package org.ssh.torch.view.model;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.benchmarks.RangedMeasurement;

/**
 * The Class MeasurementViewModel.
 *
 * @param <M> the type parameter
 * @author Jeroen de Jong
 */
@Value
public class MeasurementViewModel<M>
    implements RangedMeasurement<M>, ViewModel<DescriptiveMeasurement<M>> {

  M min;
  M max;
  @Delegate
  DescriptiveMeasurement<M> object;
}
