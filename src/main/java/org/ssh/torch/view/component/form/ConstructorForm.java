package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.Panel;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author jeroen.dejong
 * @since 14/01/2017.
 */
public class ConstructorForm extends Panel {
    public ConstructorForm(
            ConstructorViewModel constructor,
            Consumer<List<FormElement<?>>> onSubmit
    ) {
        constructor.getParameterPresenters().stream()
            .reduce((FormBuilder) new SimpleFormBuilder(), // TODO uitzoeken waarom java zo kanker is
                    (form, param) -> form.addField(param.getName(), param.getType(), param.getDefault("")),
                    (u, t) -> t)
            .addButton("Construct", onSubmit)
            .build()
            .addTo(this);
    }
}
