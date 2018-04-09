package io.leonis.zosma.ipc.serialization.gson;

import com.google.gson.*;
import io.leonis.zosma.game.data.PlayerCommand;
import java.lang.reflect.Type;

/**
 * Class for handling serialization of PlayerCommand objects.
 *
 * @author Ryan Meulenkamp
 */
public class PlayerCommandSerializer implements JsonSerializer<PlayerCommand> {
  @Override
  public JsonElement serialize(
      final PlayerCommand src,
      final Type typeOfSrc,
      final JsonSerializationContext context
  ) {
    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("xVelocity", src.getVelocityX());
    jsonObject.addProperty("yVelocity", src.getVelocityY());
    jsonObject.addProperty("rVelocity", src.getVelocityR());
    jsonObject.addProperty("flatKick", src.getFlatKick());
    jsonObject.addProperty("chipKick", src.getChipKick());
    jsonObject.addProperty("dribblerSpin", src.getDribblerSpin());
    return jsonObject;
  }
}
