package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.gui2.*;
import org.ssh.torch.TorchUI;
import org.ssh.torch.view.Workspace;
import org.ssh.torch.view.window.WorkspaceWizardModal;

/**
 * The Class WorkspaceSwitchModal.
 *
 * @author Jeroen de Jong
 */
public class WorkspaceSwitchModal extends BasicModal {

  /**
   * Instantiates a new Workspace switch modal.
   */
  public WorkspaceSwitchModal() {
    super("Other workspaces");
    final Panel panel = new Panel();
    final ActionListBox listBox = new ActionListBox();
    listBox.addItem("Add new workspace", () -> {
      final WorkspaceWizardModal workspaceWizard = new WorkspaceWizardModal();
      workspaceWizard.setCloseWindowWithEscape(true);
      this.getWorkspace().setActiveWindow(workspaceWizard);
      this.close();
    });
    TorchUI.lit().getWorkspaces().stream()
        .filter(workspace -> !TorchUI.getActiveWorkspace().equals(workspace))
        .filter(Workspace::isConstructible)
        .reduce(listBox,
            (list, workspace) ->
                list.addItem(list.getItemCount() + ". " + workspace.getTitle(), () -> {
                  TorchUI.setActiveWorkspace(workspace);
                  this.close();
                }),
            (u, t) -> t)
        .addTo(panel);
    this.setComponent(panel);
  }
}
