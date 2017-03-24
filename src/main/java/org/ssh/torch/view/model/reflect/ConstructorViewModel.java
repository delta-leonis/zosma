package org.ssh.torch.view.model.reflect;

import lombok.Value;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.ssh.torch.view.component.form.FormElement;
import org.ssh.torch.view.component.form.SimpleFormBuilder;
import org.ssh.torch.view.model.ViewModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jeroen.dejong
 * @since 12/01/2017.
 */
@Value
@Slf4j
public class ConstructorViewModel implements ViewModel<Constructor> {
    @Delegate
    private Constructor object;

    public List<ParameterViewModel> getParameterPresenters(){
        return Stream.of(object.getParameters())
                .map(ParameterViewModel::new)
                .collect(Collectors.toList());
    }

    public boolean allParametersSupported() {
        SimpleFormBuilder formBuilder = new SimpleFormBuilder();
        return getParameterPresenters().stream()
                .map(ParameterViewModel::getType)
                .allMatch(formBuilder::isSupported);
    }

    public boolean allParameterNamePresent() {
        return this.getParameterPresenters()
                .stream()
                .allMatch(ParameterViewModel::isNamePresent);
    }

    public <O> O create(List<FormElement<?>> data) {
        try {
            return (O) this.newInstance(data.stream().map(FormElement::getValue).toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.debug("Could not create " + this.getDeclaringClass().getSimpleName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return String.format("%s(%s)", this.getDeclaringClass().getSimpleName(), this.getParameterDescription());
    }

    private String getParameterDescription(){
        return getParameterPresenters().stream()
                .map(ParameterViewModel::getDescription)
                .reduce((list, param) -> list + ", " + param).orElse("");
    }
}
