package org.ssh.torch.view.component.form;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.ssh.torch.view.model.reflect.FormElementClassViewModel;

import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class SimpleFormBuilder implements FormBuilder {
    private Map<Class<?>, Class<? extends FormElement>> supportedTypes;
    private Map<String, FormElement<?>> elements;
    private Map<String, Consumer<List<FormElement<?>>>> buttons;

    public SimpleFormBuilder(){
        buttons  = new LinkedHashMap<>();
        elements = new LinkedHashMap<>();
        supportedTypes = new Reflections(this.getClass().getPackage() + ".fields")
            .getSubTypesOf(FormElement.class).stream()
            .filter(aClass -> !Modifier.isAbstract(aClass.getModifiers()))
            .map(FormElementClassViewModel::new)
            .filter(FormElementClassViewModel::hasEmptyConstructor)
            .collect(Collectors.toMap(
                    FormElementClassViewModel::getElementType,
                    FormElementClassViewModel::getObject
            ));
    }

    @Override
    public boolean isSupported(Class<?> type) {
        return this.supportedTypes.containsKey(type);
    }

    @Override
    public <T> SimpleFormBuilder addFormElement(String label, FormElement<T> element) {
        this.elements.put(label, element);
        return this;
    }

    @Override
    public Form build() {
        return Form.of(this.elements, this.buttons);
    }

    @Override
    public SimpleFormBuilder addButton(String label, Consumer<List<FormElement<?>>> onSelect) {
        this.buttons.put(label, onSelect);
        return this;
    }

    @Override
    public <T> FormElement<T> createFormElement(Class<T> type) throws NoSuchFormElementException {
        try {
            return (FormElement<T>) this.supportedTypes.get(type).newInstance();
        } catch ( IllegalAccessException | InstantiationException  e) {
            throw new NoSuchFormElementException(e);
        }
    }
}
