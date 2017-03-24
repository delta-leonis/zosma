package org.ssh.torch.view.component.form.fields;

/**
 * Created by jeroen.dejong on 19/02/2017.
 */
public class IntegerField extends NumberField<Integer> {
    public IntegerField(){
        super(Integer::valueOf);
    }
}