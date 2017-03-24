package org.ssh.ipc.system;

/**
 * The Enum SystemInfo.
 *
 * This enumeration lists system component which are attached to the system
 * the program is running on.
 *
 * @author Rimon Oz
 */
public enum SystemComponent {
  /**
   * Memory system component.
   */
  MEMORY,
  /**
   * Cpu system component.
   */
  CPU,
  /**
   * Power system component.
   */
  POWER,
  /**
   * Disk system component.
   */
  DISK,
  /**
   * Network system component.
   */
  NETWORK,
  /**
   * Display system component.
   */
  DISPLAY,
  /**
   * Bus system component.
   */
  BUS
}
