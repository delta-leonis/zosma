package org.ssh.ipc.peripheral;

/**
 * @author Jeroen de Jong
 */
public interface Controller<I, S> {
  I getIdentifier();
  S getControls();
}
