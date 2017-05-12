package org.ssh.game.engine;

import java.util.*;
import org.ssh.game.Arbiter;

/**
 * The Interface MapEngine. <p> This interface describes the functionality of a mapped engine. A
 * mapped engineis an {@link Arbiter} with parts, which process some type of fuel into emissions. A
 * mapped engine contains a set of parts which are mapped to an identifying value.
 *
 * @param <M> The type of object used to identify an engine part.
 * @param <O> The type of object used as the engine's output.
 * @param <P> The type of engine part.
 * @author Rimon Oz
 */
public interface MapEngine<M, O, P> extends Engine<O, P> {

  @Override
  default Set<P> getParts() {
    return this.getPartsMap().keySet();
  }

  /**
   * Returns a mapping of the engine's parts into their identifying values.
   *
   * @return A set of the engine's parts into their identifying values.
   */
  Map<P, M> getPartsMap();
}
