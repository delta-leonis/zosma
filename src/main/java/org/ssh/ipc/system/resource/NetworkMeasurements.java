package org.ssh.ipc.system.resource;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lombok.Value;
import org.ssh.benchmarks.*;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.math.statistic.DescriptiveMeasure;
import oshi.hardware.NetworkIF;

/**
 * The Class NetworkMeasurements.
 *
 * This class represents a {@link GroupedMeasurement collection} of network measurements.
 *
 * @author Rimon Oz
 */
@Value
public class NetworkMeasurements implements GroupedMeasurement {
  private final String label = "network";
  private final DescriptiveMeasurement<String> name, macAddress;
  private final DescriptiveMeasurement<Integer> MTU;
  private final DescriptiveMeasurement<Long> speed;
  private final DescriptiveMeasurement<String> IPv4addr, IPv6addr;
  private final DescriptiveMeasurement<Long> packetsReceived, packetsSent, bytesReceived, bytesSent;
  private final long timestamp = System.currentTimeMillis();

  /**
   * @param networkInterface The {@link NetworkIF} to extract measurements from.
   * @return A {@link NetworkMeasurements} containing measurements extracted from {@link NetworkIF}.
   */
  public static NetworkMeasurements of(final NetworkIF networkInterface) {
    return new NetworkMeasurements(
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getName(),
            "name"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getMacaddr(),
            "MAC address"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getMTU(),
            "MTU"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getSpeed(),
            "speed"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            String.join(".", networkInterface.getIPv4addr()),
            "IPv4 address"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            String.join(":", networkInterface.getIPv6addr()),
            "IPv6 address"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getPacketsRecv(),
            "packets received"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getPacketsSent(),
            "packets sent"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getBytesRecv(),
            "bytes received"),
        new SystemInfoState<>(
            SystemComponent.NETWORK,
            DescriptiveMeasure.Summary.DESCRIPTION,
            networkInterface.getBytesSent(),
            "bytes sent"));
  }

  @Override
  public Set<DescriptiveMeasurement> getValue() {
    return ImmutableSet.of(
        this.getName(),
        this.getMacAddress(),
        this.getMTU(),
        this.getSpeed(),
        this.getIPv4addr(),
        this.getIPv6addr(),
        this.getPacketsReceived(),
        this.getPacketsSent(),
        this.getBytesReceived(),
        this.getBytesSent());
  }
}
