package io.leonis.math.control.anomaly;

import com.google.common.collect.Range;
import io.leonis.math.control.AnomalyDetector;
import java.util.Set;
import lombok.Value;

/**
 * The Interface RangeAnomalyDetector.
 *
 * This interface describes the functionality of an anomaly detector, ie. a function which can
 * determine whether a specific input is considered to be an anomaly or not, which considers inputs
 * to be anomalies whenever they fall within one or more specified ranges.
 *
 * @author Rimon Oz
 */
@Value
public class RangeAnomalyDetector<C extends Comparable> implements AnomalyDetector<C> {
  /**
   * The ranges within which an input is considered to be an anomaly.
   */
  private final Set<Range<C>> ranges;

  @Override
  public boolean test(final C comparable) {
    return this.ranges.stream()
        .anyMatch(entry -> entry.contains(comparable));
  }
}
