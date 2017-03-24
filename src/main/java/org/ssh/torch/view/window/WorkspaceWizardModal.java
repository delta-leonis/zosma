package org.ssh.torch.view.window;

import org.reflections.Reflections;
import org.ssh.torch.view.AbstractWorkspace;
import org.ssh.torch.view.BasicWindow;
import org.ssh.torch.view.component.WorkspaceList;
import org.ssh.torch.view.window.modal.ConstructorModal;

/**
 * @author jeroen.dejong
 * @since 15/01/2017.
 */
public class WorkspaceWizardModal extends BasicWindow {
    public WorkspaceWizardModal() {
        super("Workspace wizard");
        this.setComponent(
                new WorkspaceList(
                        new Reflections("org.ssh.torch.view.workspace").getSubTypesOf(AbstractWorkspace.class),
                        selectedScene -> {
                            this.getWorkspace().addWindow(
                                    new ConstructorModal(selectedScene.getCallableConstructors())
                            );
                            this.close();
                        }
                )
        );
    }
}
