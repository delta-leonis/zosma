package org.ssh.math.control;

import java.util.function.*;

/**
 * The Interface AnomalyDetector.
 *
 * This interface describes the functionality of an anomaly detector, ie. a function which can
 * determine whether a specific input is considered to be an anomaly or not.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface AnomalyDetector<I> extends Predicate<I> {
}
