package org.ssh.math.tree;

import com.google.common.collect.Table;
import java.util.function.BiConsumer;

/**
 * The Interface FiniteStateMachine.
 * <p>
 * This interface describes the functionality of a finite state machine.
 *
 * @param <S> the type parameter
 * @author Rimon Oz
 */
public interface FiniteStateMachine<S extends FiniteStateMachine.State> extends Graph<String, S> {

  /**
   * Transitions from one state to the next, if the state transition is supported by
   * this finite state machine.
   *
   * @param from The state to transition from.
   * @param to   The state to transition to.
   * @return True if successful, false otherwise.
   */
  default boolean transition(S from, S to) {
    if (this.getStateTransitionTable().contains(from, to)) {
      this.getStateTransitionTable().get(from, to).accept(from, to);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the state transition table.
   *
   * @return The state transition table.
   */
  Table<S, S, BiConsumer<S, S>> getStateTransitionTable();

  /**
   * The Interface State.
   * <p>
   * This interface describes the functionality of a state in the state machine.
   */
  interface State extends Graph.Node<String> {

  }
}
