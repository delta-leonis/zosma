package org.ssh.ipc.peripheral;

import java.util.function.Function;

/**
 * @author Jeroen de Jong
 */
public interface ControllerHandler<C extends Controller, O> extends Function<C, O> {
}
