package io.leonis.zosma.parameters;

import io.reactivex.functions.BiFunction;
import lombok.*;

/**
 * @author jeroen.dejong.
 */
@Value
@AllArgsConstructor
public class Tuple<I0, I1> {
  I0 first;
  I1 second;

  public <R> R apply(BiFunction<I0, I1, R> function) throws Exception {
    return function.apply(first, second);
  }

  public <I2> Triplet<I0, I1, I2> join(Singleton<I2> singleton) {
    return new Triplet<>(this.first, this.second, singleton.getFirst());
  }

  public <I2, I3> Quadruplet<I0, I1, I2, I3> join(Tuple<I2, I3> tuple) {
    return new Quadruplet<>(this.first, this.second, tuple.getFirst(), tuple.getSecond());
  }
}
