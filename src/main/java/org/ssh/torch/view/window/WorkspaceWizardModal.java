package org.ssh.torch.view.window;

import org.reflections.Reflections;
import org.ssh.ipc.Zosma;
import org.ssh.ipc.event.TorchEvent.Action;
import org.ssh.ipc.event.torch.WorkspaceEvent;
import org.ssh.torch.view.*;
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
              this.getWorkspace().addWindow(new ConstructorModal<Workspace>(
                  selectedScene.getCallableConstructors(),
                  workspace -> Zosma.broadcast(new WorkspaceEvent(
                      Action.CREATED,
                      workspace))));
              this.close();
            }));
  }
}
