package org.ssh.game;

/**
 * The Interface Agent.
 * <p>
 * This interface describes the functionality of an object which can be
 * issued an instruction. The interpretation of the instruction
 * is determined by a {@link Command}.
 *
 * @author Rimon Oz
 */
@FunctionalInterface
public interface Agent {

  /**
   * @return Unique identifier of an agent
   */
  int getId();
}
