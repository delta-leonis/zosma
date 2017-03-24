package org.ssh.ipc.system.resource;

import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.benchmarks.MeasurementContext;

import java.util.Arrays;
import java.util.List;

/**
 * The Interface ProcessorInfo.
 *
 * This interface describes the functionality of a {@link MeasurementContext}
 * which contains measurements done on the {@link oshi.hardware.CentralProcessor}.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
public interface ProcessorInfo extends MeasurementContext {
    /**
     * Returns the processor family-name.
     *
     * @return The processor family-name
     */
    DescriptiveMeasurement<String> getFamily();

    /**
     * Returns the identifier of the processor.
     *
     * @return The identifier of the processor.
     */
    DescriptiveMeasurement<String> getIdentifier();

    /**
     * Returns the processor model.
     *
     * @return The processor model.
     */
    DescriptiveMeasurement<String> getModel();

    /**
     * Returns the processor name.
     *
     * @return The processor name.
     */
    DescriptiveMeasurement<String> getName();

    /**
     * Returns the stepping of the processor.
     *
     * @return The stepping of the processor.
     */
    DescriptiveMeasurement<String> getStepping();

    /**
     * Returns the serial number of the processor.
     *
     * @return The serial number of the processor.
     */
    DescriptiveMeasurement<String> getSerialNumber();

    /**
     * Returns the vendor of the processor.
     *
     * @return The vendor of the processor.
     */
    DescriptiveMeasurement<String> getVendor();

    /**
     * Returns the number of logical processors.
     *
     * @return The number of logical processors.
     */
    DescriptiveMeasurement<Integer> getLogicalProcessors();

    /**
     * Returns the number of physical processors.
     *
     * @return The number of physical processors.
     */
    DescriptiveMeasurement<Integer> getPhysicalProcessors();

    /**
     * Returns a boolean indicating whether the system has a 64-bit architecture.
     *
     * @return True if 64-bit, false otherwise.
     */
    DescriptiveMeasurement<Boolean> getCpu64bit();

    /**
     * Returns the average system load.
     *
     * @return The average system load.
     */
    DescriptiveMeasurement<Double> getAverageSystemLoad();

    /**
     * Returns the current system load.
     *
     * @return The current system load.
     */
    DescriptiveMeasurement<Double> getSystemLoad();

    /**
     * Returns the current CPU frequency.
     *
     * @return The current CPU frequency.
     */
    DescriptiveMeasurement<Long> getFrequency();

    /**
     * Returns the load per core.
     *
     * @return The load per core
     */
    DescriptiveMeasurement<List<Double>> getLoadPerCore();

    default List<DescriptiveMeasurement<?>> getMeasurements() {
        return Arrays.asList(
                this.getFamily(),
                this.getIdentifier(),
                this.getModel(),
                this.getName(),
                this.getStepping(),
                this.getSerialNumber(),
                this.getVendor(),
                this.getLogicalProcessors(),
                this.getPhysicalProcessors(),
                this.getCpu64bit(),
                this.getAverageSystemLoad(),
                this.getSystemLoad(),
                this.getFrequency(),
                this.getLoadPerCore());
    }
}
