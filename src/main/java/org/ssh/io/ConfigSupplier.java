package org.ssh.io;

import com.typesafe.config.Config;

/**
 * The Interface ConfigSupplier
 *
 * This interface describes the functionality of an object which exposes a {@link Config}.
 *
 * @author Jeroen de Jong
 */
public interface ConfigSupplier {

  /**
   * @return The {@link Config}.
   */
  Config getConfig();
}