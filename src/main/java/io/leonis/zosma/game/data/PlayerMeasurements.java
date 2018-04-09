package io.leonis.zosma.game.data;

import io.leonis.zosma.game.data.Player.PlayerIdentity;
import java.util.Map;
import lombok.Value;

/**
 * The Class PlayerMeasurements.
 *
 * This class represents a collection of measurements for a {@link PlayerIdentity specific robot}.
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface PlayerMeasurements {

  /**
   * @return The identity of the player.
   */
  PlayerIdentity getPlayerIdentity();

  /**
   * @return Measurements identified by a unique string.
   */
  Map<String, Double> getMeasurements();

  @Value
  class State implements PlayerMeasurements {
    private final PlayerIdentity playerIdentity;
    private final Map<String, Double> measurements;
  }
}
