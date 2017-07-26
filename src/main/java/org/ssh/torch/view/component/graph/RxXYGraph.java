package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.TerminalSize;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.GroupedMeasurement;
import org.ssh.benchmarks.RangedMeasurement;
import org.ssh.torch.view.RxComponent;
import org.ssh.torch.view.model.GroupedMeasurementViewModel;

/**
 * The Class RxXYGraph.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 * @author Jeroen de Jong
 * @author Thomas Hakkers
 */
@Value
public class RxXYGraph<C extends GroupedMeasurementViewModel<? extends GroupedMeasurement>, R extends RangedMeasurement<? extends Number>>
    extends XYGraph implements RxComponent<C> {

  private transient final Function<C, R> accessor;

  /**
   * The Measurements.
   */
  final transient List<Map.Entry<Integer, Double>> measurements = new CopyOnWriteArrayList<>();

  public RxXYGraph(final Function<C, R> accessor, final TerminalSize size) {
    this(accessor, size, new ThinLine());
  }
  /**
   * Instantiates a new Rx xy graph.
   *
   * @param accessor the accessor
   */
  public RxXYGraph(final Function<C, R> accessor, final TerminalSize size, final LineType lineType) {
    this.accessor = accessor;
    this.setLineType(lineType);
    this.setPreferredSize(size);
  }

  @Override
  public void onSubscribe(final Subscription s) {
    s.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final C context) {
    R measurement = accessor.apply(context);
    this.setMaxY(measurement.getMax().doubleValue());
    this.setMinY(measurement.getMin().doubleValue());

    measurements.add(
        new AbstractMap.SimpleEntry<>(measurements.size(), measurement.getValue().doubleValue()));
  }

  @Override
  public void onError(final Throwable t) {
  }

  @Override
  public void onComplete() {
  }
}
