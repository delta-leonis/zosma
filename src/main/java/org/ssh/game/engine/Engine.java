package org.ssh.game.engine;

import java.util.Set;
import org.ssh.game.Arbiter;

/**
 * The Interface Engine.
 * <p>
 * This interface describes the functionality of an engine. An engine is an {@link Arbiter}
 * with parts, which process some type of fuel into emissions. An engine contains an
 * unordered set of parts.
 *
 * @param <O> The type of object used as the engine's output.
 * @param <P> The type of engine part.
 * @author Rimon Oz
 */
public interface Engine<O, P> extends Arbiter<O, P> {

  /**
   * Returns a set of the engine's parts.
   *
   * @return A set of the engine's parts.
   */
  Set<P> getParts();
}
