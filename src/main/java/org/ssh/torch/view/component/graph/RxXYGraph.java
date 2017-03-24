package org.ssh.torch.view.component.graph;

import lombok.AccessLevel;
import lombok.Getter;
import org.reactivestreams.Subscription;
import org.ssh.benchmarks.MeasurementContext;
import org.ssh.benchmarks.RangedMeasurement;
import org.ssh.torch.view.RxComponent;
import org.ssh.torch.view.model.MeasurementContextViewModel;

import java.util.*;
import java.util.function.Function;

/**
 * Created by jeroen.dejong on 25/02/2017.
 */
public class RxXYGraph<C extends MeasurementContextViewModel<? extends MeasurementContext>, R extends RangedMeasurement<? extends Number>> extends XYGraph implements RxComponent<C> {
    private final Function<C, R> getter;
    @Getter(AccessLevel.NONE)
    List<Map.Entry<Integer, Integer>> measurements = new ArrayList<>();

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
        measurements.add(new AbstractMap.SimpleEntry<>(measurements.size(), measurement.getValue().intValue()));
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
