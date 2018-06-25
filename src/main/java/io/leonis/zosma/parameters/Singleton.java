package io.leonis.zosma.parameters;

import io.reactivex.functions.Function;
import lombok.*;

/**
 * @author jeroen.dejong.
 */
@Value
@AllArgsConstructor
public class Singleton<I0> {
  I0 first;

  public <R> R apply(Function<I0, R> function) throws Exception {
    return function.apply(first);
  }

  public <I1> Tuple<I0, I1> join(Singleton<I1> singleton) {
    return new Tuple<>(this.first, singleton.getFirst());
  }

  public <I1, I2> Triplet<I0, I1, I2> join(Tuple<I1, I2> tuple) {
    return new Triplet<>(this.first, tuple.getFirst(), tuple.getSecond());
  }

  public <I1, I2, I3> Quadruplet<I0, I1, I2, I3> join(Triplet<I1, I2, I3> triplet) {
    return new Quadruplet<>(this.first, triplet.getFirst(), triplet.getSecond(), triplet.getThird());
  }
}
