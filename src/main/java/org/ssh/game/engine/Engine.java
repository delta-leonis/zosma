package org.ssh.game.engine;

import java.util.Map;
import java.util.function.Function;
import org.reactivestreams.*;
import org.ssh.game.*;

/**
 * The Interface Engine.
 *
 * This interface describes the functionality of an engine, ie. an {@link Function} from {@link
 * Publisher publishers of} input states to {@link Publisher publishers of} output states
 * which can {@link Arbiter#arbitrate(Map)}  be arbitrated}.
 *
 * @param <S> The type of output produced by this Engine.
 * @param <G> The type of input interpreted by this Engine.
 * @param <P> The type of parts of which this Engine consists.
 * @author Rimon Oz
 */
public interface Engine<S, G, P> extends Arbiter<S, P>, Function<Publisher<G>, Publisher<S>> {
}
