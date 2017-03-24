package org.ssh.benchmarks.report;

/**
 * The Interface Section.
 *
 * This class describes the functionality of a section in a {@link Report}.
 *
 * @param <T> The type of content encompassed by this section.
 * @author Rimon Oz
 */
public interface Section<T> {

  /**
   * Returns the heading of the section.
   *
   * @return The heading of the section as a String.
   */
  String getHeading();

  /**
   * Returns the section content.
   *
   * @return The section content.
   */
  T getSection();
}
