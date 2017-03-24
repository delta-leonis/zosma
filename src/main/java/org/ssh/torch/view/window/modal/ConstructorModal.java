package org.ssh.torch.view.window.modal;

import org.ssh.torch.view.component.ConstructorList;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

import java.util.Collection;

/**
 * Created by jeroen.dejong on 23/02/2017.
 */
public class ConstructorModal extends BasicModal {
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
                )
        );
    }
}
