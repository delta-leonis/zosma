package org.ssh.ipc.serialization;

/**
 * Json helper interface
 *
 * @author Ryan Meulenkamp
 */
public interface JsonSerializable extends Serializable<String, JsonSerializationContext> {
    /**
     * Method used to create json of this object.
     *
     * @return this object as a json string.
     */
    @Override
    default String serialize(final JsonSerializationContext serializationContext) {
        return serializationContext.serialize(this);
    }

    /**
     * Exception for the collection of JsonSerializers.
     */
    class JsonSerializableCollectionException extends RuntimeException {}
}
