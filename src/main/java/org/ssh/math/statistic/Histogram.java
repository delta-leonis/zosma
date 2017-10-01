package org.ssh.math.statistic;

import java.util.*;
import java.util.stream.*;

/**
 * The Interface Histogram.
 *
 * @author Rimon Oz
 */
public interface Histogram {
  double getLowerBound();
  double getBinWidth();
  List<Double> getBins();
  Histogram add(double toAdd, double weight);

  /**
   * @param binIndex The index of the bin to compute the average value of.
   * @return The average value of the bin at the specified index.
   */
  default double computeAverageValue(final int binIndex) {
    return (((binIndex + 1) * this.getBinWidth()) - this.getBinWidth() / 2) + this.getLowerBound();
  }
}
