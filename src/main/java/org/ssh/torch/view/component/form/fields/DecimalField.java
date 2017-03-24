package org.ssh.torch.view.component.form.fields;

import java.util.function.Function;

/**
 * The Class DecimalField.
 *
 * @param <N> the type parameter
 * @author Jeroen de Jong
 */
public abstract class DecimalField<N extends Number> extends NumberField<N> {

  /**
   * Instantiates a new Decimal field.
   *
   * @param parser the parser
   */
  public DecimalField(Function<String, N> parser) {
    this("", parser);
  }

  /**
   * Instantiates a new Decimal field.
   *
   * @param initialContent the initial content
   * @param parser         the parser
   */
  public DecimalField(String initialContent, Function<String, N> parser) {
    super(initialContent, parser, "[-+]?[0-9]*\\.?[0-9]*");
  }

  /**
   * Instantiates a new Decimal field.
   *
   * @param initialContent the initial content
   * @param parser         the parser
   */
  public DecimalField(N initialContent, Function<String, N> parser) {
    this(initialContent.toString(), parser);
  }
}