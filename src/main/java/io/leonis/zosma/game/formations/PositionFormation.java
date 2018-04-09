package io.leonis.zosma.game.formations;

import io.leonis.zosma.game.Formation;
import io.leonis.zosma.game.data.Player;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import java.util.Map;
import lombok.Value;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * The Interface PositionFormation.
 *
 * This interface describes the functionality of a formation in which {@link Player SSL robots}
 * are assigned positions on the field.
 *
 * @author Rimon Oz
 */
@Value
public class PositionFormation implements Formation<PlayerIdentity, INDArray> {
  private final Map<PlayerIdentity, INDArray> positions;

  @Override
  public INDArray getFormationFor(final PlayerIdentity player) {
    return this.getPositions().get(player);
  }
}
