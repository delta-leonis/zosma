package org.ssh.torch.view.model.reflect;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.Torch;
import org.ssh.torch.view.model.ViewModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jeroen.dejong
 * @since 12/01/2017.
 */
@Value
public class WorkspaceClassViewModel<T> implements ViewModel<Class<T>> {
    @Delegate
    private final Class<T> object;

    public String toString() {
        return this.getSimpleName();
    }

    public List<ConstructorViewModel> getCallableConstructors() {
        return Stream.of(this.getConstructors())
                .map(ConstructorViewModel::new)
                .filter(ConstructorViewModel::allParametersSupported)
                .collect(Collectors.toList());
    }

    public boolean isConstructable() {
        return Optional.ofNullable(object.getAnnotation(Torch.class))
                .map(Torch::constructible).orElse(true);
    }
}
