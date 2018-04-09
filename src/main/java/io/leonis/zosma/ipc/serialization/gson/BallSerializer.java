package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.Ball;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Ball objects.
 *
 * @author Ryan Meulenkamp
 */
public final class BallSerializer implements JsonSerializer<Ball> {
  @Override
  public JsonElement serialize(
      final Ball ball,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("timestamp", ball.getTimestamp());
    jsonObject.addProperty("x", ball.getX());
    jsonObject.addProperty("y", ball.getY());
    jsonObject.addProperty("z", ball.getZ());
    return jsonObject;
  }
}