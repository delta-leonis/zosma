package org.ssh.torch.view.component.form.fields;

import com.googlecode.lanterna.gui2.TextBox;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.ssh.torch.view.component.form.FormElement;

/**
 * The Class TextField.
 *
 * @author Jeroen de Jong
 */
public class TextField extends TextBox implements FormElement<String> {

  /**
   * Instantiates a new Text field.
   */
  public TextField() {
    this("", ".*");
  }

  /**
   * Instantiates a new Text field.
   *
   * @param initialContent the initial content
   * @param regex          the regex
   */
  public TextField(String initialContent, String regex) {
    super(initialContent);
    this.setValidationPattern(Pattern.compile(regex));
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

  @Override
  public Function<String, String> getParser() {
    return String::new;
  }
}