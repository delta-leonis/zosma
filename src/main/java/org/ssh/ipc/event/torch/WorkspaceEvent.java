package org.ssh.ipc.event.torch;

import lombok.Value;
import org.ssh.ipc.event.TorchEvent;
import org.ssh.torch.view.Workspace;

/**
 * The Class WorkspaceEvent
 *
 * This class describes an {@link TorchEvent event} fired by a Workspace.
 *
 * @author Jeroen de Jong
 */
@Value
public class WorkspaceEvent implements TorchEvent<Workspace> {
  /**
   * The action which this event describes
   */
  private final Action action;
  /**
   * The workspace which fired this event
   */
  private final Workspace source;
  /**
   * The timestamp when this event has been created
   */
  private final long timestamp = System.currentTimeMillis();
}
