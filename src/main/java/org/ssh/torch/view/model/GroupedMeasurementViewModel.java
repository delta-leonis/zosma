package org.ssh.torch.view.model;

import org.ssh.benchmarks.GroupedMeasurement;

/**
 * The Interface GroupedMeasurementViewModel.
 *
 * @param <M> The type of {@link GroupedMeasurement}.
 * @author Jeroen de Jong
 */
public interface GroupedMeasurementViewModel<M extends GroupedMeasurement> extends ViewModel<M> {

  /**
   * @return The label of the measurement.
   */
  String getLabel();
}
