package org.ssh.ipc.system.resource.state;

import com.google.common.primitives.Doubles;
import java.util.List;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.ssh.benchmarks.DescriptiveMeasurement;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.ipc.system.resource.ProcessorInfo;
import org.ssh.math.statistic.DescriptiveMeasure;
import org.ssh.math.unit.BaseUnit;
import org.ssh.math.unit.Description;
import oshi.hardware.CentralProcessor;

/**
 * The Class ProcessorInfoState.
 *
 * This class contains measurements made on a {@link CentralProcessor}.
 *
 * @author Jeroen de Jong
 */
@Value
@NonFinal
public class ProcessorInfoState implements ProcessorInfo {

  DescriptiveMeasurement<String> family, identifier, model, name, stepping, serialNumber, vendor;
  DescriptiveMeasurement<Integer> logicalProcessors, physicalProcessors;
  DescriptiveMeasurement<Boolean> cpu64bit;
  DescriptiveMeasurement<Double> averageSystemLoad, systemLoad;
  DescriptiveMeasurement<Long> frequency;
  DescriptiveMeasurement<List<Double>> loadPerCore;

  /**
   * Creates a {@link ProcessorInfoState} from a supplied {@link CentralProcessor}.
   *
   * @param processor The {@link CentralProcessor}.
   * @return A {@link ProcessorInfoState}.
   */
  public static ProcessorInfoState of(CentralProcessor processor) {
    return new ProcessorInfoState(
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getFamily(),
            "Family"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getIdentifier(),
            "Identifier"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getModel(),
            "Model"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getName(),
            "Name"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getStepping(),
            "Stepping"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getSystemSerialNumber(),
            "System serial number"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            Description.TEXT,
            DescriptiveMeasure.Summary.DESCRIPTION,
            processor.getVendor(),
            "Vendor"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.UNIT,
            DescriptiveMeasure.Count.TOTAL,
            processor.getLogicalProcessorCount(),
            "Number of logical processors"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.UNIT,
            DescriptiveMeasure.Count.TOTAL,
            processor.getPhysicalProcessorCount(),
            "Number of physical processors"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.TRUTH,
            DescriptiveMeasure.Summary.VALIDITY,
            processor.isCpu64bit(),
            "Is 64-bit?"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.RATIO,
            DescriptiveMeasure.Continuous.Center.MEAN,
            processor.getSystemLoadAverage(),
            "System load average"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.RATIO,
            DescriptiveMeasure.Count.TOTAL,
            processor.getSystemCpuLoad(),
            "System CPU load"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.UNIT,
            DescriptiveMeasure.Count.TOTAL,
            processor.getVendorFreq(),
            "Vendor frequency"),
        new SystemInfoState<>(
            SystemComponent.CPU,
            BaseUnit.UNIT,
            DescriptiveMeasure.Count.DISPERSION,
            Doubles.asList(processor.getProcessorCpuLoadBetweenTicks()),
            "Core load between ticks"));
  }
}
