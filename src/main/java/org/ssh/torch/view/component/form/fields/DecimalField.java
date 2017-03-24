package org.ssh.torch.view.component.form.fields;

import java.util.function.Function;

/**
 * Created by jeroen.dejong on 19/02/2017.
 */
public abstract class DecimalField<N extends Number> extends NumberField<N> {
    public DecimalField(Function<String, N> parser) {
        this("", parser);
    }

    public DecimalField(N initialContent, Function<String, N> parser) {
        this(initialContent.toString(), parser);
    }

    public DecimalField(String initialContent, Function<String, N> parser) {
        super(initialContent, parser, "[-+]?[0-9]*\\.?[0-9]*");
    }
}