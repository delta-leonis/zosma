package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * Defines a thick line for a graph. A {@link Gradient} can be used to make it look prettier.
 * The graph can end up looking like this: <br/>
 * <pre>
 * ▄▄
 * ██ ▄█
 * █████
 * </pre>
 *
 * @author Thomas Hakkers
 */
@Value
@AllArgsConstructor
public class ThickLine implements LineType {

  private static final char POSITIVE_CHARACTER = '▄';
  private static final char NEGATIVE_CHARACTER = '▀';
  private static final char FULL_CHARACTER = '█';

  private final Gradient gradient;

  public ThickLine() {
    this(new Gradient(Color.BLACK, Color.BLACK));
  }

  @Override
  public TextCharacter getCharacter(final double min, final double max, final double value,
      final boolean negative) {
    final TextColor color = this.gradient.getColor(min, max, value);
    final double shape = value % 1;

    if(shape == 0 || shape > 0.6)
      return new TextCharacter(FULL_CHARACTER, color, this.getBackgroundColor());
    else
      return new TextCharacter(negative ? NEGATIVE_CHARACTER : POSITIVE_CHARACTER, color, this.getBackgroundColor());

  }

  @Override
  public TextCharacter toXAxis(final TextCharacter original) {
    return new TextCharacter('+', ANSI.BLACK, original.getForegroundColor());
  }
}
