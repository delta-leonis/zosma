package org.ssh.torch.view.component.graph;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.BasicTextImage;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import lombok.NonNull;

/**
 * A {@link BasicTextImage} that represents a graph based on a list of data.
 *
 * @author Thomas Hakkers
 * @since 7-5-17
 */
public final class GraphImage extends BasicTextImage {

  public GraphImage(@NonNull final List<Entry<Integer, Double>> data, final int columns,
      final int rows, final double minY,
      final double maxY, @NonNull final LineType lineType) {
    this(data, columns, rows, minY, maxY, lineType, ANSI.WHITE);
  }

  public GraphImage(@NonNull final List<Entry<Integer, Double>> data, final int columns,
      final int rows, final double minY,
      final double maxY, @NonNull final LineType lineType, final TextColor backgroundColor) {
    super(columns, rows);

    final int start = Math.max(0, data.size() - columns);

    this.setAll(new TextCharacter(' ', backgroundColor, backgroundColor));
    // Keep xAxis within range [0, rows]
    final int xAxis = Math.max(0, Math.min((int) (((0 - minY) / (maxY - minY)) * rows), rows));
    data.stream()
        .skip(start)
        .map(entry -> getCurrentPosition(entry, minY, maxY, rows, start))
        .forEach(entry -> drawColumn(entry, xAxis, rows, lineType));
  }

  private Map.Entry<Integer, Float> getCurrentPosition(final Map.Entry<Integer, Double> entry,
      final double minY, final double maxY, final int rows, final int start) {
    return new SimpleEntry<>(entry.getKey() - start,
        ((float) (entry.getValue() - minY) / (float) (maxY - minY)) * rows);
  }

  private void drawColumn(final Map.Entry<Integer, Float> entry, final int xAxis, final int rows,
      final LineType lineType) {
    if (entry.getValue() > xAxis) {
      IntStream.rangeClosed(xAxis, (int) Math.ceil(entry.getValue()))
          .asDoubleStream()
          .map(index -> index > entry.getValue() ? entry.getValue() : index)
          .forEach(bar -> setCharacterAt(entry.getKey(), rows - (int) Math.ceil(bar),
              lineType.getCharacter(0, rows, bar, false))
          );
    } else if (entry.getValue() < xAxis) {
      IntStream.rangeClosed((int) Math.floor(entry.getValue()), xAxis)
          .asDoubleStream()
          .map(index -> index < entry.getValue() ? entry.getValue() : index)
          .forEach(bar -> setCharacterAt(entry.getKey(), rows - (int) Math.floor(bar),
              lineType.getCharacter(0, rows, bar, true))
          );
    }
  }
}
