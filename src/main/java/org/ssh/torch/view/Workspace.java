package org.ssh.torch.view;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import java.util.Optional;
import org.ssh.torch.*;

/**
 * The Interface Workspace.
 *
 * @author Jeroen de Jong
 */
public interface Workspace extends WindowBasedTextGUI {

  /**
   * @return The title of the workspace.
   */
  String getTitle();

  /**
   * @return True if constructible, false otherwise.
   */
  default boolean isConstructible() {
    return Optional.ofNullable(this.getClass().getAnnotation(Torch.class))
        .map(Torch::constructible)
        .orElse(true);
  }

  /**
   * @return the workspace thread
   */
  default WorkspaceThread getWorkspaceThread() {
    return (WorkspaceThread) this.getGUIThread();
  }
}
