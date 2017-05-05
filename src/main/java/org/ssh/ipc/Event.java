package org.ssh.ipc;

import org.ssh.math.Temporal;

/**
 * The Interface Event.
 *
 * This interface describes the functionality of an event, ie. an object which can be
 * emitted using {@link Zosma}.
 *
 * @author Rimon Oz
 */
public interface Event<S> extends Temporal {
  /**
   * @return The source of the event.
   */
  S getSource();
}
