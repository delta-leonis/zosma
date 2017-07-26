package org.ssh.game.engine;

import java.util.function.Function;
import org.reactivestreams.Publisher;

/**
 * The Interface Engine.
 *
 * This interface describes the functionality of an engine, ie. an {@link Function} from {@link
 * Publisher publishers of} input states to {@link Publisher publishers of} output states.
 *
 * @param <I> The type of input interpreted by this Engine.
 * @param <O> The type of output produced by this Engine.
 * @author Rimon Oz
 */
public interface Engine<I, O> {
  Publisher<O> transform(final Publisher<I> inputPublisher);
}
