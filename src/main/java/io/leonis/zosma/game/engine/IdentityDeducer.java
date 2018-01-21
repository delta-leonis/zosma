package io.leonis.zosma.game.engine;

import org.reactivestreams.Publisher;

/**
 * The Class IdentityDeducer.
 *
 * Deduces the input using an identity operation.
 *
 * @author Rimon Oz
 */
public class IdentityDeducer<I> implements Deducer<I, I> {
  @Override
  public Publisher<I> apply(final Publisher<I> inputPublisher) {
    return inputPublisher;
  }
}
