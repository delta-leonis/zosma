package io.leonis.zosma.ipc.serialization.protobuf.vision;

import com.google.common.collect.ImmutableSet;
import io.leonis.algieba.geometry.Vectors;
import io.leonis.zosma.game.data.*;
import io.reactivex.functions.Function3;
import java.util.Set;

/**
 * The Class GoalsSelector.
 *
 * Deduces the two {@link Goal Goals} based on the current {@link Field} and {@link GoalDimension}.
 *
 * @author Jeroen de Jong
 */
public class GoalsSelector implements Function3<Field, GoalDimension, Referee, Set<Goal>> {

  @Override
  public Set<Goal> apply(final Field field, final GoalDimension goalDimension, final Referee referee) {
    return ImmutableSet.of(
        new Goal.State(
            goalDimension,
            Vectors.columnVector((field.getLength() + goalDimension.getDepth()) / 2f, 0f),
            referee.getPositiveHalfTeam(),
            FieldHalf.POSITIVE),
        new Goal.State(
            goalDimension,
            Vectors.columnVector(-1f * ((field.getLength() + goalDimension.getDepth()) / 2f), 0f),
            referee.getNegativeHalfTeam(),
            FieldHalf.NEGATIVE));
  }
}
