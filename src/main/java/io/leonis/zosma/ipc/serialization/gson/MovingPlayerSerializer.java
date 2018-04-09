package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.MovingPlayer;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of Player objects.
 *
 * @author Ryan Meulenkamp
 */
public class MovingPlayerSerializer implements JsonSerializer<MovingPlayer> {
  @Override
  public JsonElement serialize(
      final MovingPlayer src,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", src.getId());
    jsonObject.addProperty("timeStamp", src.getTimestamp());
    jsonObject.addProperty("x", src.getX());
    jsonObject.addProperty("y", src.getY());
    jsonObject.addProperty("orientation", src.getOrientation());
    jsonObject.addProperty("xVelocity", src.getXVelocity());
    jsonObject.addProperty("yVelocity", src.getYVelocity());
    jsonObject.addProperty("orientationVelocity", src.getOrientationVelocity());
    jsonObject.add("teamColor", context.serialize(src.getTeamColor()));
    return jsonObject;
  }
}
