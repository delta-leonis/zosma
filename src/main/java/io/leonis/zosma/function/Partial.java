package io.leonis.zosma.function;

import java.util.function.*;
import lombok.experimental.UtilityClass;

/**
 * The Class Partial.
 *
 * Utility class containing helper methods to partially apply functions from {@link Function9} to
 * {@link Function}.
 *
 * @author Jeroen de Jong
 */
@UtilityClass
public class Partial {
  /**
   * Partially applies a {@link BiFunction} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, R> Function<I2, R> partial(final BiFunction<I1, I2, R> base, final I1 input1) {
    return (input2) -> base.apply(input1, input2);
  }

  /**
   * Partially applies a {@link Function3} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, R> Function<I3, R> partial(final Function3<I1, I2, I3, R> base, final I1 input1, final I2 input2) {
    return (input3) -> base.apply(input1, input2, input3);
  }

  /**
   * Partially applies a {@link Function3} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, R> BiFunction<I2, I3, R> partial(final Function3<I1, I2, I3, R> base, final I1 input1) {
    return (input2, input3) -> base.apply(input1, input2, input3);
  }

  /**
   * Partially applies a {@link Function4} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, R> Function<I4, R> partial(final Function4<I1, I2, I3, I4, R> base, final I1 input1, final I2 input2, final I3 input3) {
    return (input4) -> base.apply(input1, input2, input3, input4);
  }

  /**
   * Partially applies a {@link Function4} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, R> BiFunction<I3, I4, R> partial(final Function4<I1, I2, I3, I4, R> base, final I1 input1, final I2 input2) {
    return (input3, input4) -> base.apply(input1, input2, input3, input4);
  }

  /**
   * Partially applies a {@link Function4} to a {@link Function3}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, R> Function3<I2, I3, I4, R> partial(final Function4<I1, I2, I3, I4, R> base, final I1 input1) {
    return (input2, input3, input4) -> base.apply(input1, input2, input3, input4);
  }

  /**
   * Partially applies a {@link Function5} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, R> Function<I5, R> partial(final Function5<I1, I2, I3, I4, I5, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4) {
    return (input5) -> base.apply(input1, input2, input3, input4, input5);
  }

  /**
   * Partially applies a {@link Function5} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, R> BiFunction<I4, I5, R> partial(final Function5<I1, I2, I3, I4, I5, R> base, final I1 input1, final I2 input2, final I3 input3) {
    return (input4, input5) -> base.apply(input1, input2, input3, input4, input5);
  }

  /**
   * Partially applies a {@link Function5} to a {@link Function3}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, R> Function3<I3, I4, I5, R> partial(final Function5<I1, I2, I3, I4, I5, R> base, final I1 input1, final I2 input2) {
    return (input3, input4, input5) -> base.apply(input1, input2, input3, input4, input5);
  }

  /**
   * Partially applies a {@link Function5} to a {@link Function4}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, R> Function4<I2, I3, I4, I5, R> partial(final Function5<I1, I2, I3, I4, I5, R> base, final I1 input1) {
    return (input2, input3, input4, input5) -> base.apply(input1, input2, input3, input4, input5);
  }

  /**
   * Partially applies a {@link Function6} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, R> Function<I6, R> partial(final Function6<I1, I2, I3, I4, I5, I6, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5) {
    return (input6) -> base.apply(input1, input2, input3, input4, input5, input6);
  }

  /**
   * Partially applies a {@link Function6} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, R> BiFunction<I5, I6, R> partial(final Function6<I1, I2, I3, I4, I5, I6, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4) {
    return (input5, input6) -> base.apply(input1, input2, input3, input4, input5, input6);
  }

  /**
   * Partially applies a {@link Function6} to a {@link Function3}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, R> Function3<I4, I5, I6, R> partial(final Function6<I1, I2, I3, I4, I5, I6, R> base, final I1 input1, final I2 input2, final I3 input3) {
    return (input4, input5, input6) -> base.apply(input1, input2, input3, input4, input5, input6);
  }

  /**
   * Partially applies a {@link Function6} to a {@link Function4}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, R> Function4<I3, I4, I5, I6, R> partial(final Function6<I1, I2, I3, I4, I5, I6, R> base, final I1 input1, final I2 input2) {
    return (input3, input4, input5, input6) -> base.apply(input1, input2, input3, input4, input5, input6);
  }

  /**
   * Partially applies a {@link Function6} to a {@link Function5}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, R> Function5<I2, I3, I4, I5, I6, R> partial(final Function6<I1, I2, I3, I4, I5, I6, R> base, final I1 input1) {
    return (input2, input3, input4, input5, input6) -> base.apply(input1, input2, input3, input4, input5, input6);
  }

  /**
   * Partially applies a {@link Function7} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param input6 The sixth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, R> Function<I7, R> partial(final Function7<I1, I2, I3, I4, I5, I6, I7, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5, final I6 input6) {
    return (input7) -> base.apply(input1, input2, input3, input4, input5, input6, input7);
  }

  /**
   * Partially applies a {@link Function7} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, R> BiFunction<I6, I7, R> partial(final Function7<I1, I2, I3, I4, I5, I6, I7, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5) {
    return (input6, input7) -> base.apply(input1, input2, input3, input4, input5, input6, input7);
  }

  /**
   * Partially applies a {@link Function7} to a {@link Function3}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, R> Function3<I5, I6, I7, R> partial(final Function7<I1, I2, I3, I4, I5, I6, I7, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4) {
    return (input5, input6, input7) -> base.apply(input1, input2, input3, input4, input5, input6, input7);
  }

  /**
   * Partially applies a {@link Function7} to a {@link Function4}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, R> Function4<I4, I5, I6, I7, R> partial(final Function7<I1, I2, I3, I4, I5, I6, I7, R> base, final I1 input1, final I2 input2, final I3 input3) {
    return (input4, input5, input6, input7) -> base.apply(input1, input2, input3, input4, input5, input6, input7);
  }

  /**
   * Partially applies a {@link Function7} to a {@link Function5}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, R> Function5<I3, I4, I5, I6, I7, R> partial(final Function7<I1, I2, I3, I4, I5, I6, I7, R> base, final I1 input1, final I2 input2) {
    return (input3, input4, input5, input6, input7) -> base.apply(input1, input2, input3, input4, input5, input6, input7);
  }

  /**
   * Partially applies a {@link Function7} to a {@link Function6}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, R> Function6<I2, I3, I4, I5, I6, I7, R> partial(final Function7<I1, I2, I3, I4, I5, I6, I7, R> base, final I1 input1) {
    return (input2, input3, input4, input5, input6, input7) -> base.apply(input1, input2, input3, input4, input5, input6, input7);
  }

  /**
   * Partially applies a {@link Function8} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param input6 The sixth input.
   * @param input7 The seventh input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> Function<I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5, final I6 input6, final I7 input7) {
    return (input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function8} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param input6 The sixth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> BiFunction<I7, I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5, final I6 input6) {
    return (input7, input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function8} to a {@link Function3}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> Function3<I6, I7, I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5) {
    return (input6, input7, input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function8} to a {@link Function4}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> Function4<I5, I6, I7, I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4) {
    return (input5, input6, input7, input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function8} to a {@link Function5}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> Function5<I4, I5, I6, I7, I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1, final I2 input2, final I3 input3) {
    return (input4, input5, input6, input7, input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function8} to a {@link Function6}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> Function6<I3, I4, I5, I6, I7, I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1, final I2 input2) {
    return (input3, input4, input5, input6, input7, input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function8} to a {@link Function7}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, R> Function7<I2, I3, I4, I5, I6, I7, I8, R> partial(final Function8<I1, I2, I3, I4, I5, I6, I7, I8, R> base, final I1 input1) {
    return (input2, input3, input4, input5, input6, input7, input8) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param input6 The sixth input.
   * @param input7 The seventh input.
   * @param input8 The eighth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function<I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5, final I6 input6, final I7 input7, final I8 input8) {
    return (input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link BiFunction}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param input6 The sixth input.
   * @param input7 The seventh input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> BiFunction<I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5, final I6 input6, final I7 input7) {
    return (input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function3}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param input6 The sixth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function3<I7, I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5, final I6 input6) {
    return (input7, input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function4}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param input5 The fifth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function4<I6, I7, I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4, final I5 input5) {
    return (input6, input7, input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function5}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param input4 The fourth input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function5<I5, I6, I7, I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2, final I3 input3, final I4 input4) {
    return (input5, input6, input7, input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function6}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param input3 The third input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function6<I4, I5, I6, I7, I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2, final I3 input3) {
    return (input4, input5, input6, input7, input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function7}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param input2 The second input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function7<I3, I4, I5, I6, I7, I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1, final I2 input2) {
    return (input3, input4, input5, input6, input7, input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }

  /**
   * Partially applies a {@link Function9} to a {@link Function8}.
   *
   * @param base   The original function.
   * @param input1 The first input.
   * @param <I1>   The first type.
   * @param <I2>   The second type.
   * @param <I3>   The third type.
   * @param <I4>   The fourth type.
   * @param <I5>   The fifth type.
   * @param <I6>   The sixth type.
   * @param <I7>   The seventh type.
   * @param <I8>   The eighth type.
   * @param <I9>   The ninth type.
   * @param <R>    The return type.
   * @return The partially applied function.
   */
  public <I1, I2, I3, I4, I5, I6, I7, I8, I9, R> Function8<I2, I3, I4, I5, I6, I7, I8, I9, R> partial(final Function9<I1, I2, I3, I4, I5, I6, I7, I8, I9, R> base, final I1 input1) {
    return (input2, input3, input4, input5, input6, input7, input8, input9) -> base.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9);
  }
}
