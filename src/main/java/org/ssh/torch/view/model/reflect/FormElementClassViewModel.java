package org.ssh.torch.view.model.reflect;

import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.view.component.form.FormElement;
import org.ssh.torch.view.model.ViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * Created by jeroen.dejong on 19/02/2017.
 */
@Value
public class FormElementClassViewModel implements ViewModel<Class<? extends FormElement>> {
    @Delegate
    private final Class object;

    public Class<?> getElementType(){
        // Catch the first superclass with an generic
        Type type = object.getGenericSuperclass();
        // if the superclass is equal, the superclass didn't have an generic
        if(type == object.getSuperclass())
            // thus grab the first Generic interface (FormElement)
            // Note: In the future we may need to search through the list to find FormElement
            type = object.getGenericInterfaces()[0];
        // and grab the first actual type argument
        return (Class<?>) ((ParameterizedType)type).getActualTypeArguments()[0];
    }

    public boolean hasEmptyConstructor(){
        return Stream.of(object.getConstructors())
                .anyMatch(constructor -> constructor.getParameterCount() == 0);
    }
}
