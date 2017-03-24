package org.ssh.torch.view.component.form.fields;

import com.googlecode.lanterna.gui2.TextBox;
import org.ssh.torch.view.component.form.FormElement;

import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class NumberField<N extends Number> extends TextBox implements FormElement<N> {
    private Function<String, N> parser;

    public NumberField(Function<String, N> parser) {
        this("", parser);
    }

    public NumberField(N initialContent, Function<String, N> parser, String regex) {
        this(initialContent.toString(), parser, regex);
    }

    public NumberField(String initialContent, Function<String, N> parser) {
        this(initialContent, parser, "[-+]?[0-9]*");
    }

    public NumberField(String initialContent, Function<String, N> parser, String regex) {
        super(initialContent);
        this.parser = parser;
        this.setValidationPattern(Pattern.compile(regex));
    }

    @Override
    public Function<String, N> getParser() {
        return parser;
    }

    @Override
    public N getValue() {
        return this.getParser().apply(this.getText());
    }

    @Override
    public NumberField<N> setValue(N value) {
        this.setText(value.toString());
        return this;
    }
}