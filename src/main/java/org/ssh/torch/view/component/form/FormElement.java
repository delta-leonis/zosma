package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.Interactable;
import java.util.function.Function;

/**
 * The Interface FormElement.
 *
 * @param <T> The type of value inside the element.
 * @author Jeroen de Jong
 */
public interface FormElement<T> extends Interactable {

  /**
   * @return The value of the element.
   */
  T getValue();

  /**
   * @param value The string to set on the element.
   * @return The element itself.
   */
  default FormElement setValue(final String value) {
    return setValue(getParser().apply(value));
  }

  /**
   * @param value The value to set on the element.
   * @return the value
   */
  FormElement setValue(final T value);

  /**
   * @return The parser which converts the field value to {@code <T>}.
   */
  Function<String, T> getParser();
}
