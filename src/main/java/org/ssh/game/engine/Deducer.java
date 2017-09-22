package org.ssh.game.engine;

import java.util.function.Function;
import org.reactivestreams.Publisher;

/**
 * The Interface Deducer.
 *
 * This interface describes the functionality of an engine, ie. an {@link Function} from {@link
 * Publisher publishers of} input states to {@link Publisher publishers of} output states.
 *
 * @param <I> The type of input interpreted by this Deducer.
 * @param <O> The type of output produced by this Deducer.
 * @author Rimon Oz
 */
public interface Deducer<I, O> extends Function<Publisher<I>, Publisher<O>> {
}
