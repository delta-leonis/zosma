package org.ssh.ipc.serialization;

/**
 * Interface for serializable objects.
 *
 * @author Ryan Meulenkamp
 */
public interface Serializable<O, C extends SerializationContext> extends java.io.Serializable {
    /**
     * Method for serializing this object.
     *
     * @return The serialized form of this object.
     */
    O serialize(final C serializationContext);
}
