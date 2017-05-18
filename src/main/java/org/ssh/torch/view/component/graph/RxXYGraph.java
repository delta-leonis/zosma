package org.ssh.torch.view.component.graph;

import java.util.*;
import java.util.function.Function;
import lombok.*;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.*;
import org.ssh.torch.view.RxComponent;
import org.ssh.torch.view.model.GroupedMeasurementViewModel;

/**
 * The Class RxXYGraph.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 * @author Jeroen de Jong
 */
public class RxXYGraph<C extends GroupedMeasurementViewModel<? extends GroupedMeasurement>, R extends RangedMeasurement<? extends Number>>
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
