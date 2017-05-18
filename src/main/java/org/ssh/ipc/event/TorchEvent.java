package org.ssh.ipc.event;

import org.ssh.ipc.Event;

/**
 * The Class TorchEvent.
 *
 * This class describes an event within Torch.
 *
 * @param <S> The type of source.
 * @author Thomas Hakkers
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface TorchEvent<S> extends Event<S> {

  /**
   * @return true if {@link #getAction()} equals {@link Action#RESIZED}
   */
  default boolean isResized() {
    return this.getAction().equals(Action.RESIZED);
  }

  /**
   * @return The {@link Action} which triggered this action
   */
  Action getAction();

  /**
   * @return true if {@link #getAction()} equals {@link Action#SWITCHED}
   */
  default boolean isSwitched() {
    return this.getAction().equals(Action.SWITCHED);
  }

  /**
   * @return true if {@link #getAction()} equals {@link Action#CREATED}
   */
  default boolean isCreated() {
    return this.getAction().equals(Action.CREATED);
  }

  /**
   * @return true if {@link #getAction()} equals {@link Action#REMOVED}
   */
  default boolean isRemoved() {
    return this.getAction().equals(Action.REMOVED);
  }

  /**
   * @return true if {@link #getAction()} equals {@link Action#CLOSED}
   */
  default boolean isClosed() {
    return this.getAction().equals(Action.CLOSED);
  }

  /**
   * The Enum Action.
   *
   * This enumeration enumerates the different types of event actions.
   *
   * @author Jeroen de Jong
   */
  public enum Action {
    /**
     * Resize action.
     */
    RESIZED,
    /**
     * Switch action.
     */
    SWITCHED,
    /**
     * Created action.
     */
    CREATED,
    /**
     * Removed action.
     */
    REMOVED,
    /**
     * Closed action.
     */
    CLOSED
  }
}
