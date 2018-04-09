package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.MovingBall;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Ball objects.
 *
 * @author Ryan Meulenkamp
 */
public final class MovingBallSerializer implements JsonSerializer<MovingBall> {
  @Override
  public JsonElement serialize(
      final MovingBall ball,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("timestamp", ball.getTimestamp());
    jsonObject.addProperty("x", ball.getX());
    jsonObject.addProperty("y", ball.getY());
    jsonObject.addProperty("z", ball.getZ());
    jsonObject.addProperty("xVelocity", ball.getXVelocity());
    jsonObject.addProperty("yVelocity", ball.getYVelocity());
    jsonObject.addProperty("zVelocity", ball.getZVelocity());
    return jsonObject;
  }
}