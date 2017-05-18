package org.ssh.ipc.serialization.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import lombok.Value;

/**
 * The Class SnippetWriteHandler.
 *
 * This class is used in the {@link JsonWriterTest} to write {@link Snippet snippets}.
 *
 * @author Rimon Oz
 */
@Value
public class SnippetWriteHandler implements JsonWriteHandler<Snippet> {
  @Override
  public JsonElement serialize(final Snippet src, final Type typeOfSrc, final JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("title", src.getTitle());
    jsonObject.addProperty("body", src.getBody());
    return jsonObject;
  }
}