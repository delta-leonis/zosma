package org.ssh.torch.view.component.form.fields;

/**
 * The Class IntegerField.
 *
 * @author Jeroen de Jong
 */
public class IntegerField extends NumberField<Integer> {

  /**
   * Instantiates a new Integer field.
   */
  public IntegerField() {
    super(Integer::valueOf);
  }
}