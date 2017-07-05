package org.ssh.math.control;

import java.util.Collections;
import java.util.stream.Collectors;
import lombok.Value;
import org.ssh.math.statistic.*;

/**
 * The Class HBOSAnomalyDetector.
 *
 * This class describes an {@link AnomalyDetector} which uses the <a
 * href="http://www.dfki.de/KI2012/PosterDemoTrack/ki2012pd13.pdf">HBOS algorithm</a></a>
 *
 * @author Rimon Oz
 */
@Value
public class HBOSAnomalyDetector implements ScanningAnomalyDetector<Histogram, Number> {
  private final double weight;
  private final double maximumScore;

  /**
   * Constructs a new HBOS {@link AnomalyDetector} using the supplied lower bound, bin width, and
   * measurement weight.
   *
   * @param lowerBound   The lower bound of the histogram representing the measurement space.
   * @param binWidth     The bin width of the histogram representing the measurement space.
   * @param weight       The weight added to the histogram bin corresponding to new measurement
   *                     values when they arrive.
   * @param maximumScore The maximum HBOS score allowed.
   */
  public HBOSAnomalyDetector(final double lowerBound, final double binWidth, final double weight,
      final double maximumScore) {
    this(new SimpleHistogram(lowerBound, binWidth, Collections.emptyList()), weight, maximumScore);
  }

  /**
   * Constructs a new HBOS {@link AnomalyDetector} using the supplied pre-computed histogram and
   * measurement weight.
   *
   * @param inputSpaceRepresentation The measurement space representation as a {@link Histogram}.
   * @param weight                   The weight added to the histogram bin corresponding to new
   *                                 measurement values when they arrive.
   * @param maximumScore             The maximum HBOS score allowed.
   */
  public HBOSAnomalyDetector(final Histogram inputSpaceRepresentation, final double weight,
      final double maximumScore) {
    this.weight = weight;
    this.maximumScore = maximumScore;
  }

  @Override
  public boolean test(final Histogram inputSpaceRepresentation, final Number input) {
    return this.computeHBOS(inputSpaceRepresentation, input).doubleValue() > this.getMaximumScore();
  }

  /**
   * Computes the Histogram-Based Outlier Score (HBOS) of the supplied value, according to the
   * supplied measurement space.
   *
   * @param inputSpaceRepresentation The measurement space representation as a {@link Histogram}.
   * @param value                    The value to compute the HBOS of.
   */
  protected Number computeHBOS(final Histogram inputSpaceRepresentation, final Number value) {
    final Histogram normalizedRepresentation = this.normalizeVertically(inputSpaceRepresentation);
    final Double binHeight = normalizedRepresentation.getBins()
        .get((int) Math.floor((value.doubleValue() - normalizedRepresentation.getLowerBound())
            / normalizedRepresentation.getBinWidth()));
    return Math.log(1 / binHeight);
  }


  /**
   * @return The (vertically) normalized representation of this histogram, such that the largest
   * bin(s) receive(s) a normalized weight of 1.
   */
  protected Histogram normalizeVertically(final Histogram toNormalize) {
    return toNormalize.getBins().stream()
        .max(Double::compare)
        .<Histogram>map(maximum -> new SimpleHistogram(
            toNormalize.getLowerBound(),
            toNormalize.getBinWidth(),
            toNormalize.getBins().stream()
                .map(value -> value / maximum)
                .collect(Collectors.toList())))
        .orElse(toNormalize);
  }
}
