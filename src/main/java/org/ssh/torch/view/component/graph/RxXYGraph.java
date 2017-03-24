package org.ssh.torch.view.component.graph;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Getter;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.MeasurementContext;
import org.ssh.benchmarks.RangedMeasurement;
import org.ssh.torch.view.RxComponent;
import org.ssh.torch.view.model.MeasurementContextViewModel;

/**
 * The Class RxXYGraph.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 * @author Jeroen de Jong
 */
public class RxXYGraph<C extends MeasurementContextViewModel<? extends MeasurementContext>, R extends RangedMeasurement<? extends Number>>
    extends XYGraph implements RxComponent<C> {

  private final Function<C, R> getter;
  /**
   * The Measurements.
   */
  @Getter(AccessLevel.NONE)
  List<Map.Entry<Integer, Integer>> measurements = new ArrayList<>();

  /**
   * Instantiates a new Rx xy graph.
   *
   * @param getter the getter
   */
  public RxXYGraph(Function<C, R> getter) {
    this.getter = getter;
  }

  @Override
  public void onSubscribe(Subscription s) {
    s.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(C context) {
    R measurement = getter.apply(context);
    measurements.add(
        new AbstractMap.SimpleEntry<>(measurements.size(), measurement.getValue().intValue()));
  }

  @Override
  public void onError(Throwable t) {
  }

  @Override
  public void onComplete() {
  }

  @Override
  protected List<Map.Entry<Integer, Integer>> getData() {
    return Collections.unmodifiableList(this.measurements);
  }
}
