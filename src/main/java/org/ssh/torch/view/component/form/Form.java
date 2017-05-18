package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * The Class Form.
 *
 * @author Jeroen de Jong
 */
public class Form extends Panel {

  private final Panel labels;
  private final Panel elements;
  private final Panel buttons;

  private Form() {
    super();
    this.labels = new Panel();
    this.buttons = new Panel();
    this.elements = new Panel();
    this.setLayoutManager(new BorderLayout());
    this.addComponent(labels, BorderLayout.Location.LEFT);
    this.addComponent(elements, BorderLayout.Location.RIGHT);
    this.addComponent(buttons, BorderLayout.Location.BOTTOM);
  }

  /**
   * Creates a form using the supplied elements and buttons.
   *
   * @param elements the elements
   * @param buttons  the buttons
   * @return the form
   */
  public static Form of(
      final Map<String, FormElement<?>> elements,
      final Map<String, Consumer<List<FormElement<?>>>> buttons
  ) {
    final Form form = new Form();
    // create buttons
    buttons.entrySet().stream()
        .map(entry -> new Button(entry.getKey(),
            () -> entry.getValue().accept(form.getFormData())))
        .forEach(form::addComponent);
    // add form elements
    elements.entrySet().forEach(entry -> form.addComponent(entry.getKey(), entry.getValue()));
    return form;
  }

  /**
   * @return The data inside the form.
   */
  public synchronized List<FormElement<?>> getFormData() {
    return this.elements.getChildren().stream()
        .filter(child -> child instanceof FormElement)
        .map(child -> (FormElement<?>) child)
        .collect(Collectors.toList());
  }

  /**
   * Add component form.
   *
   * @param label   the label
   * @param element the element
   * @return the form
   */
  public synchronized Form addComponent(final String label, final FormElement<?> element) {
    this.labels.addComponent(new Label(label));
    this.elements.addComponent(element);
    return this;
  }

  /**
   * Add component form.
   *
   * @param button the button
   * @return the form
   */
  public synchronized Form addComponent(final Button button) {
    this.buttons.addComponent(button);
    return this;
  }
}
