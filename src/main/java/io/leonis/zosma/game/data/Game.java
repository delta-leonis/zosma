package io.leonis.zosma.game.data;

import lombok.*;

/**
 * The Interface Game.
 *
 * Describes the state of a game.
 *
 * @author Jeroen de Jong
 * @author Rimon Oz
 */
public interface Game {

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
   * @return The number of commands sent since the start of the game.
   */
  int getCommandCount();

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

  @Value
  @AllArgsConstructor
  class State implements Game {
    private final Stage coarseStage;
    private final double timeLeftInStage;
    private final Command command;
    private final double commandTimeStamp;
    private final int commandCount;
    private final long timestamp;
  }
}
