package org.ssh.benchmarks;

import org.ssh.benchmarks.report.Report;

/**
 * The Interface ReportReader.
 *
 * @author Rimon Oz
 */
public interface ReportReader {

  /**
   * Reads a report from file.
   *
   * @param fileName The location of the file.
   * @return The parsed {@link Report}.
   */
  Report readReport(String fileName);
}
