package org.ssh.torch.view.component.form.fields;

/**
 * Created by jeroen.dejong on 19/02/2017.
 */
public class DoubleField extends DecimalField<Double> {
    public DoubleField(){
        super(Double::valueOf);
    }
}
