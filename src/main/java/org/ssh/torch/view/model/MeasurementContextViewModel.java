package org.ssh.torch.view.model;

import org.ssh.benchmarks.MeasurementContext;

/**
 * The interface Measurement context view model.
 *
 * @param <M> type of measurement
 */
public interface MeasurementContextViewModel<M extends MeasurementContext> extends ViewModel<M> {

  /**
   * Gets label.
   *
   * @return the label
   */
  String getLabel();
}
