package org.ssh.ipc.event.torch;

import com.googlecode.lanterna.terminal.Terminal;
import lombok.Value;
import org.ssh.ipc.event.TorchEvent;

/**
 * The Class TerminalEvent
 *
 * This class describes an {@link TorchEvent<Terminal> event} fired by a Terminal.
 *
 * @author Jeroen de Jong
 */
@Value
public class TerminalEvent implements TorchEvent<Terminal> {
  /**
   * The action which this event describes
   */
  private final Action action;
  /**
   * The terminal which fired this event
   */
  private final Terminal source;
  /**
   * The timestamp when this event has been created
   */
  private final long timestamp = System.currentTimeMillis();
}
