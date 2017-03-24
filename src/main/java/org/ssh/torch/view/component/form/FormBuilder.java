package org.ssh.torch.view.component.form;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * The Interface FormBuilder.
 *
 * @author Jeroen de Jong
 */
public interface FormBuilder {

  /**
   * Are supported boolean.
   *
   * @param types the types
   * @return the boolean
   */
  default boolean areSupported(Class<?>... types) {
    return Stream.of(types).allMatch(this::isSupported);
  }

  /**
   * Is supported boolean.
   *
   * @param type the type
   * @return the boolean
   */
  boolean isSupported(Class<?> type);

  /**
   * Add field form builder.
   *
   * @param <T>   the type parameter
   * @param label the label
   * @param type  the type
   * @return the form builder
   */
  default <T> FormBuilder addField(String label, Class<T> type) {
    return this.addField(label, type, "");
  }

  /**
   * Add field form builder.
   *
   * @param <T>            the type parameter
   * @param label          the label
   * @param type           the type
   * @param initialContent the initial content
   * @return the form builder
   */
  default <T> FormBuilder addField(String label, Class<T> type, String initialContent) {
    FormElement<T> element = createFormElement(type);
    element.setValue(initialContent);
    return addFormElement(label, element);
  }

  /**
   * Create form element form element.
   *
   * @param <T>  the type parameter
   * @param type the type
   * @return the form element
   * @throws NoSuchFormElementException the no such form element exception
   */
// private
  <T> FormElement<T> createFormElement(Class<T> type) throws NoSuchFormElementException;

  /**
   * Add form element simple form builder.
   *
   * @param <T>     the type parameter
   * @param label   the label
   * @param element the element
   * @return the simple form builder
   */
// protected
  <T> SimpleFormBuilder addFormElement(String label, FormElement<T> element);

  /**
   * Add field form builder.
   *
   * @param <T>            the type parameter
   * @param label          the label
   * @param initialContent the initial content
   * @return the form builder
   */
  default <T> FormBuilder addField(String label, T initialContent) {
    return this.addField(label, initialContent.getClass(), initialContent.toString());
  }

  /**
   * Add button simple form builder.
   *
   * @param label    the label
   * @param onSelect the on select
   * @return the simple form builder
   */
  SimpleFormBuilder addButton(String label, Consumer<List<FormElement<?>>> onSelect);

  /**
   * Build form.
   *
   * @return the form
   */
  Form build();

  /**
   * The type No such form element exception.
   */
  class NoSuchFormElementException extends RuntimeException {

    /**
     * Instantiates a new No such form element exception.
     *
     * @param cause the cause
     */
    NoSuchFormElementException(Throwable cause) {
      super(cause);
    }
  }
}
