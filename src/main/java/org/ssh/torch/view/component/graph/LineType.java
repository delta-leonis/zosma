package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;

/**
 * Describes how a line should be displayed on a graph.
 *
 * @author Thomas Hakkers
 */
public interface LineType {

  /**
   * Returns the {@link TextCharacter} for the given values.
   *
   * @param min The minimum value in the graph.
   * @param max The maximum value in the graph.
   * @param value The value on the graph within min and max.
   * @param negative Whether the value should be depicted as a negative value. Which means the ascii
   * art needs to be changed.
   * @return The {@link TextCharacter} that belongs to the given value.
   */
  TextCharacter getCharacter(final double min, final double max, final double value, final boolean negative);

  default TextColor getBackgroundColor() {
    return ANSI.WHITE;
  }

  /**
   * Turns the given {@link TextCharacter} into a character used as an x axis
   * @param original The original {@link TextCharacter}
   * @return The {@link TextCharacter} to be used as an x axis
   */
  TextCharacter toXAxis(final TextCharacter original);
}
