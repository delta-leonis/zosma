package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.Interactable;
import java.util.function.Function;

/**
 * The Interface FormElement.
 *
 * @param <T> the type parameter
 * @author Jeroen de Jong
 */
public interface FormElement<T> extends Interactable {

  /**
   * Gets value.
   *
   * @return the value
   */
  T getValue();

  /**
   * Sets value.
   *
   * @param value the value
   * @return the value
   */
  default FormElement setValue(String value) {
    return setValue(getParser().apply(value));
  }

  /**
   * Sets value.
   *
   * @param value the value
   * @return the value
   */
  FormElement setValue(T value);

  /**
   * Gets parser.
   *
   * @return the parser
   */
  Function<String, T> getParser();
}
