package io.leonis.zosma.ipc.protobuf;

import io.leonis.zosma.game.data.*;
import io.leonis.zosma.ipc.protobuf.vision.*;
import io.leonis.zosma.parameters.Triplet;
import io.reactivex.functions.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Wrapper.WrapperPacket;

/**
 * The Class VisionPacketAdapter.
 *
 * This class represents a {@link Function} which creates {@link Triplet} from {@link
 * WrapperPacket}.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
@AllArgsConstructor
public class VisionPacketAdapter implements BiFunction<WrapperPacket, AllegianceTuple<Team>, Triplet<Field, Set<Ball>, AllegianceTuple<Set<Player>>>> {

  @Override
  public Triplet<Field, Set<Ball>, AllegianceTuple<Set<Player>>> apply(
      final WrapperPacket wrapperPacket,
      final AllegianceTuple<Team> teams
  ) throws Exception {
    return new Triplet<>(
        new FieldAdapter().apply(wrapperPacket.getGeometry()),
        new BallsAdapter().apply(wrapperPacket.getDetection()),
        new PlayersAdapter().apply(wrapperPacket.getDetection(), teams));
  }
}
