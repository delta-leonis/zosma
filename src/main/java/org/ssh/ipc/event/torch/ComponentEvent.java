package org.ssh.ipc.event.torch;

import com.googlecode.lanterna.gui2.Component;
import lombok.Value;
import org.ssh.ipc.event.TorchEvent;

/**
 * The Class ComponentEvent
 *
 * This class describes an {@link TorchEvent event} fired by a {@link Component}.
 *
 * @author Jeroen de Jong
 */
@Value
public class ComponentEvent implements TorchEvent<Component> {
  /**
   * The action which this event describes
   */
  private final Action action;
  /**
   * The component which fired this event
   */
  private final Component source;
  /**
   * The timestamp when this event has been created
   */
  private final long timestamp = System.currentTimeMillis();
}
