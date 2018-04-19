package io.leonis.zosma.ipc.serialization.protobuf.refbox;

import io.leonis.zosma.game.data.Team;
import io.reactivex.functions.*;
import java.util.*;
import lombok.AllArgsConstructor;
import org.robocup.ssl.Referee.SSL_Referee.TeamInfo;

/**
 * @author jeroen.dejong.
 */
@AllArgsConstructor
public final class TeamSelector<T extends Team> implements BiFunction<TeamInfo, Long, T> {
  Function9<Long, String, Integer, Integer, Integer, List<Integer>, Integer, Integer, Integer, T> constructor;
  @Override
  public T apply(final TeamInfo teamInfo, Long timestamp) throws Exception {
    return constructor.apply(
        timestamp,
        teamInfo.getName(),
        teamInfo.getScore(),
        teamInfo.getRedCards(),
        teamInfo.getYellowCardTimesCount(),
        Collections.unmodifiableList(teamInfo.getYellowCardTimesList()),
        teamInfo.getTimeouts(),
        teamInfo.getTimeoutTime(),
        teamInfo.getGoalie());
  }
}
