package io.leonis.zosma.game.data;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.Team.TeamIdentity;
import java.io.Serializable;
import java.util.Set;
import lombok.Value;

/**
 * The Class RefereeState.
 *
 * This class represents the state of a Small Size League referee object, based on <a href="https://github.com/RoboCup-SSL/ssl-refbox/blob/0bc7511caffe0ed0e6026f2c652a00fb1eea47e3/referee.proto"
 * >RoboCup-SSL/ssl-refbox</a>
 *
 * @author Rimon Oz
 * @author Jeroen de Jong
 */
public interface Referee extends Temporal, Serializable {

  /**
   * @return The coarse {@link Stage}.
   */
  Stage getCoarseStage();

  /**
   * @return The amount of the time left in this stage of the game in seconds.
   */
  double getTimeLeftInStage();

  /**
   * @return The latest {@link Command} issued by the referee.
   */
  Command getCommand();

  /**
   * @return The timestamp of the last issued command.
   */
  double getCommandTimeStamp();

  /**
   * @return The {@link TeamIdentity} playing on the {@link FieldHalf#POSITIVE positive field half}.
   */
  TeamIdentity getPositiveHalfTeam();

  /**
   * @return The {@link TeamIdentity} playing on the {@link FieldHalf#NEGATIVE negative field half}.
   */
  TeamIdentity getNegativeHalfTeam();

  /**
   * @return The number of commands sent since the start of the game.
   */
  int getCommandCount();

  /**
   * @return All active teams in this game.
   */
  Set<Team> getTeams();

  enum Command {
    HALT,
    STOP,
    NORMAL_START,
    FORCE_START,
    PREPARE_KICKOFF_YELLOW,
    PREPARE_KICKOFF_BLUE,
    PREPARE_PENALTY_YELLOW,
    PREPARE_PENALTY_BLUE,
    DIRECT_FREE_YELLOW,
    DIRECT_FREE_BLUE,
    INDIRECT_FREE_YELLOW,
    INDIRECT_FREE_BLUE,
    TIMEOUT_YELLOW,
    TIMEOUT_BLUE,
    GOAL_YELLOW,
    GOAL_BLUE,
    UNRECOGNIZED;
  }

  enum Stage {
    NORMAL_FIRST_HALF_PRE,
    NORMAL_FIRST_HALF,
    NORMAL_HALF_TIME,
    NORMAL_SECOND_HALF_PR,
    NORMAL_SECOND_HALF,
    EXTRA_TIME_BREAK,
    EXTRA_FIRST_HALF_PRE,
    EXTRA_FIRST_HALF,
    EXTRA_HALF_TIME,
    EXTRA_SECOND_HALF_PRE,
    EXTRA_SECOND_HALF,
    PENALTY_SHOOTOUT_BREAK,
    PENALTY_SHOOTOUT,
    POST_GAME,
    UNRECOGNIZED;
  }

  interface Supplier {
    Referee getReferee();
  }

  @Value
  class State implements Referee {
    private final long timestamp;
    private final Stage coarseStage;
    private final double timeLeftInStage;
    private final Command command;
    private final Set<Team> teams;
    private final double commandTimeStamp;
    private final TeamIdentity positiveHalfTeam, negativeHalfTeam;
    private final int commandCount;
  }
}
