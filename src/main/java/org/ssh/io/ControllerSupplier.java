package org.ssh.io;

import java.util.*;
import org.ssh.game.Agent;
import org.ssh.ipc.peripheral.Controller;

/**
 * The Interface ControllerSupplier.
 *
 * This interface describes the functionality of an object which exposes a {@link Map} of
 * {@link Controller} to {@link Set sets} of {@link Agent}.
 *
 * @param <C> The type of {@link Controller}.
 * @param <A> The type of {@link Agent} to which the controllers are mapped.
 * @author Jeroen de Jong
 */
public interface ControllerSupplier<C extends Controller, A> {
  Map<C, Set<A>> getAgentMapping();
}
