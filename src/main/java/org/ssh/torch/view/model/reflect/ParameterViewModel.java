package org.ssh.torch.view.model.reflect;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.Default;
import org.ssh.torch.view.model.ViewModel;

import java.lang.reflect.Parameter;
import java.util.Optional;

/**
 * @author jeroen.dejong
 * @since 13/01/2017.
 */
@Value
public class ParameterViewModel implements ViewModel<Parameter> {
    @Delegate
    Parameter object;

    public Optional<String> getDefault() {
        return Optional.ofNullable(object.getAnnotation(Default.class))
                .map(Default::value);
    }

    public String getDefault(String fallback){
        return getDefault().orElse(fallback);
    }

    public String getTypeName() {
        return this.getType().getSimpleName();
    }

    public String getDescription() {
        return String.format("%s: %s", this.getName(), this.getTypeName());
    }
}
