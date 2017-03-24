package org.ssh.torch.view;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import java.util.Optional;
import org.ssh.torch.Torch;
import org.ssh.torch.WorkspaceThread;
import org.ssh.torch.event.EventHandler;

/**
 * The Interface Workspace.
 *
 * @author Jeroen de Jong
 */
public interface Workspace extends WindowBasedTextGUI, EventHandler<Workspace> {

  /**
   * Gets title.
   *
   * @return the title
   */
  String getTitle();

  /**
   * Is constructible boolean.
   *
   * @return the boolean
   */
  default boolean isConstructible() {
    return Optional.ofNullable(this.getClass().getAnnotation(Torch.class))
        .map(Torch::constructible)
        .orElse(true);
  }

  /**
   * Gets workspace thread.
   *
   * @return the workspace thread
   */
  default WorkspaceThread getWorkspaceThread() {
    return (WorkspaceThread) this.getGUIThread();
  }
}
