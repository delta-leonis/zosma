package org.ssh.torch.view.component.form;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by jeroen.dejong on 23/02/2017.
 */
public interface FormBuilder {
    default boolean areSupported(Class<?>... types) {
        return Stream.of(types).allMatch(this::isSupported);
    }

    boolean isSupported(Class<?> type);

    default <T> FormBuilder addField(String label, Class<T> type) {
        return this.addField(label, type, "");
    }

    default <T> FormBuilder addField(String label, T initialContent) {
        return this.addField(label, initialContent.getClass(), initialContent.toString());
    }

    default <T> FormBuilder addField(String label, Class<T> type, String initialContent) {
        FormElement<T> element = createFormElement(type);
        element.setValue(initialContent);
        return addFormElement(label, element);
    }

    SimpleFormBuilder addButton(String label, Consumer<List<FormElement<?>>> onSelect);

    Form build();

    class NoSuchFormElementException extends RuntimeException {
        NoSuchFormElementException(Throwable cause) {
            super(cause);
        }
    }

    // protected
    <T> SimpleFormBuilder addFormElement(String label, FormElement<T> element);

    // private
    <T> FormElement<T> createFormElement(Class<T> type) throws NoSuchFormElementException;
}
