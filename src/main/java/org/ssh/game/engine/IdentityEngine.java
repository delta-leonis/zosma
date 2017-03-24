package org.ssh.game.engine;

/**
 * The Interface IdentityEngine.
 * <p>
 * This interface describes the functionality of an identity engine, that is
 * an engine which takes an input stream of a specific type and provides an
 * output stream with data of the same type.
 *
 * @param <I> The type of object which the engine accepts and emits.
 * @param <P> The type of engine part.
 * @author Rimon Oz
 */
public interface IdentityEngine<I, P> extends ProcessorEngine<I, I, P> {

}
