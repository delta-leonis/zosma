package io.leonis.game;

import java.util.Set;

/**
 * The Interface Agent.
 *
 * This interface describes the functionality of an object which can be issued an instruction.
 *
 * @author Rimon Oz
 */
public interface Agent {

  /**
   * @return Unique identifier of an agent
   */
  int getId();

  interface SetSupplier<A extends Agent> {
    Set<A> getAgents();
  }
}
