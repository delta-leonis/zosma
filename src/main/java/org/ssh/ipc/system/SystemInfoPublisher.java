package org.ssh.ipc.system;

import org.reactivestreams.Publisher;
import org.ssh.benchmarks.DescriptiveMeasurement;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

/**
 * The Class RamInfoPublisher.
 *
 * This class describes a {@link org.reactivestreams.Publisher} of system information.
 *
 * @param <T> the type parameter
 * @author Jeroen de Jong
 */
public abstract class SystemInfoPublisher<T extends DescriptiveMeasurement> implements Publisher<T> {

  /**
   * The INTERVAL between new data in ms
   */
  protected static final Integer INTERVAL = 500;
  /**
   * The {@link SystemInfo source} of the state information.
   */
  protected final SystemInfo systemInfo = new SystemInfo();
  /**
   * The {@link HardwareAbstractionLayer source} of the hardware state information.
   */
  protected final HardwareAbstractionLayer hal = this.systemInfo.getHardware();
}
