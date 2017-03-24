package org.ssh.torch.view.component.form.fields;

/**
 * Created by jeroen.dejong on 19/02/2017.
 */
public class FloatField extends DecimalField<Float> {
    public FloatField(){
        super(Float::valueOf);
    }
}