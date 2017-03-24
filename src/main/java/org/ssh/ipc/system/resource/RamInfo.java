package org.ssh.ipc.system.resource;

import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.benchmarks.MeasurementContext;

import java.util.Arrays;
import java.util.List;

/**
 * The Interface RamInfo
 *
 * This interface describes the functionality of a {@link MeasurementContext}
 * which contains measurements done on the RAM.
 *
 * @author Jeroen de Jong
 */
public interface RamInfo extends MeasurementContext {
    /**
     * Returns the free memory.
     *
     * @return The free memory
     */
    DescriptiveMeasurement<Long> getFreeMemory();

    /**
     * Returns the total memory.
     *
     * @return The total memory
     */
    DescriptiveMeasurement<Long> getTotalMemory();

    /**
     * Returns the used memory.
     *
     * @return The used memory
     */
    DescriptiveMeasurement<Long> getUsedMemory();

    @Override
    default List<DescriptiveMeasurement<?>> getMeasurements(){
        return Arrays.asList(this.getFreeMemory(), this.getTotalMemory(), this.getUsedMemory());
    }
}
