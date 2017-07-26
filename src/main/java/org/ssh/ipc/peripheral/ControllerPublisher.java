package org.ssh.ipc.peripheral;

import java.util.*;
import org.reactivestreams.Publisher;
import org.ssh.game.Agent;
import org.ssh.io.ControllerAware;

/**
 * @author Jeroen de Jong
 */
public interface ControllerPublisher<U, C extends Controller<U, ?>, A extends Agent> extends Publisher<ControllerAware<C, A>> {

  /**
   *
   * @return Mapping of applicable agents per controller identifier
   */
  Map<U, Set<A>> getControllerMapping();
}
