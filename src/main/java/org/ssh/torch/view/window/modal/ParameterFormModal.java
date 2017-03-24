package org.ssh.torch.view.window.modal;

import org.ssh.torch.TorchAction;
import org.ssh.torch.TorchUI;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.view.Workspace;
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
              TorchUI.dispatchTorchEvent(TorchEvent.of(
                  constructor.<Workspace>create(formData),
                  TorchAction.CREATED
              ));
            }
        ));
  }
}
