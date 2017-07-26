package org.ssh.io;

import java.util.*;
import org.ssh.game.Agent;
import org.ssh.ipc.peripheral.Controller;

/**
 * The Interface ControllerAware.
 *
 * This interface describes the functionality of an object which exposes a {@link Map} of
 * {@link Controller} to {@link Set sets} of {@link Agent}.
 *
 * @author Jeroen de Jong
 * @param <U> The type of object used to uniquely identify a {@link Controller}.
 */
public interface ControllerAware<C extends Controller, A> {
  Map<C, Set<A>> getAgentMapping();
}
