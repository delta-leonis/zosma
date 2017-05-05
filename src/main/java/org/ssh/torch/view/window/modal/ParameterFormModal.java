package org.ssh.torch.view.window.modal;

import org.ssh.ipc.Zosma;
import org.ssh.ipc.event.TorchEvent.Action;
import org.ssh.ipc.event.torch.WorkspaceEvent;
import org.ssh.torch.view.component.form.ConstructorForm;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * The Class ParameterFormModal.
 *
 * @author Jeroen de Jong
 */
public class ParameterFormModal extends BasicModal {
  /**
   * Instantiates a new Parameter form modal.
   *
   * @param constructor the constructor
   */
  public ParameterFormModal(ConstructorViewModel constructor) {
    super("Choose parameters: ");
    this.setComponent(
        new ConstructorForm(
            constructor,
            formData -> {
              this.close();
              Zosma.broadcast(new WorkspaceEvent(
                  Action.CREATED,
                  constructor.create(formData)));
            }
        ));
  }
}
