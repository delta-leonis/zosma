package org.ssh.torch;

import java.util.List;
import org.ssh.torch.view.Workspace;

/**
 * The Interface WorkspaceManager.
 *
 * @author Jeroen de Jong
 */
public interface WorkspaceManager {

  /**
   * Gets active workspace.
   *
   * @return the active workspace
   */
  Workspace getActiveWorkspace();

  /**
   * Sets active workspace.
   *
   * @param workspace the workspace
   * @return the active workspace
   */
  WorkspaceManager setActiveWorkspace(Workspace workspace);

  /**
   * Gets workspaces.
   *
   * @return the workspaces
   */
  List<Workspace> getWorkspaces();

  /**
   * Add workspace workspace manager.
   *
   * @param workspace the workspace
   * @return the workspace manager
   */
  WorkspaceManager addWorkspace(Workspace workspace);
}
