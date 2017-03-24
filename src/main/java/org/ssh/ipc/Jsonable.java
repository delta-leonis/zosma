package org.ssh.ipc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * The Interface Jsonable.
 *
 * This interface describes the functionality of an object which can be serialized to JSON.
 *
 * @author Ryan Meulenkamp
 */
public interface Jsonable {
    /**
     * Static builder for {@link Gson} instances. Keeps track of type adapters, serializers and deserializers.
     */
    GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();

    /**
     * Add a new type adapter. Delegation of {@link GsonBuilder#registerTypeAdapter(Type, Object)}.
     *
     * @param typeAdapter A type adapter used to describe how an object should be (de)serialized
     * @return The Gson object that is build after the given typeadapter is added.
     */
    static Gson addTypeAdapter(final Class<?> clazz, final Object typeAdapter) {
        return gsonBuilder.registerTypeAdapter(clazz, typeAdapter).create();
    }

    /**
     * Method used to create json of this object.
     *
     * @return this object as a json string.
     */
    default String toJson() {
        return gsonBuilder.create().toJson(this);
    }
}
