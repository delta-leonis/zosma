package org.ssh.torch.view.model.reflect;

import java.lang.reflect.Parameter;
import java.util.Optional;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.Default;
import org.ssh.torch.view.model.ViewModel;

/**
 * The Class ParameterViewModel.
 *
 * @author Jeroen de Jong
 */
@Value
public class ParameterViewModel implements ViewModel<Parameter> {

  @Delegate
  Parameter object;

  /**
   * Gets default.
   *
   * @param fallback the fallback
   * @return the default
   */
  public String getDefault(final String fallback) {
    return getDefault().orElse(fallback);
  }

  /**
   * Gets default.
   *
   * @return the default
   */
  public Optional<String> getDefault() {
    return Optional.ofNullable(object.getAnnotation(Default.class))
        .map(Default::value);
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return String.format("%s: %s", this.getName(), this.getTypeName());
  }

  /**
   * Gets type name.
   *
   * @return the type name
   */
  public String getTypeName() {
    return this.getType().getSimpleName();
  }
}
