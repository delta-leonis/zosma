package org.ssh.torch.view.component.form.fields;

/**
 * The Class DoubleField.
 *
 * @author Jeroen de Jong
 */
public class DoubleField extends DecimalField<Double> {
  /**
   * Instantiates a new Double field.
   */
  public DoubleField() {
    super(Double::valueOf);
  }
}
