package org.ssh.torch.view.window.modal;

import java.util.Collection;
import org.ssh.torch.view.component.ConstructorList;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * The Class ConstructorModal.
 *
 * @author Jeroen de Jong
 */
public class ConstructorModal extends BasicModal {

  /**
   * Instantiates a new Constructor modal.
   *
   * @param constructors the constructors
   */
  public ConstructorModal(Collection<ConstructorViewModel> constructors) {
    super("Select constructor: ");
    this.setComponent(
        new ConstructorList(
            constructors,
            constructor -> {
              this.getWorkspace().addWindow(
                  new ParameterFormModal(constructor)
              );
              this.close();
            }
        ));
  }
}
