package io.leonis.zosma.ipc.serialization.json;

import com.google.gson.*;
import io.leonis.zosma.game.data.PlayerCommand;
import io.leonis.zosma.ipc.serialization.gson.PlayerCommandSerializer;
import java.util.function.Function;

/**
 * @author Jeroen de Jong
 */
public class PlayerCommandWriter implements Function<PlayerCommand, String> {
  private final Gson gson;

  public PlayerCommandWriter() {
    this.gson = new GsonBuilder()
        .registerTypeAdapter(PlayerCommand.class, new PlayerCommandSerializer())
        .create();
  }

  @Override
  public String apply(final PlayerCommand playerCommand) {
    return this.gson.toJson(playerCommand);
  }
}
