package io.leonis.zosma.ipc.serialization.protobuf.vision;

import com.google.common.collect.ImmutableSet;
import io.leonis.algieba.geometry.Vectors;
import io.leonis.zosma.game.data.*;
import io.leonis.zosma.game.engine.Deducer;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * The Class GoalsDeducer.
 *
 * Deduces the two {@link Goal Goals} based on the current {@link Field} and {@link GoalDimension}.
 *
 * @author Jeroen de Jong
 */
public class GoalsDeducer<I extends Referee.Supplier & GoalDimension.Supplier & Field.Supplier>
    implements Deducer<I, Goal.SetSupplier> {

  @Override
  public Publisher<Goal.SetSupplier> apply(final Publisher<I> iPublisher) {
    return Flux.from(iPublisher)
        .map(frame ->
            () -> ImmutableSet.of(
                new Goal.State(
                    frame.getGoalDimension(),
                    Vectors.columnVector((frame.getField().getLength() + frame.getGoalDimension().getDepth()) / 2f, 0f),
                    frame.getReferee().getPositiveHalfTeam(),
                    FieldHalf.POSITIVE),
                new Goal.State(
                    frame.getGoalDimension(),
                    Vectors.columnVector(-1f * ((frame.getField().getLength() + frame.getGoalDimension().getDepth()) / 2f), 0f),
                    frame.getReferee().getNegativeHalfTeam(),
                    FieldHalf.NEGATIVE)));
  }
}
