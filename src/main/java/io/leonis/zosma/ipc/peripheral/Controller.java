package io.leonis.zosma.ipc.peripheral;

import java.util.*;

/**
 * @author Jeroen de Jong
 */
public interface Controller<I, S> {
  I getIdentifier();

  S getControls();

  interface MappingSupplier<C extends Controller, A> {
    Map<C, Set<A>> getAgentMapping();
  }
}
