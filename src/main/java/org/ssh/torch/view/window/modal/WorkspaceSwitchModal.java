package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Panel;
import org.ssh.torch.TorchUI;
import org.ssh.torch.view.Workspace;
import org.ssh.torch.view.window.WorkspaceWizardModal;

/**
 * @author jeroen.dejong
 * @since 02/02/2017.
 */
public class WorkspaceSwitchModal extends BasicModal {
    public WorkspaceSwitchModal() {
        super("Other workspaces");
        final Panel panel = new Panel();
        ActionListBox listBox = new ActionListBox();
        listBox.addItem("Add new workspace", () -> {
            WorkspaceWizardModal workspaceWizard = new WorkspaceWizardModal();
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
