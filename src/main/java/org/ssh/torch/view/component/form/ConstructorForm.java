package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.Panel;
import java.util.List;
import java.util.function.Consumer;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * The Class ConstructorForm.
 *
 * @author Jeroen de Jong
 */
public class ConstructorForm extends Panel {

  /**
   * Instantiates a new Constructor form.
   *
   * @param constructor the constructor
   * @param onSubmit    the on submit
   */
  public ConstructorForm(
      ConstructorViewModel constructor,
      Consumer<List<FormElement<?>>> onSubmit
  ) {
    constructor.getParameterPresenters().stream()
        .reduce((FormBuilder) new SimpleFormBuilder(),
            // TODO uitzoeken waarom java zo kanker is
            (form, param) -> form
                .addField(param.getName(), param.getType(), param.getDefault("")),
            (u, t) -> t)
        .addButton("Construct", onSubmit)
        .build()
        .addTo(this);
  }
}
