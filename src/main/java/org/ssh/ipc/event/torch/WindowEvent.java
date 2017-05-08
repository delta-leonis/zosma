package org.ssh.ipc.event.torch;

import lombok.Value;
import org.ssh.ipc.event.TorchEvent;
import org.ssh.torch.view.Window;

/**
 * The Class WindowEvent
 *
 * This class describes an {@link TorchEvent event} fired by a Window.
 *
 * @author Jeroen de Jong
 */
@Value
public class WindowEvent implements TorchEvent<Window> {
  /**
   * The action which this event describes
   */
  private final Action action;
  /**
   * The window which fired this event
   */
  private final Window source;
  /**
   * The timestamp when this event has been created
   */
  private final long timestamp = System.currentTimeMillis();
}
