package org.ssh.torch;

import org.ssh.torch.view.Workspace;

import java.util.List;

/**
 * @author jeroen.dejong
 * @since 01/02/2017.
 */
public interface WorkspaceManager {
    Workspace getActiveWorkspace();

    WorkspaceManager setActiveWorkspace(Workspace workspace);

    List<Workspace> getWorkspaces();

    WorkspaceManager addWorkspace(Workspace workspace);
}
