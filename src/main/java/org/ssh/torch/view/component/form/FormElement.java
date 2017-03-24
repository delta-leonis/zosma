package org.ssh.torch.view.component.form;

import com.googlecode.lanterna.gui2.Interactable;

import java.util.function.Function;

/**
 * Created by jeroen.dejong on 16/02/2017.
 */
public interface FormElement<T> extends Interactable {
    Function<String, T> getParser();

    T getValue();

    FormElement setValue(T value);

    default FormElement setValue(String value) {
        return setValue(getParser().apply(value));
    }
}
