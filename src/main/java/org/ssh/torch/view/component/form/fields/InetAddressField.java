package org.ssh.torch.view.component.form.fields;

import com.googlecode.lanterna.gui2.TextBox;
import org.ssh.math.function.LambdaExceptions;
import org.ssh.torch.view.component.form.FormElement;

import java.net.InetAddress;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Created by jeroen.dejong on 19/02/2017.
 */
public class InetAddressField extends TextBox implements FormElement<InetAddress> {
    private static final String IPADDRESS_PATTERN =
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

    public InetAddressField() {
        super();
        this.setValidationPattern(Pattern.compile(IPADDRESS_PATTERN));
    }

    @Override
    public Function<String, InetAddress> getParser() {
        return LambdaExceptions.rethrowFunction(InetAddress::getByName);
    }

    @Override
    public InetAddress getValue() {
        return getParser().apply(this.getText());
    }

    @Override
    public InetAddressField setValue(InetAddress value) {
        this.setText(value.getHostAddress());
        return this;
    }
}
