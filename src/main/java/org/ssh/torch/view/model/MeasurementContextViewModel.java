package org.ssh.torch.view.model;

import org.ssh.benchmarks.MeasurementContext;

/**
 * @param <M> type of measurement
 */
public interface MeasurementContextViewModel<M extends MeasurementContext> extends ViewModel<M> {
    String getLabel();
}
