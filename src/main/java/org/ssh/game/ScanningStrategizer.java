package org.ssh.game;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.*;

/**
 * The Interface ScanningStrategizer.
 * <p>
 * This interface describes the functionality of an object which
 * {@link Subscriber subscribes} to {@link Game a game} and produces a
 * {@link Strategy} based on the latest emitted {@link Strategy} and the
 * newest {@link Game} state.
 *
 * @param <S> The type of {@link Strategy} emitted by this strategizer.
 * @param <G> The type of (game) object for which this engine is meant.
 * @author Rimon Oz
 */
@Value
@NonFinal
public abstract class ScanningStrategizer<S extends Strategy, G>
    implements Strategizer<S, G> {
  /**
   * The {@link TopicProcessor} on which new {@link Game game states} are submitted.
   */
  private final FluxProcessor<G, G> gameProcessor = TopicProcessor.create();
  /**
   * The {@link Flux} to which the scanner is applied.
   */
  private final Flux<S> scanningProcessor;

  public ScanningStrategizer(final S initialStrategy) {
    this.scanningProcessor = this.gameProcessor
        .scan(initialStrategy, this::strategize);
  }

  @Override
  public S strategize(final G mostRecentGame) {
    this.gameProcessor.onNext(mostRecentGame);
    return this.scanningProcessor.blockFirst();
  }

  /**
   * Computes a {@link Strategy} based on the previously computed {@link Strategy} and
   * most recent {@link Game game state}.
   *
   * @param previousStrategy The previously returned {@link Strategy}.
   * @param mostRecentGame   The newest {@link Game game state}.
   * @return The newly computed {@link Strategy}.
   */
  public abstract S strategize(final S previousStrategy, final G mostRecentGame);
}
