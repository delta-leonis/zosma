package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import java.awt.Color;
import lombok.Getter;

/**
 * Defines a thin line for a graph. A {@link Gradient} can be used to make it look prettier.
 * The graph can end up looking like this: <br/>
 * <pre>
 * :!
 * || .|
 * |||||
 * </pre>
 *
 * @author Thomas Hakkers
 */
public class ThinLine implements LineType {

  @Getter
  private final Gradient gradient;

  private static final char POSITIVE_CHARACTERS[] = {'¡', ':', '.'};
  private static final char NEGATIVE_CHARACTERS[] = {'!', ':', '\''};
  private static final char FULL_CHARACTER = '|';

  public ThinLine() {
    this(new Gradient(Color.BLACK, Color.BLACK));
  }

  public ThinLine(final Gradient gradient) {
    this.gradient = gradient;
  }

  @Override
  public TextCharacter getCharacter(final double min, final double max, final double value,
      final boolean negative) {
    final TextColor color = this.gradient.getColor(min, max, value);
    final int shape = (int) (3 * (value % 1));

    return new TextCharacter(shape == 0 ? FULL_CHARACTER :
        (negative ? NEGATIVE_CHARACTERS[shape] :
            POSITIVE_CHARACTERS[shape]), color, this.getBackgroundColor(), SGR.BOLD);
  }

  @Override
  public TextCharacter toXAxis(final TextCharacter original) {
    return new TextCharacter('+', original.getForegroundColor(), ANSI.WHITE, SGR.BOLD);
  }
}
