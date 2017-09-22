package org.ssh.game.engine;

import java.util.function.*;
import java.util.stream.Stream;
import lombok.*;
import org.reactivestreams.Publisher;
import org.ssh.io.ConfigSupplier;
import org.ssh.math.function.*;
import reactor.core.publisher.Flux;

/**
 * The Class ParallelDeducer.
 *
 * This class describes the functionality of a {@link Deducer} which takes a {@link Flux} of inputs,
 * parses them through a collection of {@link Deducer deducers} and subsequently combines them with
 * the original data from which the deductions were derived.
 *
 * @param <I> The type of input interpreted by this {@link ParallelDeducer}.
 * @param <O> The type of output produced by this {@link ParallelDeducer}.
 * @author Rimon Oz
 */
@AllArgsConstructor
@SuppressWarnings("unchecked")
public final class ParallelDeducer<I extends ConfigSupplier, T1, T2, T3, T4, T5, T6, T7, T8, T9, O>
    implements Deducer<I, O> {
  private final Function<Object[], O> combinator;
  private final Deducer<I, ?>[] deducers;

  /**
   * Constructs a new ParallelDeducer with a single {@link Deducer}.
   *
   * @param firstDeducer The first {@link Deducer}.
   * @param combinator   A {@link BiFunction} which combines the original data and a single
   *                     deduction.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final BiFunction<? super I, ? super T1, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer};
    this.combinator = tuple -> combinator.apply((I) tuple[0], (T1) tuple[1]);
  }

  /**
   * Constructs a new ParallelDeducer with two {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param combinator    A {@link Function3} which combines the original data and two deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Function3<? super I, ? super T1, ? super T2, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer};
    this.combinator = tuple -> combinator.apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2]);
  }

  /**
   * Constructs a new ParallelDeducer with three {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param combinator    A {@link Function4} which combines the original data and three
   *                      deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Function4<? super I, ? super T1, ? super T2, ? super T3, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3]);
  }

  /**
   * Constructs a new ParallelDeducer with four {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param fourthDeducer The fourth {@link Deducer}.
   * @param combinator    A {@link Function5} which combines the original data and four deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Deducer<I, ? extends T4> fourthDeducer,
      final Function5<? super I, ? super T1, ? super T2, ? super T3, ? super T4, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3], (T4) tuple[4]);
  }

  /**
   * Constructs a new ParallelDeducer with five {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param fourthDeducer The fourth {@link Deducer}.
   * @param fifthDeducer  The fifth {@link Deducer}.
   * @param combinator    A {@link Function6} which combines the original data and five deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Deducer<I, ? extends T4> fourthDeducer,
      final Deducer<I, ? extends T5> fifthDeducer,
      final Function6<? super I, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3], (T4) tuple[4],
            (T5) tuple[5]);
  }

  /**
   * Constructs a new ParallelDeducer with six {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param fourthDeducer The fourth {@link Deducer}.
   * @param fifthDeducer  The fifth {@link Deducer}.
   * @param sixthDeducer  The sixth {@link Deducer}.
   * @param combinator    A {@link Function7} which combines the original data and six deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Deducer<I, ? extends T4> fourthDeducer,
      final Deducer<I, ? extends T5> fifthDeducer,
      final Deducer<I, ? extends T6> sixthDeducer,
      final Function7<? super I, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3], (T4) tuple[4],
            (T5) tuple[5], (T6) tuple[6]);
  }

  /**
   * Constructs a new ParallelDeducer with seven {@link Deducer deducers}.
   *
   * @param firstDeducer   The first {@link Deducer}.
   * @param secondDeducer  The second {@link Deducer}.
   * @param thirdDeducer   The third {@link Deducer}.
   * @param fourthDeducer  The fourth {@link Deducer}.
   * @param fifthDeducer   The fifth {@link Deducer}.
   * @param sixthDeducer   The sixth {@link Deducer}.
   * @param seventhDeducer The seventh {@link Deducer}.
   * @param combinator     A {@link Function8} which combines the original data and seven
   *                       deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Deducer<I, ? extends T4> fourthDeducer,
      final Deducer<I, ? extends T5> fifthDeducer,
      final Deducer<I, ? extends T6> sixthDeducer,
      final Deducer<I, ? extends T7> seventhDeducer,
      final Function8<? super I, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3], (T4) tuple[4],
            (T5) tuple[5], (T6) tuple[6], (T7) tuple[7]);
  }

  /**
   * Constructs a new ParallelDeducer with eight {@link Deducer deducers}.
   *
   * @param firstDeducer   The first {@link Deducer}.
   * @param secondDeducer  The second {@link Deducer}.
   * @param thirdDeducer   The third {@link Deducer}.
   * @param fourthDeducer  The fourth {@link Deducer}.
   * @param fifthDeducer   The fifth {@link Deducer}.
   * @param sixthDeducer   The sixth {@link Deducer}.
   * @param seventhDeducer The seventh {@link Deducer}.
   * @param eighthDeducer  The eighth {@link Deducer}.
   * @param combinator     A {@link Function9} which combines the original data and eight
   *                       deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Deducer<I, ? extends T4> fourthDeducer,
      final Deducer<I, ? extends T5> fifthDeducer,
      final Deducer<I, ? extends T6> sixthDeducer,
      final Deducer<I, ? extends T7> seventhDeducer,
      final Deducer<I, ? extends T8> eighthDeducer,
      final Function9<? super I, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer, eighthDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3], (T4) tuple[4],
            (T5) tuple[5], (T6) tuple[6], (T7) tuple[7], (T8) tuple[8]);
  }

  /**
   * Constructs a new ParallelDeducer with nine {@link Deducer deducers}.
   *
   * @param firstDeducer   The first {@link Deducer}.
   * @param secondDeducer  The second {@link Deducer}.
   * @param thirdDeducer   The third {@link Deducer}.
   * @param fourthDeducer  The fourth {@link Deducer}.
   * @param fifthDeducer   The fifth {@link Deducer}.
   * @param sixthDeducer   The sixth {@link Deducer}.
   * @param seventhDeducer The seventh {@link Deducer}.
   * @param eighthDeducer  The eighth {@link Deducer}.
   * @param ninthDeducer   The ninth {@link Deducer}.
   * @param combinator     A {@link Function9} which combines the original data and nine
   *                       deductions.
   */
  public ParallelDeducer(
      final Deducer<I, ? extends T1> firstDeducer,
      final Deducer<I, ? extends T2> secondDeducer,
      final Deducer<I, ? extends T3> thirdDeducer,
      final Deducer<I, ? extends T4> fourthDeducer,
      final Deducer<I, ? extends T5> fifthDeducer,
      final Deducer<I, ? extends T6> sixthDeducer,
      final Deducer<I, ? extends T7> seventhDeducer,
      final Deducer<I, ? extends T8> eighthDeducer,
      final Deducer<I, ? extends T9> ninthDeducer,
      final Function10<? super I, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends O> combinator
  ) {
    this.deducers = new Deducer[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer, eighthDeducer, ninthDeducer};
    this.combinator = tuple -> combinator
        .apply((I) tuple[0], (T1) tuple[1], (T2) tuple[2], (T3) tuple[3], (T4) tuple[4],
            (T5) tuple[5], (T6) tuple[6], (T7) tuple[7], (T8) tuple[8], (T9) tuple[9]);
  }

  @Override
  public Publisher<O> apply(final Publisher<I> inputPublisher) {
    return Flux.from(inputPublisher)
        .transform(iPublisher -> Flux.combineLatest(
            this.combinator,
            Stream.concat(
                Stream.of(iPublisher),
                Stream.of(this.deducers).sequential()
                    .map(deducer -> iPublisher.transform(deducer::apply)))
                .toArray(Publisher[]::new)));
  }
}
