package org.ssh.torch.view.component.form.fields;

/**
 * The Class FloatField.
 *
 * @author Jeroen de Jong
 */
public class FloatField extends DecimalField<Float> {

  /**
   * Instantiates a new Float field.
   */
  public FloatField() {
    super(Float::valueOf);
  }
}