package org.ssh.torch.view.component;

import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableModel;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.torch.view.RxComponent;

import java.util.List;

/**
 * @author jeroen.dejong
 * @since 04/02/2017.
 */
@Slf4j
public class MeasurementsTable<M extends List<DescriptiveMeasurement>> extends Table<String> implements RxComponent<M> {
    public MeasurementsTable() {
        super("Name", "Value");
        this.getTableModel().addRow("", "Loading...");
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(M descriptiveMeasurements) {
        TableModel<String> model =
                descriptiveMeasurements.stream()
                    .reduce(new TableModel<>("Name", "Value"), (table, measurement) ->
                        table.addRow(measurement.getLabel(), measurement.getValue().toString())
                    , (u, t) -> t);
        this.setTableModel(model);
    }

    @Override
    public void onError(Throwable t) {
        MeasurementsTable.log.debug("MeasurementsTable encountered an error", t);
    }

    @Override
    public void onComplete() {
        // remove on complete
        this.getParent().removeComponent(this);
    }
}
