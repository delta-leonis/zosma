package org.ssh.torch.view.window.modal;

import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.ssh.torch.view.component.form.ConstructorForm;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;

/**
 * The Class ParameterFormModal.
 *
 * @author Jeroen de Jong
 */
@Slf4j
public class ParameterFormModal<C> extends BasicModal {
  /**
   * Instantiates a new parameter form modal.
   *
   * @param constructor The constructor to call with the parameters.
   * @param constructedConsumer The callback to call once the object is constructed.
   */
  public ParameterFormModal(final ConstructorViewModel constructor, final Consumer<C> constructedConsumer) {
    super("Choose parameters: ");
    this.setComponent(
        new ConstructorForm(
            constructor,
            formData -> {
              try {
                constructedConsumer.accept(constructor.create(formData));
                this.close();
              } catch (final InstantiationException | IllegalAccessException | InvocationTargetException exception) {
                MessageDialog.showMessageDialog(
                    this.getTextGUI(),
                    "Unable to construct " + constructor.getDeclaringClass().getSimpleName(),
                    "Unable to construct " + constructor.getDeclaringClass().getSimpleName()
                        + " using supplied parameters.");
              }
            }));
  }
}
