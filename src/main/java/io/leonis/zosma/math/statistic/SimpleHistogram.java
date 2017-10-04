package io.leonis.zosma.math.statistic;

import java.util.*;
import java.util.stream.*;
import lombok.Value;

/**
 * The Class SimpleHistogram.
 *
 * This class represents a simple {@link Histogram}.
 *
 * @author Rimon Oz
 */
@Value
public class SimpleHistogram implements Histogram {
  private final double lowerBound;
  private final double binWidth;
  private final List<Double> bins;

  public List<Double> getBins() {
    return Collections.unmodifiableList(this.bins);
  }

  /**
   * @param toAdd  The value to add to the histogram.
   * @param weight The weight to add to the bin corresponding to the supplied value.
   * @return The current histogram with the value's weight added to the corresponding bin.
   */
  public Histogram add(final double toAdd, final double weight) {
    return new SimpleHistogram(
        this.getLowerBound(),
        this.getBinWidth(),
        IntStream.range(0, Math.max(this.computeTargetIndex(toAdd) + 1, this.getBins().size()))
            .mapToObj(currentIndex -> {
              final double targetBinValue =
                  currentIndex < this.getBins().size() ? this.getBins().get(currentIndex) : 0;
              if (currentIndex == this.computeTargetIndex(toAdd)) {
                return targetBinValue + weight;
              }
              return targetBinValue;
            })
            .collect(Collectors.toList()));
  }

  /**
   * @param toAdd The value to compute the target index for.
   * @return The index of the bin in which the value should end.
   */
  protected int computeTargetIndex(final double toAdd) {
    return (int) Math.floor((toAdd - this.getLowerBound()) / this.getBinWidth());
  }
}
