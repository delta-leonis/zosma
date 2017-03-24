package org.ssh.torch.view.component.form.fields;

import com.googlecode.lanterna.gui2.TextBox;
import org.ssh.torch.view.component.form.FormElement;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Created by jeroen.dejong on 16/02/2017.
 */
public class TextField extends TextBox implements FormElement<String> {
    public TextField() {
        this("", ".*");
    }

    public TextField(String initialContent, String regex) {
        super(initialContent);
        this.setValidationPattern(Pattern.compile(regex));
    }

    @Override
    public Function<String, String> getParser() {
        return String::new;
    }

    @Override
    public String getValue() {
        return this.getText();
    }

    @Override
    public TextField setValue(String value) {
        this.setText(value);
        return this;
    }
}
