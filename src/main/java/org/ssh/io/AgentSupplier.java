package org.ssh.io;

import java.util.Set;
import org.ssh.game.Agent;

/**
 * The Interface AgentSupplier.
 *
 * This interface describes the functionality of an object which exposes a {@link Set} of {@link
 * Agent}.
 *
 * @param  <A> The type of {@link Agent} supplied by this supplier.
 * @author Jeroen de Jong
 */
public interface AgentSupplier<A extends Agent> {
  Set<A> getAgents();
}
