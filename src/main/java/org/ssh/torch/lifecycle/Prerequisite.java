package org.ssh.torch.lifecycle;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

/**
 * The Class Prerequisite.
 *
 * This class describes a pre-requisite, that is a thread which must be called
 * and completed before continuing.
 *
 * @author Jeroen De Jong
 */
@AllArgsConstructor
public class Prerequisite implements Runnable {

  /**
   * The name of the pre-requisite.
   */
  private final String name;
  /**
   * The runnable which is executed.
   */
  @Delegate
  private final Runnable runnable;

  @Override
  public String toString() {
    return name;
  }
}
