package org.ssh.torch.view.component.form.fields;

import com.googlecode.lanterna.gui2.TextBox;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.ssh.torch.view.component.form.FormElement;

/**
 * The type Number field.
 *
 * @param <N> the type parameter
 */
public abstract class NumberField<N extends Number> extends TextBox implements FormElement<N> {

  private Function<String, N> parser;

  /**
   * Instantiates a new Number field.
   *
   * @param parser the parser
   */
  public NumberField(final Function<String, N> parser) {
    this("", parser);
  }

  /**
   * Instantiates a new Number field.
   *
   * @param initialContent the initial content
   * @param parser         the parser
   */
  public NumberField(final String initialContent, final Function<String, N> parser) {
    this(initialContent, parser, "[-+]?[0-9]*");
  }

  /**
   * Instantiates a new Number field.
   *
   * @param initialContent the initial content
   * @param parser         the parser
   * @param regex          the regex
   */
  public NumberField(final String initialContent, final Function<String, N> parser, final String regex) {
    super(initialContent);
    this.parser = parser;
    this.setValidationPattern(Pattern.compile(regex));
  }

  /**
   * Instantiates a new Number field.
   *
   * @param initialContent the initial content
   * @param parser         the parser
   * @param regex          the regex
   */
  public NumberField(final N initialContent, final Function<String, N> parser, final String regex) {
    this(initialContent.toString(), parser, regex);
  }

  @Override
  public N getValue() {
    return this.getParser().apply(this.getText());
  }

  @Override
  public NumberField<N> setValue(final N value) {
    this.setText(value.toString());
    return this;
  }

  @Override
  public Function<String, N> getParser() {
    return parser;
  }
}