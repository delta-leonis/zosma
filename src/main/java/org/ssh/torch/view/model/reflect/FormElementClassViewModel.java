package org.ssh.torch.view.model.reflect;

import java.lang.reflect.*;
import java.util.stream.Stream;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.view.component.form.FormElement;
import org.ssh.torch.view.model.ViewModel;

/**
 * The Class FormElementClassViewModel.
 *
 * @author Jeroen de Jong
 */
@Value
public class FormElementClassViewModel implements ViewModel<Class<? extends FormElement>> {

  @Delegate
  private final Class object;

  /**
   * Gets element type.
   *
   * @return the element type
   */
  public Class<?> getElementType() {
    // Catch the first superclass with an generic
    Type type = object.getGenericSuperclass();
    // if the superclass is equal, the superclass didn't have an generic
    if (type == object.getSuperclass())
    // thus grab the first Generic interface (FormElement)
    // Note: In the future we may need to search through the list to find FormElement
    {
      type = object.getGenericInterfaces()[0];
    }
    // and grab the first actual type argument
    return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
  }

  /**
   * Has empty constructor boolean.
   *
   * @return the boolean
   */
  public boolean hasEmptyConstructor() {
    return Stream.of(object.getConstructors())
        .anyMatch(constructor -> constructor.getParameterCount() == 0);
  }
}
