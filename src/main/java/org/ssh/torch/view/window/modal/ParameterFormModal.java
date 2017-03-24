package org.ssh.torch.view.window.modal;

import org.ssh.torch.TorchAction;
import org.ssh.torch.TorchUI;
import org.ssh.torch.event.TorchEvent;
import org.ssh.torch.view.Workspace;
import org.ssh.torch.view.component.form.ConstructorForm;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * Created by jeroen.dejong on 23/02/2017.
 */
public class ParameterFormModal extends BasicModal {
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
            )
        );
    }
}
