package org.ssh.benchmarks.report;

import java.util.List;

/**
 * The Interface Report.
 *
 * This interface represents a report.
 *
 * @author Rimon Oz
 */
public interface Report {

  /**
   * Returns the title of the report.
   *
   * @return The title of the report.
   */
  String getTitle();

  /**
   * Returns the ordered list of chapters in this report.
   *
   * @return The ordered list of chapters in this report.
   */
  List<Chapter> getChapters();
}
