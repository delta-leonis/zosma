package org.ssh.ipc.event;

import lombok.Value;
import org.ssh.ipc.Event;
import org.ssh.torch.LifeCycle;
import org.ssh.torch.lifecycle.Prerequisite;

/**
 * The Class LifeCycleEvent.
 *
 * This class describes an event of a {@link LifeCycle}
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
@Value
public class LifeCycleEvent<S, C> implements Event<S> {
  /**
   * The timestamp when this event has been created
   */
  private final long timestamp = System.currentTimeMillis();

  /**
   * The source which triggered the event
   */
  private final S source;

  /**
   * The context in which this event has been triggered
   *
   * For example if the {@link #source} is a {@link Prerequisite} this will
   * be the {@link LifeCycle}-implementation which contained it.
   */
  private final C context;

  /**
   * The state of the {@link LifeCycle}
   */
  private final State state;

  /**
   * Constructs a {@link LifeCycleEvent} based on a {@link LifeCycle}
   *
   * @param source  The source which initiated this event
   * @param context The context in which this event was instantiated
   * @param state   The current state of the {@link #source}
   * @param <S>     The type of source
   * @param <C>     The type of context
   * @return A {@link LifeCycleEvent} describing the state of the provided source within a context.
   */
  public static <S, C extends LifeCycle> LifeCycleEvent<S, C> of(
      final S source,
      final C context,
      final State state
  ) {
    return new LifeCycleEvent<>(source, context, state);
  }

  /**
   * Constructs a {@link LifeCycleEvent} based on the loading of a {@link Prerequisite}
   *
   * @param source       The source which loaded a prerequisite
   * @param prerequisite The prerequisite which was loaded
   * @param <S>          The type of source
   * @param <P>          The type of {@link Prerequisite}
   * @return A {@link LifeCycleEvent} describing the loading of a {@link Prerequisite}.
   */
  public static <S, P extends Prerequisite> LifeCycleEvent<S, P> of(
      final S source,
      final P prerequisite
  ) {
    return new LifeCycleEvent<>(
        source,
        prerequisite,
        State.PREREQUISITE);
  }

  /**
   * A description of the state of the {@link LifeCycle}
   */
  public enum State {
    /**
     * The prerequisite state.
     */
    PREREQUISITE,
    /**
     * The start state.
     */
    START,
    /**
     * The pause state.
     */
    PAUSE,
    /**
     * The stop state.
     */
    STOP,
    /**
     * The reset state.
     */
    RESET
  }
}
