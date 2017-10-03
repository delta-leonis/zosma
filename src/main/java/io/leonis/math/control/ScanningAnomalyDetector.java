package io.leonis.math.control;

import java.util.function.BiPredicate;

/**
 * The Interface ScanningAnomalyDetector.
 *
 * This interface describes the functionality of a scanning anomaly detector, ie. a function which
 * can determine whether a specific input is considered to be an anomaly or not using a state
 * representation of the input space which is updated with every input.
 *
 * @author Rimon Oz
 */
public interface ScanningAnomalyDetector<S, I> extends BiPredicate<S, I> {
}
