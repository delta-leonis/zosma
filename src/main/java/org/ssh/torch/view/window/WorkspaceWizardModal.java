package org.ssh.torch.view.window;

import org.reflections.Reflections;
import org.ssh.torch.view.AbstractWorkspace;
import org.ssh.torch.view.BasicWindow;
import org.ssh.torch.view.component.WorkspaceList;
import org.ssh.torch.view.window.modal.ConstructorModal;

/**
 * The Class WorkspaceWizardModal.
 *
 * @author Jeroen de Jong
 */
public class WorkspaceWizardModal extends BasicWindow {

  /**
   * Instantiates a new Workspace wizard modal.
   */
  public WorkspaceWizardModal() {
    super("Workspace wizard");
    this.setComponent(
        new WorkspaceList(
            new Reflections("org.ssh.torch.workspace")
                .getSubTypesOf(AbstractWorkspace.class),
            selectedScene -> {
              this.getWorkspace().addWindow(
                  new ConstructorModal(selectedScene.getCallableConstructors())
              );
              this.close();
            }
        ));
  }
}
