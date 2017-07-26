package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;
import lombok.Setter;

/**
 * The {@link Component} XYGraph can be overridden to create a {@link Component} that displays a
 * graph based on the data supplied through {@link #getMeasurements()}}
 *
 * @param <G> the type parameter
 * @author Jeroen de Jong
 * @author Thomas Hakkers
 */
public abstract class XYGraph<G extends XYGraph> extends AbstractComponent<G> {

  /**
   * The Max y.
   */
  @Setter
  private Double maxY = 0d;
  /**
   * The Min y.
   */
  @Setter
  private Double minY = 0d;
  /**
   * The Y label.
   */
  @Setter
  private String yLabel = "time";

  @Setter
  private LineType lineType;

  @Override
  protected ComponentRenderer<G> createDefaultRenderer() {
    return new DefaultXYGraphRender();
  }

  private class DefaultXYGraphRender implements ComponentRenderer<G> {

    @Override
    public TerminalSize getPreferredSize(final XYGraph component) {
      return component.getPreferredSize();
    }

    @Override
    public void drawComponent(final TextGUIGraphics graphics, final G component) {
      // Draw the actual graph
      graphics.drawImage(new TerminalPosition(5, 0), new GraphImage(XYGraph.this.getMeasurements(),
          component.getPreferredSize().getColumns() - 6,
          component.getPreferredSize().getRows() - 2, minY, maxY, lineType));

      // Draw the axes
      IntStream.range(0, graphics.getSize().getRows())
          .forEach(y -> graphics.setCharacter(4, y,
              new TextCharacter(y % 2 == 0 ? '+' : '|', ANSI.BLACK, ANSI.WHITE)));

      final int xAxis = Math.max(0,
          Math.min((int) (((0 - minY) / (maxY - minY)) * component.getPreferredSize().getRows()),
              component.getPreferredSize().getRows())) - 1;
      IntStream.range(2, graphics.getSize().getColumns() / 4)
          .forEach(x -> graphics.setCharacter(x * 4, xAxis,
              lineType.toXAxis(graphics.getCharacter(x * 4, xAxis))));

      // Draw the maximum and minimum value
      graphics.putString(0, 0, maxY == null ? "NaN" : new PrefixPresenter(maxY.longValue()).format());
      graphics.putString(0, component.getPreferredSize().getRows() - 2,
          minY == null ? "NaN" : new PrefixPresenter(minY.longValue()).format());
    }
  }

  protected abstract List<Map.Entry<Integer, Double>> getMeasurements();
}
