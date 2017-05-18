package org.ssh.torch.view.model.reflect;

import java.util.*;
import java.util.stream.*;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.Torch;
import org.ssh.torch.view.model.ViewModel;

/**
 * The Class WorkspaceClassViewModel.
 *
 * @param <T> The type of {@link Class}
 * @author Jeroen de Jong
 */
@Value
public class WorkspaceClassViewModel<T> implements ViewModel<Class<T>> {

  @Delegate
  private final Class<T> object;

  public String toString() {
    return this.getSimpleName();
  }

  /**
   * @return The callable constructors of the wrapped class.
   */
  public List<ConstructorViewModel> getCallableConstructors() {
    return Stream.of(this.getConstructors())
        .map(ConstructorViewModel::new)
        .filter(ConstructorViewModel::allParametersSupported)
        .collect(Collectors.toList());
  }

  /**
   * @return True if class is constructible, false otherwise.
   */
  public boolean isConstructible() {
    return Optional.ofNullable(object.getAnnotation(Torch.class))
        .map(Torch::constructible).orElse(true);
  }
}
