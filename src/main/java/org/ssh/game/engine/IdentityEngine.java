package org.ssh.game.engine;

/**
 * The Interface IdentityEngine.
 *
 * This interface describes the functionality of an {@link Engine} whose output
 * has the same type as its input.
 *
 * @param <I> The type of input handled by this engine.
 * @author Rimon Oz
 */
public interface IdentityEngine<I> extends Engine<I, I> {
}
