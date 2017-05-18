package org.ssh.torch.view.model.reflect;

import java.lang.reflect.*;
import java.util.List;
import java.util.stream.*;
import lombok.Value;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.ssh.torch.view.component.form.*;
import org.ssh.torch.view.model.ViewModel;

/**
 * The Class ConstructorViewModel.
 *
 * @author Jeroen de Jong
 */
@Value
@Slf4j
public class ConstructorViewModel implements ViewModel<Constructor> {

  @Delegate
  private Constructor object;

  /**
   * @return True if parameters are supported, false otherwise.
   */
  public boolean allParametersSupported() {
    SimpleFormBuilder formBuilder = new SimpleFormBuilder();
    return getParameterPresenters().stream()
        .map(ParameterViewModel::getType)
        .allMatch(formBuilder::isSupported);
  }

  /**
   * @return A {@link List} of {@link ParameterViewModel} for each parameter of the constructor.
   */
  public List<ParameterViewModel> getParameterPresenters() {
    return Stream.of(object.getParameters())
        .map(ParameterViewModel::new)
        .collect(Collectors.toList());
  }

  /**
   * @return True if all parameters names are present, false otherwise.
   */
  public boolean allParameterNamePresent() {
    return this.getParameterPresenters()
        .stream()
        .allMatch(ParameterViewModel::isNamePresent);
  }

  /**
   * Calls the constructor with the values in the supplied {@link List} of {@link FormElement}.
   *
   * @param <O>  The type of object produced by the constructor.
   * @param data The list of {@link FormElement} containing values used as arguments to the
   *             constructor.
   * @return The instantiated object produced by the constructor.
   */
  public <O> O create(final List<FormElement<?>> data)
      throws IllegalAccessException, InvocationTargetException, InstantiationException {
    try {
      return (O) this.newInstance(data.stream().map(FormElement::getValue).toArray());
    } catch (final InstantiationException | IllegalAccessException | InvocationTargetException exception) {
      log.debug(String.format("Could not create %s", this.getDeclaringClass().getSimpleName()), exception);
      throw exception;
    }
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", this.getDeclaringClass().getSimpleName(),
        this.getParameterDescription());
  }

  /**
   * @return A {@link String} containing the method signature of the constructor.
   */
  private String getParameterDescription() {
    return getParameterPresenters().stream()
        .map(ParameterViewModel::getDescription)
        .reduce((list, param) -> list + ", " + param).orElse("");
  }
}
