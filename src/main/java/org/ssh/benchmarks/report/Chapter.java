package org.ssh.benchmarks.report;

import java.util.List;

/**
 * The Interface Chapter.
 * <p>
 * This interface represents a chapter within a {@link Report}.
 *
 * @author Rimon Oz
 */
public interface Chapter {
    /**
     * Returns the title of the chapter.
     *
     * @return The title of the chapter.
     */
    String getTitle();

    /**
     * Gets the ordered list of sections in this chapter.
     *
     * @return The ordered list of sections in this chapter.
     */
    List<Section> getSections();
}