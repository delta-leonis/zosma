package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.RGB;
import java.awt.Color;
import lombok.Value;

/**
 * Describes the gradient to be used by a {@link GraphImage}.
 * @author Thomas Hakkers
 */
@Value
public class Gradient {
  private Color min, max;

  /**
   * Controls what color a value on the graph should have.
   * @param min The minimum value in the graph.
   * @param max The maximum value in the graph.
   * @param value The value on the graph within min and max.
   * @return The {@link TextColor} for the specified value.
   */
  TextColor getColor(final double min, final double max, final double value){
    return applyGradient(this.getMin(), this.getMax(), (Math.floor(value) - min) / (max - min));
  }

  private static RGB applyGradient(final Color minColor, final Color maxColor, final double gradientValue) {
    return new RGB(
        Gradient.calculateGradient(minColor.getRed(), maxColor.getRed(), gradientValue),
        Gradient.calculateGradient(minColor.getGreen(), maxColor.getGreen(), gradientValue),
        Gradient.calculateGradient(minColor.getBlue(), maxColor.getBlue(), gradientValue)
    );
  }

  private static int calculateGradient(final int minValue, final int maxValue, final double gradient) {
    return (int)(minValue * gradient + maxValue * (gradient -1));
  }
}
