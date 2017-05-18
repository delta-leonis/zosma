package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.table.*;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.torch.view.RxComponent;

/**
 * The Class MeasurementsTable.
 *
 * @param <M> The type of measurements.
 * @author Jeroen de Jong
 */
@Slf4j
public class MeasurementsTable<M extends Set<DescriptiveMeasurement>> extends Table<String>
    implements RxComponent<M> {

  /**
   * Instantiates a new measurements table.
   */
  public MeasurementsTable() {
    super("Name", "Value");
    this.getTableModel().addRow("", "Loading...");
  }

  @Override
  public void onSubscribe(final Subscription subscription) {
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final M descriptiveMeasurements) {
    final TableModel<String> model =
        descriptiveMeasurements.stream().sorted()
            .reduce(new TableModel<>("Name", "Value"), (table, measurement) ->
                    table.addRow(measurement.getLabel(), measurement.getValue().toString()),
                (u, t) -> t);
    this.setTableModel(model);
  }

  @Override
  public void onError(final Throwable throwable) {
    log.debug("MeasurementsTable encountered an error", throwable);
  }

  @Override
  public void onComplete() {
    // remove on complete
    this.getParent().removeComponent(this);
  }
}
