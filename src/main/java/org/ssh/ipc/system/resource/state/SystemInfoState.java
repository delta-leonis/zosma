package org.ssh.ipc.system.resource.state;

import lombok.Value;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.math.statistics.DescriptiveMeasure;
import org.ssh.math.units.Unit;

/**
 * The Class SystemInfoState.
 *
 * This class represents the state of a {@link SystemComponent} obtained
 * through a {@link DescriptiveMeasurement measurement}.
 *
 * @author Rimon Oz
 */
@Value
public class SystemInfoState<T> implements DescriptiveMeasurement<T> {
    /** The type of {@link SystemComponent}. **/
    private final SystemComponent systemComponent;
    /** The {@link org.ssh.math.units.Unit} belonging to the value of the measurement. */
    private final Unit unit;
    /** The {@link DescriptiveMeasure type} of measurement. */
    private final DescriptiveMeasure type;
    /** The value of the measurement. */
    private final T                   value;
    /** The description of the measurement. */
    private final String              label;
}
