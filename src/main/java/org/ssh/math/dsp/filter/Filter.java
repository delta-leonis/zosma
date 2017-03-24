package org.ssh.math.dsp.filter;

import org.ssh.math.function.Scanner;

/**
 * The Interface Filter.
 * <p>
 * This interface represents a mathematical filter which operates on objects of the supplied
 * generic type.
 *
 * @param <T> The type of mathematical object on which the filter operates.
 * @author Rimon Oz
 */
public interface Filter<T> extends Scanner<T, T> {

}
