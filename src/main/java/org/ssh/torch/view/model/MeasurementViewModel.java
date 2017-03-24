package org.ssh.torch.view.model;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.benchmarks.RangedMeasurement;

/**
 * Created by jeroen.dejong on 24/02/2017.
 */
@Value
public class MeasurementViewModel<M> implements RangedMeasurement<M>, ViewModel<DescriptiveMeasurement<M>> {
    M min;
    M max;
    @Delegate
    DescriptiveMeasurement<M> object;
}
