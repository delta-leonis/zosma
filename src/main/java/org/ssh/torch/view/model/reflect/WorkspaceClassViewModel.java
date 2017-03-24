package org.ssh.torch.view.model.reflect;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.Torch;
import org.ssh.torch.view.model.ViewModel;

/**
 * The Class WorkspaceClassViewModel.
 *
 * @param <T> the type parameter
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
   * Gets callable constructors.
   *
   * @return the callable constructors
   */
  public List<ConstructorViewModel> getCallableConstructors() {
    return Stream.of(this.getConstructors())
        .map(ConstructorViewModel::new)
        .filter(ConstructorViewModel::allParametersSupported)
        .collect(Collectors.toList());
  }

  /**
   * Is constructable boolean.
   *
   * @return the boolean
   */
  public boolean isConstructable() {
    return Optional.ofNullable(object.getAnnotation(Torch.class))
        .map(Torch::constructible).orElse(true);
  }
}
