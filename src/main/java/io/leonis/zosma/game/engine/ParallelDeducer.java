package io.leonis.zosma.game.engine;

import io.leonis.zosma.function.*;
import java.util.function.*;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
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
public final class ParallelDeducer<I, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, O>
    implements Deducer<I, O> {
  /**
   * The combinator which takes all outputs from the deducers and combines them
   * into a single object.
   */
  private final Function<Object[], O> combinator;
  /**
   * The individual {@link Deducer deducers}.
   */
  private final Function<Publisher<I>, Publisher>[] deducers;

  /**
   * Constructs a new ParallelDeducer with two {@link Deducer}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param combinator    A {@link BiFunction} which combines two deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final BiFunction<? super T1, ? super T2, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer};
    this.combinator = tuple -> combinator.apply((T1) tuple[0], (T2) tuple[1]);
  }

  /**
   * Constructs a new ParallelDeducer with three {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param combinator    A {@link Function3} which combines three deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function3<? super T1, ? super T2, ? super T3, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer};
    this.combinator = tuple -> combinator.apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2]);
  }

  /**
   * Constructs a new ParallelDeducer with four {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param fourthDeducer The fourth {@link Deducer}.
   * @param combinator    A {@link Function4} which combines four deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3]);
  }

  /**
   * Constructs a new ParallelDeducer with five {@link Deducer deducers}.
   *
   * @param firstDeducer  The first {@link Deducer}.
   * @param secondDeducer The second {@link Deducer}.
   * @param thirdDeducer  The third {@link Deducer}.
   * @param fourthDeducer The fourth {@link Deducer}.
   * @param fifthDeducer  The fifth {@link Deducer}.
   * @param combinator    A {@link Function5} which combines five deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function<Publisher<I>, Publisher<? extends T5>> fifthDeducer,
      final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3], (T5) tuple[4]);
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
   * @param combinator    A {@link Function6} which combines six deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function<Publisher<I>, Publisher<? extends T5>> fifthDeducer,
      final Function<Publisher<I>, Publisher<? extends T6>> sixthDeducer,
      final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3], (T5) tuple[4],
            (T6) tuple[5]);
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
   * @param combinator     A {@link Function7} which combines seven deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function<Publisher<I>, Publisher<? extends T5>> fifthDeducer,
      final Function<Publisher<I>, Publisher<? extends T6>> sixthDeducer,
      final Function<Publisher<I>, Publisher<? extends T7>> seventhDeducer,
      final Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3], (T5) tuple[4],
            (T6) tuple[5], (T7) tuple[6]);
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
   * @param combinator     A {@link Function8} which combines eight deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function<Publisher<I>, Publisher<? extends T5>> fifthDeducer,
      final Function<Publisher<I>, Publisher<? extends T6>> sixthDeducer,
      final Function<Publisher<I>, Publisher<? extends T7>> seventhDeducer,
      final Function<Publisher<I>, Publisher<? extends T8>> eighthDeducer,
      final Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer, eighthDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3], (T5) tuple[4],
            (T6) tuple[5], (T7) tuple[6], (T8) tuple[7]);
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
   * @param combinator     A {@link Function9} which combines nine deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function<Publisher<I>, Publisher<? extends T5>> fifthDeducer,
      final Function<Publisher<I>, Publisher<? extends T6>> sixthDeducer,
      final Function<Publisher<I>, Publisher<? extends T7>> seventhDeducer,
      final Function<Publisher<I>, Publisher<? extends T8>> eighthDeducer,
      final Function<Publisher<I>, Publisher<? extends T9>> ninthDeducer,
      final Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer, eighthDeducer, ninthDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3], (T5) tuple[4],
            (T6) tuple[5], (T7) tuple[6], (T8) tuple[7], (T9) tuple[8]);
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
   * @param tenthDeducer   The tenth {@link Deducer}.
   * @param combinator     A {@link Function9} which combines ten deductions.
   */
  public ParallelDeducer(
      final Function<Publisher<I>, Publisher<? extends T1>> firstDeducer,
      final Function<Publisher<I>, Publisher<? extends T2>> secondDeducer,
      final Function<Publisher<I>, Publisher<? extends T3>> thirdDeducer,
      final Function<Publisher<I>, Publisher<? extends T4>> fourthDeducer,
      final Function<Publisher<I>, Publisher<? extends T5>> fifthDeducer,
      final Function<Publisher<I>, Publisher<? extends T6>> sixthDeducer,
      final Function<Publisher<I>, Publisher<? extends T7>> seventhDeducer,
      final Function<Publisher<I>, Publisher<? extends T8>> eighthDeducer,
      final Function<Publisher<I>, Publisher<? extends T9>> ninthDeducer,
      final Function<I, ? extends T10> tenthDeducer,
      final Function10<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? extends O> combinator
  ) {
    this.deducers = new Function[]{firstDeducer, secondDeducer, thirdDeducer, fourthDeducer,
        fifthDeducer, sixthDeducer, seventhDeducer, eighthDeducer, ninthDeducer, tenthDeducer};
    this.combinator = tuple -> combinator
        .apply((T1) tuple[0], (T2) tuple[1], (T3) tuple[2], (T4) tuple[3], (T5) tuple[4],
            (T6) tuple[5], (T7) tuple[6], (T8) tuple[7], (T9) tuple[8], (T10) tuple[9]);
  }

  @Override
  public Publisher<O> apply(final Publisher<I> inputPublisher) {
    return Flux.from(inputPublisher)
        .transform(iPublisher -> Flux.combineLatest(
            this.combinator,
            Stream.of(this.deducers).sequential()
                .map(deducer -> iPublisher.transform(deducer::apply))
                .toArray(Publisher[]::new)));
  }
}
