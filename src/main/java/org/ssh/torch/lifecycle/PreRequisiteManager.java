package org.ssh.torch.lifecycle;

import java.util.List;
import org.reactivestreams.Publisher;

/**
 * The Interface PreRequisiteManager.
 *
 * This interface describes the functionality of a pre-requisite manager, that is a manager
 * which {@link Publisher publishes} pre-requisites.
 *
 * @author Jeroen de Jong
 */
public interface PreRequisiteManager {

  /**
   * Gets pre requisites.
   *
   * @return the pre requisites
   */
  List<Prerequisite> getPreRequisites();
}
