package org.ssh.ipc.serialization.json;

import java.io.Serializable;
import org.ssh.ipc.serialization.WriteHandler;

/**
 * The Interface JsonWriteHandler.
 *
 * This interface describes the functionality of an object which couples Gson's #write
 * to a {@link WriteHandler} to allow for writing as JSON.
 *
 * @param <O> Type of object which will be written
 * @author Jeroen de Jong
 */
public interface JsonWriteHandler<O extends Serializable>
    extends com.google.gson.JsonSerializer<O>, WriteHandler<O> {
}
