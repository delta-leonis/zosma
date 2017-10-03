package io.leonis.math.statistic;

import java.util.List;

/**
 * The Interface Histogram.
 *
 * @author Rimon Oz
 */
public interface Histogram {
  /**
   * @return A list of values indicating the left corner of the bin.
   */
  List<Double> getBins();

  /**
   * @param toAdd  The value to insert into the histogram.
   * @param weight The weight of the value to insert.
   * @return A new histogram with the added value.
   */
  Histogram add(double toAdd, double weight);

  /**
   * @param binIndex The index of the bin to compute the average value of.
   * @return The average value of the bin at the specified index.
   */
  default double computeAverageValue(final int binIndex) {
    return (((binIndex + 1) * this.getBinWidth()) - this.getBinWidth() / 2) + this.getLowerBound();
  }

  /**
   * @return The width of the bin.
   */
  double getBinWidth();

  /**
   * @return The lowest value in the histogram.
   */
  double getLowerBound();
}
