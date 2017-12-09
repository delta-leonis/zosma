package io.leonis.zosma.game.engine;

import java.util.function.*;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;

/**
 * The Class Zosma.
 *
 * This class represents a runnable game agent, ie. an object which reacts to game data and manages
 * its own runtime.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public class Zosma implements Runnable {
  /** The {@link Runnable} zosma instance. */
  private final Runnable zosma;

  /**
   * Constructs a new game agent which contains adapters for dealing with external types.
   *
   * @param inputPublisher   A {@link Publisher} of game data from an external library.
   * @param inputAdapter     A {@link Function} which transforms the type to something the game
   *                         agent can interpret.
   * @param ai               The game agent, which transforms game data to actionable commands.
   * @param outputAdapter    A {@link Function} which transforms actionable commands to data which
   *                         can be interpreted by an external library.
   * @param outputSubscriber A {@link Subscriber} for sinking the data.
   * @param <I>              The type of input accepted by the system.
   * @param <O>              The type of output produced by the system.
   * @param <J>              The type of input accepted by the game agent.
   * @param <Q>              The type of output produced by the game agent.
   */
  public <I, J, Q, O> Zosma(
      final Publisher<I> inputPublisher,
      final Function<Publisher<I>, Publisher<J>> inputAdapter,
      final Function<Publisher<J>, Publisher<Q>> ai,
      final Function<Publisher<Q>, Publisher<O>> outputAdapter,
      final Subscriber<O> outputSubscriber
  ) {
    this(inputPublisher, inputAdapter.andThen(ai).andThen(outputAdapter), outputSubscriber);
  }

  /**
   * Constructs a new game agent.
   *
   * @param inputPublisher   A {@link Publisher} of game data.
   * @param ai               The game agent, which transforms game data to actionable commands.
   * @param outputSubscriber A {@link Consumer}
   * @param <I>              The type of input accepted by the system.
   * @param <O>              The type of output produced by the system.
   */
  public <I, O> Zosma(
      final Publisher<I> inputPublisher,
      final Function<Publisher<I>, Publisher<O>> ai,
      final Subscriber<O> outputSubscriber
  ) {
    this.zosma = () -> Flux.from(inputPublisher)
        .transform(ai)
        .subscribe(outputSubscriber);
  }

  /**
   * Constructs a new game agent which contains adapters for dealing with external types.
   *
   * @param inputPublisher   A {@link Publisher} of game data from an external library.
   * @param inputAdapter     A {@link Function} which transforms the type to something the game
   *                         agent can interpret.
   * @param ai               The game agent, which transforms game data to actionable commands.
   * @param outputAdapter    A {@link Function} which transforms actionable commands to data which
   *                         can be interpreted by an external library.
   * @param outputConsumer   A {@link Consumer} for sinking the data.
   * @param <I>              The type of input accepted by the system.
   * @param <O>              The type of output produced by the system.
   * @param <J>              The type of input accepted by the game agent.
   * @param <Q>              The type of output produced by the game agent.
   */
  public <I, J, Q, O> Zosma(
      final Publisher<I> inputPublisher,
      final Function<Publisher<I>, Publisher<J>> inputAdapter,
      final Function<Publisher<J>, Publisher<Q>> ai,
      final Function<Publisher<Q>, Publisher<O>> outputAdapter,
      final Consumer<O> outputConsumer
  ) {
    this(inputPublisher, inputAdapter.andThen(ai).andThen(outputAdapter), outputConsumer);
  }

  /**
   * Constructs a new game agent.
   *
   * @param inputPublisher   A {@link Publisher} of game data.
   * @param ai               The game agent, which transforms game data to actionable commands.
   * @param outputConsumer   A {@link Consumer} for sinking the data.
   * @param <I>              The type of input accepted by the system.
   * @param <O>              The type of output produced by the system.
   */
  public <I, O> Zosma(
      final Publisher<I> inputPublisher,
      final Function<Publisher<I>, Publisher<O>> ai,
      final Consumer<O> outputConsumer
  ) {
    this.zosma = () -> Flux.from(inputPublisher)
        .transform(ai)
        .subscribe(outputConsumer);
  }

  @Override
  public void run() {
    this.zosma.run();
  }
}
