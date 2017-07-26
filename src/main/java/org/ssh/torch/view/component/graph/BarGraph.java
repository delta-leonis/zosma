package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.gui2.ProgressBar;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.*;
import org.ssh.torch.view.RxComponent;
import org.ssh.torch.view.model.GroupedMeasurementViewModel;

/**
 * The Class BarGraph.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public class BarGraph<C extends GroupedMeasurementViewModel<? extends GroupedMeasurement>, R extends RangedMeasurement<? extends Number>>
    extends ProgressBar implements RxComponent<C> {

  private final Function<C, R> getter;

  @Override
  public void onSubscribe(final Subscription s) {
    s.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final C context) {
    final R measurement = getter.apply(context);
    //TODO This should be in RxGraph, which should allow
    // a class to be passed which should be the actual renderer or something
    this.setMin(measurement.getMin().intValue());
    this.setMax(measurement.getMax().intValue());
    this.setValue(measurement.getValue().intValue());
    this.setLabelFormat(context.getLabel());
  }

  @Override
  public void onError(final Throwable t) {
    this.setLabelFormat("An error occurred...");
  }

  @Override
  public void onComplete() {
    // niks ?
  }
}
